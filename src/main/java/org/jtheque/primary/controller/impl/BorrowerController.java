package org.jtheque.primary.controller.impl;

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

import org.jtheque.primary.controller.able.IBorrowerController;
import org.jtheque.primary.controller.impl.undo.GenericDataCreatedEdit;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.services.able.IPersonService;
import org.jtheque.primary.view.able.IBorrowerView;
import org.jtheque.primary.view.able.ViewMode;
import org.jtheque.undo.IUndoRedoService;
import org.jtheque.views.impl.AbstractController;

import javax.annotation.Resource;

/**
 * A Borrower Controller.
 *
 * @author Baptiste Wicht
 */
public final class BorrowerController extends AbstractController implements IBorrowerController {
	private ViewMode state = ViewMode.NEW;

	@Resource
	private IPersonService borrowersService;

	@Resource
	private IUndoRedoService undoRedoService;

	@Resource
	private IBorrowerView borrowerView;

	@Override
	public void newBorrower(){
		state = ViewMode.NEW;

        borrowerView.getModel().setBorrower(borrowersService.getEmptyPerson());
		borrowerView.reload();
	}

	@Override
	public void editBorrower(Person borrower){
		assert borrower.getType().equals(borrowersService.getPersonType()) : "The person must be a borrower";

		state = ViewMode.EDIT;

		borrowerView.getModel().setBorrower(borrower);
		borrowerView.reload();

		displayView();
	}

	@Override
	public void save(String firstName, String name, String email){
        Person borrower = borrowerView.getModel().getBorrower();

		borrower.setFirstName(firstName);
		borrower.setEmail(email);
		borrower.setName(name);

		if (state == ViewMode.NEW){
			borrowersService.create(borrower);

			undoRedoService.addEdit(new GenericDataCreatedEdit<Person>(borrowersService, borrower));
		} else {
			borrowersService.save(borrower);
		}
	}

	@Override
	public IBorrowerView getView(){
		return borrowerView;
	}
}