package org.jtheque.primary.view.able;

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

import org.jtheque.core.managers.view.able.IView;

import javax.swing.JTextField;

/**
 * A language view specification.
 *
 * @author Baptiste Wicht
 */
public interface ILanguageView extends DataView, IView {
    /**
     * Return the field containing the name of the kind.
     *
     * @return The field for the name.
     */
    JTextField getFieldNom();
}
