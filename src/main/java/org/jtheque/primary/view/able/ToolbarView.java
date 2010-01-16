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

/**
 * A toolbar.
 *
 * @author Baptiste Wicht
 */
public interface ToolbarView {
	/**
	 * Set the display mode of the toolbar. A toolbar can have different mode normally in function of the mode of
	 * the controller.
	 *
	 * @param mode The mode to set.
	 */
	void setDisplayMode(ViewMode mode);
}