package org.jtheque.primary.utils.views.listeners;

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

import org.jtheque.primary.able.od.Data;

import java.util.EventObject;

/**
 * An event who occurs when the object change.
 *
 * @author Baptiste Wicht
 */
public final class ObjectChangedEvent extends EventObject {
    private static final long serialVersionUID = -6717915776583232663L;

    private final Data object;

    /**
     * Construct a new ObjectChangedEvent.
     *
     * @param source The source of the event
     */
    public ObjectChangedEvent(Object source) {
        this(source, null);
    }

    /**
     * Construct a new ObjectChangedEvent.
     *
     * @param source The source of the event
     * @param object The object who has changed
     */
    public ObjectChangedEvent(Object source, Data object) {
        super(source);

        this.object = object;
    }

    /**
     * Return the object of the event.
     *
     * @return The object.
     */
    public Data getObject() {
        return object;
    }
}
