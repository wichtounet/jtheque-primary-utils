package org.jtheque.primary.utils.web.analyzers.generic.field;

import org.jdom.Element;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;
import org.jtheque.primary.utils.web.analyzers.generic.field.SimpleFieldGetterFactory.SimpleFieldGetter;
import org.jtheque.primary.utils.web.analyzers.generic.operation.Operation;
import org.jtheque.primary.utils.web.analyzers.generic.operation.OperationFactory;
import org.jtheque.primary.utils.web.analyzers.generic.transform.Transformer;
import org.jtheque.primary.utils.web.analyzers.generic.transform.TransformerFactory;
import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.XMLReader;

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
 * @author Baptiste Wicht
 */
public abstract class AbstractFieldGetterFactory implements Factory<FieldGetter> {
	/**
	 * Init the operations of a Field Getter.
	 *
	 * @param getter The field getter.
	 * @param node The node in which we search for operations.
	 * @param reader The XML Reader
	 *
	 * @throws XMLException If an errors occurs during the parse of the XML Elements.
	 */
	static void initOperations(SimpleFieldGetter getter, Object node, XMLReader reader) throws XMLException {
		for (Element n : reader.getNodes("operations/*", node)){
			Operation operation = OperationFactory.getValueGetter(n, reader);

			if (operation != null){
				getter.addOperation(operation);
			}
		}
	}

	/**
	 * Init the transformers of a field getter.
	 *
	 * @param getter The getter to fill.
	 * @param currentNode The node of the getter.
	 * @param reader The XML Reader
	 *
	 * @throws XMLException Thrown if an errors occurs during the xml reading process.
	 */
	static void initTransformers(SimpleFieldGetter getter, Object currentNode, XMLReader reader) throws XMLException{
		for (Element n : reader.getNodes("transformers/*", currentNode)){
			Transformer transformer = TransformerFactory.getTransformer(n, reader);

			if (transformer != null){
				getter.addTransformer(transformer);
			}
		}
	}
}