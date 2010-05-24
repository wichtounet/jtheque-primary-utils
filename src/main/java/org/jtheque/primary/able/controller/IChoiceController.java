package org.jtheque.primary.able.controller;

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

import org.jtheque.views.able.Controller;

/**
 * A choice controller specification.
 *
 * @author Baptiste Wicht
 */
public interface IChoiceController extends Controller {
	/**
	 * Do the choice action on the selected item.
	 *
	 * @param selectedItem The item selected on the choice view.
	 */
	void doAction(Object selectedItem);

	/**
	 * Set the action of the controller.
	 *
	 * @param action The new action
	 */
	void setAction(String action);

	/**
	 * Set the content of controller.
	 *
	 * @param content The new content
	 */
	void setContent(String content);

}
