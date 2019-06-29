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
package org.starchartlabs.majortom.event.model;

import java.util.Objects;

import javax.annotation.Nullable;

import org.starchartlabs.alloy.core.MoreObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
