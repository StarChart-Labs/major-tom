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
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starchartlabs.alloy.core.MoreObjects;
import org.starchartlabs.alloy.core.Suppliers;
import org.starchartlabs.machete.ssm.parameter.StringParameter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Handler implements RequestHandler<SNSEvent, Void> {

    private static final String PARAMETER_STORE_SLACK_URL = "SLACK_URL_SSM";

    private static final Supplier<String> SLACK_URL_SUPPLIER = Suppliers
            .memoizeWithExpiration(StringParameter.fromEnv(PARAMETER_STORE_SLACK_URL), 10, TimeUnit.MINUTES);

    /** Logger reference to output information to the application log files */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        // TODO make trace
        logger.info("Received SNS event: " + input);

        postMessage("Hello World!");

        return null;
    }

    private void postMessage(String text) {
        Message message = new Message(text);

        OkHttpClient httpClient = new OkHttpClient();

        HttpUrl url = HttpUrl.get(SLACK_URL_SUPPLIER.get());

        Request request = new Request.Builder()
                .header("User-Agent", "StarChart-Labs/Major-Tom")
                .post(RequestBody.create(MediaType.get("application/json"), message.toJson()))
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

    private static final class Message {

        private static final Gson GSON = new GsonBuilder().create();

        private final String text;

        public Message(String text) {
            this.text = Objects.requireNonNull(text);
        }

        public String getText() {
            return text;
        }

        public String toJson() {
            return GSON.toJson(this);
        }

        @Override
        public int hashCode() {
            return Objects.hash(getText());
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            boolean result = false;

            if (obj instanceof Handler.Message) {
                Handler.Message compare = (Handler.Message) obj;

                result = Objects.equals(compare.getText(), getText());
            }

            return result;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(getClass()).omitNullValues()
                    .add("text", getText())
                    .toString();
        }
    }

}
