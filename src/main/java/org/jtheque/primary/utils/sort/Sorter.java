package org.jtheque.primary.utils.sort;

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

import org.jtheque.primary.utils.views.tree.JThequeTreeModel;

/**
 * A sorter. It populates a tree model with data depending on a content type and a sort type.
 *
 * @author Baptiste Wicht
 */
public interface Sorter {
	/**
	 * Indicate if the sorter can sort the specified type of content and sort.
	 *
	 * @param content The content.
	 * @param sortType The type of sort.
	 *
	 * @return <code>true</code> if the sorter can sort the specified type.
	 */
	boolean canSort(String content, String sortType);

	/**
	 * Populate the model.
	 *
	 * @param model The tree model to populate.
	 */
	void sort(JThequeTreeModel model);
}