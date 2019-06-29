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
