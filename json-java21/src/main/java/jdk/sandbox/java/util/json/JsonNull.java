/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
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

package jdk.sandbox.java.util.json;

import jdk.sandbox.internal.util.json.JsonNullImpl;

/**
 * The interface that represents JSON null.
 * <p>
 * A {@code JsonNull} can be produced by {@link Json#parse(String)}.
 * <p> Alternatively, {@link #of()} can be used to obtain a {@code JsonNull}.
 *
 * @since 99
 */
public non-sealed interface JsonNull extends JsonValue {

    /**
     * {@return a {@code JsonNull}}
     */
    static JsonNull of() {
        return JsonNullImpl.NULL;
    }

    /**
     * {@return true if the given {@code obj} is a {@code JsonNull}}
     */
    @Override
    boolean equals(Object obj);

    /**
     * {@return the hash code value of this {@code JsonNull}}
     */
    @Override
    int hashCode();
}
