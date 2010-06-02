package org.jtheque.primary.utils.web.analyzers.generic;

import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.XMLReader;

import org.jdom.Element;

import java.util.ArrayList;
import java.util.Collection;

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
 * A container for factories.
 *
 * @author Baptiste Wicht
 */
public final class FactoryContainer<T> {
    private final Collection<Factory<T>> factories = new ArrayList<Factory<T>>(10);

    /**
     * Add a factory to the container.
     *
     * @param factory The factory to add to the container.
     */
    public void add(Factory<T> factory) {
        factories.add(factory);
    }

    /**
     * Return the factored element
     *
     * @param element The element to get the factored object from.
     * @param reader  The reader to use.
     * @return The factored object.
     * @throws XMLException If an error occurs during the XML processing.
     */
    public T getFactoredObject(Element element, XMLReader reader) throws XMLException {
        for (Factory<T> factory : factories) {
            if (factory.canFactor(element, reader)) {
                return factory.factor(element, reader);
            }
        }

        return null;
	}
}
