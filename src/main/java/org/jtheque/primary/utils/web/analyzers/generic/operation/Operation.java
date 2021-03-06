package org.jtheque.primary.utils.web.analyzers.generic.operation;

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
 * An operation for a FieldGetter.
 *
 * @author Baptiste Wicht
 */
public interface Operation {
    /**
     * Perform the operation.
     *
     * @param line     The line.
     * @param analyzer The film analyzer.
     *
     * @return The line, eventually modified.
     */
    String perform(String line, ScannerPossessor analyzer);
}