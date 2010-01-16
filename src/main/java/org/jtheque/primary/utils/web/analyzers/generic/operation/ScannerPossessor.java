package org.jtheque.primary.utils.web.analyzers.generic.operation;

import java.util.Scanner;

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
 * A scanner possessor. It seems an objet who have a scanner.
 *
 * @author Baptiste Wicht
 */
public interface ScannerPossessor {
	/**
	 * Return the scanner.
	 *
	 * @return The scanner.
	 */
	Scanner getScanner();
}