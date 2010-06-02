package org.jtheque.primary.impl.views.actions.choice;

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

import org.jtheque.primary.able.controller.IChoiceController;
import org.jtheque.primary.able.views.IChoiceView;
import org.jtheque.ui.utils.actions.JThequeAction;

import java.awt.event.ActionEvent;

/**
 * An action to validate the borrower view.
 *
 * @author Baptiste Wicht
 */
public final class AcValidateChoiceView extends JThequeAction {
    private final IChoiceController choiceController;

    /**
     * Construct a new AcValidateChoiceView.
     *
     * @param choiceController The choice controller.
     */
    public AcValidateChoiceView(IChoiceController choiceController) {
        super("choice.actions.validate");

        this.choiceController = choiceController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IChoiceView choiceView = (IChoiceView) choiceController.getView();

        if (choiceView.validateContent()) {
            choiceController.doAction(choiceView.getSelectedItem());
		}
	}
}