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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.undo.IUndoRedoManager;
import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.primary.controller.able.IBorrowerController;
import org.jtheque.primary.controller.impl.undo.CreatedPersonEdit;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.services.able.IBorrowersService;
import org.jtheque.primary.view.able.IBorrowerView;
import org.jtheque.primary.view.able.ViewMode;

import javax.annotation.Resource;

/**
 * A Borrower Controller.
 *
 * @author Baptiste Wicht
 */
public final class BorrowerController extends AbstractController implements IBorrowerController {
    private ViewMode state = ViewMode.NEW;
    private Person currentBorrower;

    @Resource
    private IBorrowersService borrowersService;

    @Resource
    private IBorrowerView borrowerView;

    @Override
    public void newBorrower() {
        state = ViewMode.NEW;

        borrowerView.reload();
        currentBorrower = borrowersService.getEmptyBorrower();
    }

    @Override
    public void editBorrower(Person borrower) {
        assert borrower.getType().equals(IBorrowersService.PERSON_TYPE) : "The person must be a borrower";

        state = ViewMode.EDIT;

        borrowerView.reload(borrower);
        currentBorrower = borrower;

        displayView();
    }

    @Override
    public void save(String firstName, String name, String email) {
        currentBorrower.setFirstName(firstName);
        currentBorrower.setEmail(email);
        currentBorrower.setName(name);

        if (state == ViewMode.NEW) {
            borrowersService.create(currentBorrower);

            Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedPersonEdit(currentBorrower));
        } else {
            borrowersService.save(currentBorrower);
        }
    }

    @Override
    public IBorrowerView getView() {
        return borrowerView;
    }
}