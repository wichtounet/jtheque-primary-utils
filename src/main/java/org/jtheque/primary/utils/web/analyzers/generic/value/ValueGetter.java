package org.jtheque.primary.utils.web.analyzers.generic.value;

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
 * A value getter.
 *
 * @author Baptiste Wicht
 */
public interface ValueGetter {
	/**
	 * Return the value in the line.
	 *
	 * @param line The line to search in.
	 *
	 * @return The value.
	 */
	String getValue(String line);
}