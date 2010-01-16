package org.jtheque.primary.utils.web.analyzers.generic.operation.iterator;

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
 * A factory for iterator operations.
 *
 * @author Baptiste Wicht
 */
public final class IteratorOperationFactory {
	private static final FactoryContainer<IteratorOperation> FACTORY = new FactoryContainer<IteratorOperation>();

	/**
	 * This an utility class, not instanciable.
	 */
	private IteratorOperationFactory(){
		super();
	}

	static{
		FACTORY.add(new NextLineFactory());
		FACTORY.add(new TrimFactory());
		FACTORY.add(new AppendLineFactory());
		FACTORY.add(new AppendTextFactory());
		FACTORY.add(new AppendFactory());
	}

	/**
	 * Return the iterator operation on the element.
	 *
	 * @param element The element to get the value getter for.
	 * @param reader The reader to use.
	 *
	 * @return The factored IteratorOperation to use.
	 *
	 * @throws XMLException If an error occurs during the XML processing.
	 */
	public static IteratorOperation getPosition(Element element, XMLReader reader) throws XMLException{
		return FACTORY.getFactoredObject(element, reader);
	}
}
