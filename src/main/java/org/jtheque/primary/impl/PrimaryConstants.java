package org.jtheque.primary.impl;

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
 * Constants of primary utils.
 *
 * @author Baptiste Wicht
 */
public interface PrimaryConstants {
    String BORROWER = "Borrower";
    String BORROWERS = "Borrowers";

    /**
     * The choice actions of the module.
     *
     * @author Baptiste Wicht
     */
    interface ChoiceActions {
        String EDIT = "edit";
        String DELETE = "delete";
    }
}