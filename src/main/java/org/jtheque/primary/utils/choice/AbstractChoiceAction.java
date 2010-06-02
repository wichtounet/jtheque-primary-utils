package org.jtheque.primary.utils.choice;

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
 * A choice action. It's an action who occurs on the choice view.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractChoiceAction implements ChoiceAction {
    private Object selectedItem;
    private String content;

    @Override
    public final void setSelectedItem(Object selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * Return the selected item. It's the item in which we execute the action.
     *
     * @return The selected item.
     */
    protected final Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Return the type of the content.
     *
     * @return The type of the content.
     */
    protected final String getContent() {
        return content;
    }

    @Override
    public final void setContent(String content) {
        this.content = content;
    }
}