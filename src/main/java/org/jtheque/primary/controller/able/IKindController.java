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
import org.jtheque.primary.od.able.Kind;
import org.jtheque.primary.view.able.IKindView;

/**
 * A kind controller specification.
 *
 * @author Baptiste Wicht
 */
public interface IKindController extends Controller {
    /**
     * Display the view to add a new Borrower.
     */
    void newKind();

    /**
     * Display the view to edit a kind.
     *
     * @param kind The kind to edit.
     */
    void editKind(Kind kind);

    /**
     * Save modifications made to the kind.
     *
     * @param name The name of the kind.
     */
    void save(String name);

    @Override
    IKindView getView();
}
