package org.jtheque.primary.view.impl.choice;

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
 * A choice action. It seems an action who can be executed on different type of data chosen on a list.
 *
 * @author Baptiste Wicht
 */
public interface ChoiceAction {
    /**
     * Indicate if this action can do the type of action.
     *
     * @param action The type of action.
     * @return <code>true</code> if the action can do this type else <code>false</code>.
     */
    boolean canDoAction(String action);

    /**
     * Execute the action.
     */
    void execute();

    /**
     * Set the selected item.
     *
     * @param selectedItem The selected item.
     */
    void setSelectedItem(Object selectedItem);

    /**
     * Set the type of the content.
     *
     * @param content The type of the content.
     */
    void setContent(String content);
}