/*
 * Copyright (c) Jun 28, 2019 StarChart Labs Authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    romeara - initial API and implementation and/or initial documentation
 */
package org.starchartlabs.majortom.event.handler.aws;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starchartlabs.alloy.core.Suppliers;
import org.starchartlabs.machete.sns.SnsEvents;
import org.starchartlabs.machete.ssm.parameter.StringParameter;
import org.starchartlabs.majortom.event.handler.slack.AttachmentColor;
import org.starchartlabs.majortom.event.handler.slack.Message;
import org.starchartlabs.majortom.event.handler.slack.MessageAttachment;
import org.starchartlabs.majortom.event.model.Notification;
import org.starchartlabs.majortom.event.model.NotificationLevel;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Logic which handles receiving events from AWS SNS and dispatching them appropriately
 *
 * @author romeara
 * @since 0.1.0
 */
public class Handler implements RequestHandler<SNSEvent, Void> {

    private static final String PARAMETER_STORE_SLACK_URL = "SLACK_URL_SSM";

    private static final Supplier<String> SLACK_URL_SUPPLIER = Suppliers
            .memoizeWithExpiration(StringParameter.fromEnv(PARAMETER_STORE_SLACK_URL), 10, TimeUnit.MINUTES);

    /** Logger reference to output information to the application log files */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        logger.trace("Received SNS event: " + input);

        SnsEvents.getMessages(input, Notification::fromJson, Notification.SUBJECT).stream()
        .map(this::toMessage)
        .forEach(this::postMessage);

        return null;
    }

    private Message toMessage(Notification notification) {
        Message result = null;

        AttachmentColor color = null;

        if (NotificationLevel.DANGER.equals(notification.getLevel())) {
            color = AttachmentColor.DANGER;
        } else if (NotificationLevel.WARNING.equals(notification.getLevel())) {
            color = AttachmentColor.WARNING;
        } else if (NotificationLevel.GOOD.equals(notification.getLevel())) {
            color = AttachmentColor.GOOD;
        }

        if (color == null) {
            result = new Message(notification.getMessage(), null);
        } else {
            result = new Message(null, Collections.singleton(new MessageAttachment(notification.getMessage(), color)));
        }

        return result;
    }

    private void postMessage(Message message) {
        OkHttpClient httpClient = new OkHttpClient();

        HttpUrl url = HttpUrl.get(SLACK_URL_SUPPLIER.get());

        Request request = new Request.Builder()
                .header("User-Agent", "StarChart-Labs/Major-Tom")
                .post(RequestBody.create(message.toJson(), MediaType.get("application/json")))
                .url(url)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to POST status (" + response.code() + ")");
            }
        } catch (IOException e) {
            // TODO romeara Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}
