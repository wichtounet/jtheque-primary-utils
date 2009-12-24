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

import org.jdom.Element;
import org.jtheque.core.utils.file.XMLException;
import org.jtheque.core.utils.file.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;
import org.jtheque.primary.utils.web.analyzers.generic.position.Position;
import org.jtheque.primary.utils.web.analyzers.generic.position.PositionFactory;

/**
 * @author Baptiste Wicht
 */
final class SimpleValueGetterFactory implements Factory<ValueGetter> {
    @Override
    public boolean canFactor(Element element, XMLReader reader) throws XMLException {
        return !reader.getNodes("value", element).isEmpty();
    }

    @Override
    public ValueGetter factor(Element element, XMLReader reader) throws XMLException {
        SimpleValueGetter simpleGetter = new SimpleValueGetter();

        simpleGetter.setEnd(getPosition(element, "end", reader));
        simpleGetter.setStart(getPosition(element, "start", reader));

        return simpleGetter;
    }

    /**
     * Return the position contained in a specific location of a specific node.
     *
     * @param currentNode The node to search in.
     * @param location    The location to search in.
     * @param reader      The XML reader.
     * @return The Position or null if we doesn't found one.
     * @throws XMLException If an errors occurs during the parse of the XML Elements.
     */
    private static Position getPosition(Object currentNode, String location, XMLReader reader) throws XMLException {
        Object positionNode = reader.getNode("value/" + location, currentNode);

        if (positionNode != null) {
            Element node = reader.getNode("*", positionNode);

            if (node != null) {
                return PositionFactory.getPosition(node, reader);
            }
        }

        return null;
    }

    /**
     * A simple value getter. It seems a getter who takes a value from a start position to an end
     * position.
     *
     * @author Baptiste Wicht
     */
    private static final class SimpleValueGetter implements ValueGetter {
        private Position start;
        private Position end;

        /**
         * Set the start position.
         *
         * @param start The start position.
         */
        public void setStart(Position start) {
            this.start = start;
        }

        /**
         * Set the end position.
         *
         * @param end The end position.
         */
        public void setEnd(Position end) {
            this.end = end;
        }

        @Override
        public String getValue(String line) {
            return line.substring(start.intValue(line), end.intValue(line));
        }

        @Override
        public String toString() {
            return "SimpleValueGetter{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
