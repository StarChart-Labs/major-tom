/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.majortom.event.handler.slack;

import java.lang.reflect.Type;
import java.util.Objects;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Represents the JSON form sent to Slack when posting a message with "attachments"
 *
 * @author romeara
 * @since 0.1.0
 */
public class MessageAttachment {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(MessageAttachment.class, new Serializer())
            .create();

    private final String text;

    private final AttachmentColor color;

    public MessageAttachment(String text, AttachmentColor color) {
        this.text = Objects.requireNonNull(text);
        this.color = Objects.requireNonNull(color);
    }

    public String getText() {
        return text;
    }

    public AttachmentColor getColor() {
        return color;
    }

    public String toJson() {
        return GSON.toJson(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(),
                getColor());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof MessageAttachment) {
            MessageAttachment compare = (MessageAttachment) obj;

            result = Objects.equals(compare.getText(), getText())
                    && Objects.equals(compare.getColor(), getColor());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("text", getText())
                .add("color", getColor())
                .toString();
    }

    public static final class Serializer implements JsonSerializer<MessageAttachment> {

        @Override
        public JsonElement serialize(MessageAttachment src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("text", src.getText());
            jsonObject.addProperty("color", src.getColor().getSlackCode());

            return jsonObject;
        }

    }

}
