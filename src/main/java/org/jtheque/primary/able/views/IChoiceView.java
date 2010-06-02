package org.jtheque.primary.able.views;

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
import org.jtheque.ui.able.IView;

/**
 * Represents a generic view in which we can do a choice.
 *
 * @author Baptiste Wicht
 */
public interface IChoiceView extends IView {
    /**
     * Display the choice view for a specific content.
     *
     * @param content The content
     */
    void display(String content);

    /**
     * Return the selected item in the interface.
     *
     * @return The selected item.
     */
    Data getSelectedItem();

}