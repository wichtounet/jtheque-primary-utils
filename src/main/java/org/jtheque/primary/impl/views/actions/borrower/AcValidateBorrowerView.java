package org.jtheque.primary.impl.views.actions.borrower;

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

import org.jtheque.primary.able.controller.IBorrowerController;
import org.jtheque.primary.able.views.IBorrowerView;
import org.jtheque.ui.utils.actions.JThequeAction;

import java.awt.event.ActionEvent;

/**
 * An action to validate the borrower view.
 *
 * @author Baptiste Wicht
 */
public final class AcValidateBorrowerView extends JThequeAction {
	private final IBorrowerController borrowerController;

	/**
	 * Construct a new AcValidateBorrowerView.
	 *
	 * @param borrowerController
	 */
	public AcValidateBorrowerView(IBorrowerController borrowerController){
		super("borrower.actions.ok");

		this.borrowerController = borrowerController;
	}

	@Override
	public void actionPerformed(ActionEvent event){
        IBorrowerView borrowerView = (IBorrowerView) borrowerController.getView();

		if (borrowerView.validateContent()){
			borrowerController.save(
					borrowerView.getFieldFirstName().getText(),
					borrowerView.getFieldNom().getText(),
					borrowerView.getFieldEmail().getText());

			borrowerView.closeDown();
		}
	}
}