package org.jtheque.primary.od.impl.abstraction;

import org.jtheque.primary.od.able.Collection;

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

/**
 * A collection of films.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractCollection extends AbstractPrimaryData implements Collection {
    // Collections parameter
    private String title;
    private boolean protection;
    private String password;

    @Override
    public final void setTitle(String title) {
        this.title = title;
    }

    @Override
    public final String getTitle() {
        return title;
    }

    @Override
    public final boolean isProtection() {
        return protection;
    }

    @Override
    public final void setProtection(boolean protection) {
        this.protection = protection;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override
    public final void setPassword(String password) {
        this.password = password;
    }
}