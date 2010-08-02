package org.jtheque.primary.utils.web.analyzers.generic.operation;

import org.jtheque.primary.utils.web.analyzers.generic.FactoryContainer;
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Node;

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
 * A factory for Operation.
 *
 * @author Baptiste Wicht
 */
public final class OperationFactory {
    private static final FactoryContainer<Operation> FACTORY = new FactoryContainer<Operation>();

    /**
     * This an utility class, not instanciable.
     */
    private OperationFactory() {
        super();
    }

    static {
        FACTORY.add(new NextLineFactory());
        FACTORY.add(new TrimFactory());
        FACTORY.add(new DeleterFactory());
    }

    /**
     * Return the operation value on the element.
     *
     * @param element The element to get the value getter for.
     * @param reader  The reader to use.
     *
     * @return The factored Operation to use.
     *
     * @throws XMLException If an error occurs during the XML processing.
     */
    public static Operation getValueGetter(Node element, XMLReader<Node> reader) throws XMLException {
        return FACTORY.getFactoredObject(element, reader);
    }

}
