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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class Handler implements RequestHandler<SNSEvent, Void> {

    private static final String PARAMETER_STORE_SLACK_URL = "SLACK_URL_SSM";

    /** Logger reference to output information to the application log files */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        // TODO make trace
        logger.info("Received SNS event: " + input);

        return null;
    }

}
