package org.jtheque.primary.view.able;

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

import org.jtheque.primary.od.able.Data;
import org.jtheque.views.able.components.MainComponent;

/**
 * A view for the principal data. It seems a view that display film, realizer or actor. These views have the same
 * architecture.
 *
 * @author Baptiste Wicht
 */
public interface PrincipalDataView extends View, MainComponent {
	/**
	 * Select a specific data in the view.
	 *
	 * @param data The data to select.
	 */
	void select(Data data);

	/**
	 * Sort the datas in the view.
	 *
	 * @param sort The sort type.
	 */
	void sort(String sort);

	/**
	 * Sort again the view with the same sort type.
	 */
	void resort();

	/**
	 * Select the first data in the view.
	 */
	void selectFirst();

	/**
	 * Clear the view.
	 */
	void clear();
}