package org.jtheque.primary.utils.web.analyzers.generic.operation.iterator;

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

import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.value.BuilderPossessor;

/**
 * An operation for an IteratorValue.
 *
 * @author Baptiste Wicht
 */
public interface IteratorOperation {
    /**
     * Perform the operation.
     *
     * @param line     The line.
     * @param analyzer The film analyzer.
     * @param iterator The iterator value.
     * @return The line, eventually modified.
     */
    String perform(String line, ScannerPossessor analyzer, BuilderPossessor iterator);
}