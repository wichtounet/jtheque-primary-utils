package org.jtheque.primary.controller.able;

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

import org.jtheque.core.managers.view.able.controller.Controller;
import org.jtheque.primary.od.able.Type;
import org.jtheque.primary.view.able.ITypeView;

/**
 * A type controller specification.
 *
 * @author Baptiste Wicht
 */
public interface ITypeController extends Controller {
    /**
     * Display the view to create a new type.
     */
    void newType();

    /**
     * Display the view to edit a type.
     *
     * @param type The type to edit.
     */
    void editType(Type type);

    /**
     * Save modifications made to the type.
     *
     * @param name The name of the type.
     */
    void save(String name);

    @Override
    ITypeView getView();
}
