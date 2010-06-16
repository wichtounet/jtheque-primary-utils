package org.jtheque.primary.utils.web.analyzers.generic.transform;

import org.jtheque.primary.utils.web.analyzers.generic.FactoryContainer;
import org.jtheque.xml.utils.IXMLReader;
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
 * A factory for Transformer objects.
 *
 * @author Baptiste Wicht
 */
public final class TransformerFactory {
    private static final FactoryContainer<Transformer> FACTORY = new FactoryContainer<Transformer>();

    /**
     * This an utility class, not instanciable.
     */
    private TransformerFactory() {
        super();
    }

    static {
        FACTORY.add(new CutFactory());
        FACTORY.add(new LastFactory());
        FACTORY.add(new DeleterFactory());
        FACTORY.add(new ReplacerFactory());
        FACTORY.add(new PrependerFactory());
        FACTORY.add(new AppenderFactory());
        FACTORY.add(new DeleteFirstCharFactory());
        FACTORY.add(new SplitterFactory());
    }

    /**
     * Return the transformer on the element.
     *
     * @param element The element to get the value getter for.
     * @param reader  The reader to use.
     *
     * @return The factored Transformer to use.
     *
     * @throws XMLException If an error occurs during the XML processing.
     */
    public static Transformer getTransformer(Node element, IXMLReader<Node> reader) throws XMLException {
        return FACTORY.getFactoredObject(element, reader);
    }
}