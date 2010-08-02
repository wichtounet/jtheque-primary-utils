package org.jtheque.primary.utils.web.analyzers.generic.condition;

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
 * An utility class to create Condition from the XML file.
 *
 * @author Baptiste Wicht
 */
public final class ConditionUtils {
    /**
     * Utility class, not instanciable.
     */
    private ConditionUtils() {
        super();
    }

    /**
     * Return the condition contained in a specific location of a specific node.
     *
     * @param currentNode The node to search in.
     * @param location    The location to search in.
     * @param reader      The XML Reader
     *
     * @return The Condition or null if we doesn't found one.
     *
     * @throws XMLException If an errors occurs during the parse of the XML Elements.
     */
    public static Condition getCondition(Object currentNode, String location, XMLReader<Node> reader) throws XMLException {
        Object conditionNode = reader.getNode(location, currentNode);

        if (conditionNode != null) {
            Node node = reader.getNode("*", conditionNode);

            if (node != null) {
                return ConditionFactory.getCondition(node, reader);
            }
        }

        return null;
    }
}