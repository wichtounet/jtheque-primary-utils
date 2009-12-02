package org.jtheque.primary.utils.web.analyzers.generic.field;

import org.jdom.Element;
import org.jtheque.core.utils.file.XMLException;
import org.jtheque.core.utils.file.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.FactoryContainer;

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
 * A factory for FieldGetter elements.
 *
 * @author Baptiste Wicht
 */
public final class FieldGetterFactory {
    private static final FactoryContainer<FieldGetter> FACTORY = new FactoryContainer<FieldGetter>();

    /**
     * This an utility class, not instanciable.
     */
    private FieldGetterFactory() {
        super();
    }

    static {
        FACTORY.add(new DisabledFieldGetterFactory());
        FACTORY.add(new MultiLineFieldGetterFactory());
        FACTORY.add(new SimpleFieldGetterFactory());
    }

    /**
     * Return the field getter on the element.
     *
     * @param element The element to get the value getter for.
     * @param reader  The reader to use.
     * @return The factored FieldGetter to use.
     * @throws XMLException If an error occurs during the XML processing.
     */
    public static FieldGetter getFieldGetter(Element element, XMLReader reader) throws XMLException {
        return FACTORY.getFactoredObject(element, reader);
    }
}