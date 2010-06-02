package org.jtheque.primary.utils.web.analyzers.generic.condition;

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
 * A factory to build Condition objects.
 *
 * @author Baptiste Wicht
 */
final class ConditionFactory {
    private static final FactoryContainer<Condition> FACTORY = new FactoryContainer<Condition>();

    /**
     * This is an utility class, non instanciable.
     */
    private ConditionFactory() {
        super();
    }

    static {
        FACTORY.add(new ContainFactory());
        FACTORY.add(new ContainNotFactory());
        FACTORY.add(new StartsNotFactory());
        FACTORY.add(new StartsFactory());
    }

    /**
     * Return the condition under the specified element.
     *
     * @param element The element to get the condition for.
     * @param reader  The reader to use.
     * @return The condition of the specified element.
     * @throws XMLException if an error occurs during the XML processing.
     */
    public static Condition getCondition(Element element, XMLReader reader) throws XMLException {
        return FACTORY.getFactoredObject(element, reader);
	}
}