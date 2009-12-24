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

import org.jtheque.primary.od.able.Collection;
import org.jtheque.primary.od.impl.abstraction.AbstractCollection;
import org.jtheque.utils.bean.HashCodeUtils;

/**
 * An implementation of a collection of film.
 *
 * @author Baptiste Wicht
 */
public final class CollectionImpl extends AbstractCollection {
    @Override
    public String getDisplayableText() {
        return getTitle();
    }

    @Override
    public String toString() {
        return getDisplayableText();
    }

    @Override
    public int hashCode() {
        return HashCodeUtils.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Collection other = (Collection) obj;

        if (getId() != other.getId()) {
            return false;
        }

        if (getTitle() == null) {
            if (other.getTitle() != null) {
                return false;
            }
        } else if (!getTitle().equals(other.getTitle())) {
            return false;
        }

        if (getPassword() == null) {
            if (other.getPassword() != null) {
                return false;
            }
        } else if (!getPassword().equals(other.getPassword())) {
            return false;
        }

        return isProtection() == other.isProtection();
    }
}