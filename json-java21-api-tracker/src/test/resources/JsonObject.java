/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package java.util.json;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import jdk.internal.javac.PreviewFeature;
import jdk.internal.util.json.JsonObjectImpl;

/**
 * The interface that represents JSON object.
 *
 * <p>
 * A {@code JsonObject} can be produced by a {@link Json#parse(String)}.
 *
 * Alternatively, {@link #of(Map)} can be used to obtain a {@code JsonObject}.
 * Implementations of {@code JsonObject} cannot be created from sources that
 * contain duplicate member names. If duplicate names appear during
 * a {@link Json#parse(String)}, a {@code JsonParseException} is thrown.
 *
 * @since 99
 */
@PreviewFeature(feature = PreviewFeature.Feature.JSON)
public non-sealed interface JsonObject extends JsonValue {

    /**
     * {@return an unmodifiable map of the {@code String} to {@code JsonValue}
     * members in this {@code JsonObject}}
     */
    Map<String, JsonValue> members();

    /**
     * {@return the {@code JsonObject} created from the given
     * map of {@code String} to {@code JsonValue}s}
     *
     * The {@code JsonObject}'s members occur in the same order as the given
     * map's entries.
     *
     * @param map the map of {@code JsonValue}s. Non-null.
     * @throws NullPointerException if {@code map} is {@code null}, contains
     * any keys that are {@code null}, or contains any values that are {@code null}.
     */
    static JsonObject of(Map<String, JsonValue> map) {
        return new JsonObjectImpl(map.entrySet() // Implicit NPE on map
                                     .stream()
                                     .collect(Collectors.toMap(
                                             e -> Objects.requireNonNull(e.getKey()),
                                             Map.Entry::getValue, // Implicit NPE on val
                                             (_, v) -> v,
                                             LinkedHashMap::new)));
    }

    /**
     * {@return {@code true} if the given object is also a {@code JsonObject}
     * and the two {@code JsonObject}s represent the same mappings} Two
     * {@code JsonObject}s {@code jo1} and {@code jo2} represent the same
     * mappings if {@code jo1.members().equals(jo2.members())}.
     *
     * @see #members()
     */
    @Override
    boolean equals(Object obj);

    /**
     * {@return the hash code value for this {@code JsonObject}} The hash code value
     * of a {@code JsonObject} is derived from the hash code of {@code JsonObject}'s
     * {@link #members()}. Thus, for two {@code JsonObject}s {@code jo1} and {@code jo2},
     * {@code jo1.equals(jo2)} implies that {@code jo1.hashCode() == jo2.hashCode()}
     * as required by the general contract of {@link Object#hashCode}.
     *
     * @see #members()
     */
    @Override
    int hashCode();
}
