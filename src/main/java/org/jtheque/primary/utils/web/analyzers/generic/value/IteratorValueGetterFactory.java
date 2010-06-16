package org.jtheque.primary.utils.web.analyzers.generic.value;

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

import org.jtheque.primary.utils.web.analyzers.generic.Factory;
import org.jtheque.primary.utils.web.analyzers.generic.condition.ConditionUtils;
import org.jtheque.primary.utils.web.analyzers.generic.operation.iterator.IteratorOperation;
import org.jtheque.primary.utils.web.analyzers.generic.operation.iterator.IteratorOperationFactory;
import org.jtheque.xml.utils.IXMLReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Baptiste Wicht
 */
public final class IteratorValueGetterFactory implements Factory<ValueGetter> {
    @Override
    public boolean canFactor(Node element, IXMLReader<Node> reader) throws XMLException {
        return !reader.getNodes("iterator", element).isEmpty();
    }

    @Override
    public ValueGetter factor(Node element, IXMLReader<Node> reader) throws XMLException {
        Node n = reader.getNode("iterator", element);

        IteratorValue value = new IteratorValue(ValueGetterFactory.getScannerPossessor());

        for (IteratorOperation operation : getIteratorOperations(n, "before", reader)) {
            value.addOperationsBefore(operation);
        }

        for (IteratorOperation operation : getIteratorOperations(n, "after", reader)) {
            value.addOperationsAfter(operation);
        }

        for (IteratorOperation operation : getIteratorOperations(n, "operations", reader)) {
            value.addOperations(operation);
        }

        value.setCondition(ConditionUtils.getCondition(n, "condition", reader));

        return value;
    }

    /**
     * Return the iterator operations in a specific location of node.
     *
     * @param node     The node to search in.
     * @param location The location to search in.
     * @param reader   The XML reader.
     *
     * @return A List containing all the IteratorOperation found in the specific location of the node.
     *
     * @throws XMLException If an errors occurs during the parse of the XML Elements.
     */
    private static Iterable<IteratorOperation> getIteratorOperations(Object node, String location, IXMLReader<Node> reader) throws XMLException {
        Collection<IteratorOperation> operations = new ArrayList<IteratorOperation>(8);

        for (Node n : reader.getNodes(location + "/*", node)) {
            operations.add(IteratorOperationFactory.getPosition(n, reader));
        }

        return operations;
    }
}
