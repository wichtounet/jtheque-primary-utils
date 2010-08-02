package org.jtheque.primary.able.views.model;

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

import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.ui.able.Model;

/**
 * A model for simple data.
 *
 * @author Baptiste Wicht
 */
public interface ISimpleDataModel extends Model {
    /**
     * Return the current simple data.
     *
     * @return The current simple data.
     */
    SimpleData getSimpleData();

    /**
     * Set the current simple data.
     *
     * @param data The simple data to set as current.
     */
    void setSimpleData(SimpleData data);
}