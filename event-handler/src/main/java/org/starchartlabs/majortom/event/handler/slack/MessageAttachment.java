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
