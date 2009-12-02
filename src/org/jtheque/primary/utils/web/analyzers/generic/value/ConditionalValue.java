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
 * A Conditional Value. It seems a value who return the value if the condition match the line.
 *
 * @author Baptiste Wicht
 */
public interface ConditionalValue {
    /**
     * Indicate if the condition match the line.
     *
     * @param line The line to test.
     * @return true if the condition match the line or not.
     */
    boolean match(String line);

    /**
     * Return the value.
     *
     * @param line The line to search in.
     * @return The value.
     */
    String getValue(String line);
}