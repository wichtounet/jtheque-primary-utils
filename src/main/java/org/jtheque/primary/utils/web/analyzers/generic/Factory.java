package org.jtheque.primary.utils.web.analyzers.generic;

import org.jdom.Element;
import org.jtheque.io.XMLException;
import org.jtheque.io.XMLReader;

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
 * A XML element factory.
 *
 * @author Baptiste Wicht
 */
public interface Factory<T> {
	/**
	 * Indicate if the factory can factor with the specified elements.
	 *
	 * @param element The current Element.
	 * @param reader The XML reader.
	 *
	 * @return true if the factory can factor else false.
	 *
	 * @throws XMLException If an error occurs during the XML processing.
	 */
	boolean canFactor(Element element, XMLReader reader) throws XMLException;

	/**
	 * Create and return the factored element.
	 *
	 * @param element The current element.
	 * @param reader The XML reader to use.
	 *
	 * @return The factored element.
	 *
	 * @throws XMLException If an error occurs during the XML processing.
	 */
	T factor(Element element, XMLReader reader) throws XMLException;
}