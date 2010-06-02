package org.jtheque.primary.able.controller;

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
import org.jtheque.primary.able.views.ISimpleDataView;
import org.jtheque.views.able.Controller;

/**
 * A controller for simple data.
 *
 * @author Baptiste Wicht
 */
public interface ISimpleController extends Controller {
    /**
     * Create a new simple data.
     */
    void create();

    /**
     * Edit the specified data.
     *
     * @param data The data to edit.
     */
    void edit(SimpleData data);

    /**
     * Save the data using the specified name as the new name of the data.
     *
     * @param name The new name to set.
     */
    void save(String name);

    @Override
    ISimpleDataView getView();
}