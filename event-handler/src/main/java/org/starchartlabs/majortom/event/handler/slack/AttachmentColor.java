/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.majortom.event.handler.slack;

import java.util.Objects;

/**
 * Represents the various colors which may be used with Slack message attachments for additional meaning
 *
 * @author romeara
 * @since 0.1.0
 */
public enum AttachmentColor {

    GOOD("good"),

    WARNING("warning"),

    DANGER("danger"),

    ;

    private final String slackCode;

    AttachmentColor(String slackCode) {
        this.slackCode = Objects.requireNonNull(slackCode);
    }

    public String getSlackCode() {
        return slackCode;
    }

}
