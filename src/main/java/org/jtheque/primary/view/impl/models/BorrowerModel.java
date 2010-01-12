package org.jtheque.primary.view.impl.models;

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

import org.jtheque.primary.PrimaryConstants;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.view.impl.models.able.IBorrowerModel;

/**
 * A model for the borrower view.
 *
 * @author Baptiste Wicht
 */
public final class BorrowerModel implements IBorrowerModel {
    private Person borrower;

    @Override
    public void setBorrower(Person borrower) {
        assert borrower.getType().equals(PrimaryConstants.BORROWER) : "The person must be a borrower";

        this.borrower = borrower;
    }

    @Override
    public Person getBorrower() {
        return borrower;
    }
}