package org.jtheque.primary.od.impl;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jtheque.primary.od.able.Type;
import org.jtheque.primary.od.impl.abstraction.AbstractType;
import org.jtheque.utils.bean.EqualsUtils;
import org.jtheque.utils.bean.HashCodeUtils;

/**
 * A type.
 *
 * @author Baptiste Wicht
 */
public final class TypeImpl extends AbstractType {
    @Override
    public String getDisplayableText() {
        return getName();
    }

    @Override
    public String toString() {
        return getDisplayableText();
    }

    @Override
    public int hashCode() {
        return HashCodeUtils.hashCode(this, "id", "name", "primaryImpl");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (EqualsUtils.areObjectIncompatible(this, obj)) {
            return false;
        }

        final Type other = (Type) obj;

        if (EqualsUtils.areNotEquals(getName(), other.getName())) {
            return false;
        }

        return !EqualsUtils.areNotEquals(getPrimaryImpl(), other.getPrimaryImpl());
    }
}