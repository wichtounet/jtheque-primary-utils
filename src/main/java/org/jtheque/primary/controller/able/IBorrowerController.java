package org.jtheque.primary.controller.able;

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

import org.jtheque.core.managers.view.able.controller.Controller;
import org.jtheque.primary.od.able.Person;

/**
 * A borrower controller specification.
 *
 * @author Baptiste Wicht
 */
public interface IBorrowerController extends Controller {
	/**
	 * Display the view to create a new Borrower.
	 */
	void newBorrower();

	/**
	 * Display the view to edit a Borrower.
	 *
	 * @param borrower The borrower to edit.
	 */
	void editBorrower(Person borrower);

	/**
	 * Save modifications done to the borrower.
	 *
	 * @param firstName The first name of the borrower.
	 * @param name The name of the borrower.
	 * @param email The email of the borrower.
	 */
	void save(String firstName, String name, String email);

}
