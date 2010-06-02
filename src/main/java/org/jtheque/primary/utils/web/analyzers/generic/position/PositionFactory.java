package org.jtheque.primary.utils.web.analyzers.generic.position;

import org.jtheque.primary.utils.web.analyzers.generic.FactoryContainer;
import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.XMLReader;

import org.jdom.Element;

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
 * A factory for Position.
 *
 * @author Baptiste Wicht
 */
public final class PositionFactory {
    private static final FactoryContainer<Position> FACTORY = new FactoryContainer<Position>();

    /**
     * This an utility class, not instanciable.
     */
    private PositionFactory() {
        super();
    }

    static {
        FACTORY.add(new StringPositionFactory());
        FACTORY.add(new AbsolutePositionFactory());
        FACTORY.add(new LineEndPositionFactory());
    }

    /**
     * Return the position on the element.
     *
     * @param element The element to get the value getter for.
     * @param reader  The reader to use.
     * @return The factored Position to use.
     * @throws XMLException If an error occurs during the XML processing.
     */
    public static Position getPosition(Element element, XMLReader reader) throws XMLException {
        return FACTORY.getFactoredObject(element, reader);
	}

}
