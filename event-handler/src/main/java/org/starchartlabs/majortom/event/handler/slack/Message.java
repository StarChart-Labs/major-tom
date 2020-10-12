/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.majortom.event.handler.slack;

import java.util.Collection;
import java.util.Objects;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Represents the JSON form sent to Slack to post a message
 *
 * @author romeara
 * @since 0.1.0
 */
public class Message {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(MessageAttachment.class, new MessageAttachment.Serializer())
            .create();

    @Nullable
    private final String text;

    @Nullable
    private final Collection<MessageAttachment> attachments;

    public Message(@Nullable String text, @Nullable Collection<MessageAttachment> attachments) {
        this.text = text;
        this.attachments = attachments;
    }

    @Nullable
    public String getText() {
        return text;
    }

    @Nullable
    public Collection<MessageAttachment> getAttachments() {
        return attachments;
    }

    public String toJson() {
        return GSON.toJson(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(),
                getAttachments());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof Message) {
            Message compare = (Message) obj;

            result = Objects.equals(compare.getText(), getText())
                    && Objects.equals(compare.getAttachments(), getAttachments());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("text", getText())
                .add("attachments", getAttachments())
                .toString();
    }

}
