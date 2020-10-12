/*
 * Copyright (C) 2019 StarChart-Labs@github.com Authors
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package org.starchartlabs.majortom.event.model;

import java.util.Objects;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Represents a message and it's importance level that should be dispatched to the subscribing organization
 *
 * @author romeara
 * @since 0.1.0
 */
public class Notification {

    public static final String SUBJECT = Notification.class.getName() + ":1";

    private static final Gson GSON = new GsonBuilder().create();

    private final String message;

    @Nullable
    private final NotificationLevel level;

    public Notification(String message, @Nullable NotificationLevel level) {
        this.message = Objects.requireNonNull(message);
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public NotificationLevel getLevel() {
        return level;
    }

    public String toJson() {
        return GSON.toJson(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(),
                getLevel());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof Notification) {
            Notification compare = (Notification) obj;

            result = Objects.equals(compare.getMessage(), getMessage())
                    && Objects.equals(compare.getLevel(), getLevel());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("message", getMessage())
                .add("level", getLevel())
                .toString();
    }

    public static Notification fromJson(String json) {
        Objects.requireNonNull(json);

        return GSON.fromJson(json, Notification.class);
    }

}
