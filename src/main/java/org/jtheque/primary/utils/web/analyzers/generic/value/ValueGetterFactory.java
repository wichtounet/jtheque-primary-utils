package org.jtheque.primary.utils.web.analyzers.generic.value;

import org.jtheque.primary.utils.web.analyzers.generic.FactoryContainer;
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
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
 * A factory for ValueGetter elements.
 *
 * @author Baptiste Wicht
 */
public final class ValueGetterFactory {
    private static final FactoryContainer<ValueGetter> FACTORY = new FactoryContainer<ValueGetter>();

    private static ScannerPossessor scannerPossessor;

    /**
     * This an utility class, not instanciable.
     */
    private ValueGetterFactory() {
        super();
    }

    static {
        FACTORY.add(new SimpleValueGetterFactory());
        FACTORY.add(new ConditionalValueGetterFactory());
        FACTORY.add(new IteratorValueGetterFactory());
        FACTORY.add(new EmptyValueFactory());
    }

    /**
     * Set the scanner possessor.
     *
     * @param scannerPossessor The scanner possessor.
     */
    public static void setScannerPossessor(ScannerPossessor scannerPossessor) {
        ValueGetterFactory.scannerPossessor = scannerPossessor;
    }

    /**
     * Return the scanner possessor.
     *
     * @return The scanner possessor.
     */
    static ScannerPossessor getScannerPossessor() {
        return scannerPossessor;
    }

    /**
     * Return the value getter on the element.
     *
     * @param element The element to get the value getter for.
     * @param reader  The reader to use.
     * @return The factored ValueGetter to use.
     * @throws XMLException If an error occurs during the XML processing.
     */
    public static ValueGetter getValueGetter(Element element, XMLReader reader) throws XMLException {
        return FACTORY.getFactoredObject(element, reader);
	}
}
