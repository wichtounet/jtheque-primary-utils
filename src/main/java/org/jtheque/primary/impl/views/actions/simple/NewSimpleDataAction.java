package org.jtheque.primary.impl.views.actions.simple;

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

import org.jtheque.primary.able.controller.ISimpleController;
import org.jtheque.ui.utils.actions.JThequeAction;

import java.awt.event.ActionEvent;

/**
 * An action to create a new kind.
 *
 * @author Baptiste Wicht
 */
public final class NewSimpleDataAction extends JThequeAction {
    private final ISimpleController controller;

    /**
     * Construct a AcNewKind.
     *
     * @param key        The text key of the action.
     * @param controller The controller to use.
     */
    public NewSimpleDataAction(String key, ISimpleController controller) {
        super(key);

        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.create();
        controller.displayView();
	}
}