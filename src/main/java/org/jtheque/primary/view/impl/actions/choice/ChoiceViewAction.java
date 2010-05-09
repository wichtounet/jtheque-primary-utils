package org.jtheque.primary.view.impl.actions.choice;

import org.jtheque.primary.controller.able.IChoiceController;
import org.jtheque.ui.utils.actions.JThequeAction;

import java.awt.event.ActionEvent;

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
 * An action to launch the choice view with a specific action and data type.
 *
 * @author Baptiste Wicht
 */
public final class ChoiceViewAction extends JThequeAction {
	private final String action;
	private final String dataType;
    private final IChoiceController choiceController;

	/**
	 * Construct a new ChoiceViewAction.
	 *
	 * @param key The internationalization key.
     * @param action The choice action.
     * @param dataType The datatype of the data container.
     * @param choiceController
     */
	public ChoiceViewAction(String key, String action, String dataType, IChoiceController choiceController){
		super(key);

		this.action = action;
		this.dataType = dataType;
        this.choiceController = choiceController;
    }

	@Override
	public void actionPerformed(ActionEvent event){
		choiceController.setAction(action);
		choiceController.setContent(dataType);
		choiceController.displayView();
	}
}
