package org.jtheque.primary.utils.web.analyzers.generic.position;

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
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Node;

/**
 * @author Baptiste Wicht
 */
final class StringPositionFactory implements Factory<Position> {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) {
        return "string".equals(element.getNodeName());
    }

    @Override
    public Position factor(Node node, XMLReader<Node> reader) throws XMLException {
        StringPosition p = new StringPosition(reader.readString("text", node));

        if ("true".equals(reader.readString("@first", node))) {
            p.setFirst();
        }

        if ("true".equals(reader.readString("@last", node))) {
            p.setLast();
        }

        Node fromNode = reader.getNode("from", node);

        if (fromNode != null) {
            p.setFrom(fromNode.getTextContent());
        }

        Object addNode = reader.getNode("add", node);

        if (addNode != null) {
            p.setAdd(Integer.parseInt(((Node) addNode).getTextContent()));
        }

        return p;
    }

    /**
     * A position of a String in a line.
     *
     * @author Baptiste Wicht
     */
    private static final class StringPosition implements Position {
        private final String text;
        private String from;
        private boolean first;
        private boolean last;
        private int add;

        /**
         * Construct a new StringPosition.
         *
         * @param text The text to search in the line.
         */
        private StringPosition(String text) {
            super();

            this.text = text;
        }

        /**
         * Set the boolean flag indicate if we must add the length of the String to the position.
         */
        public void setFirst() {
            first = true;
        }

        /**
         * Set the boolean flag indicate if we must search the last occurrence.
         */
        public void setLast() {
            last = true;
        }

        /**
         * Set the String from which we start to search the occurrence.
         *
         * @param from The String from which we start to search the occurrence.
         */
        public void setFrom(String from) {
            this.from = from;
        }

        /**
         * Set a int value to add to the position.
         *
         * @param add The value to add to the position.
         */
        public void setAdd(int add) {
            this.add = add;
        }

        @Override
        public int intValue(String line) {
            int index;

            if (last) {
                index = from != null && !from.isEmpty() ? line.lastIndexOf(text, line.indexOf(from)) : line.lastIndexOf(text);
            } else {
                index = from != null && !from.isEmpty() ? line.indexOf(text, line.indexOf(from)) : line.indexOf(text);
            }

            if (first) {
                index = index + text.length() + add;
            }

            return index;
        }

        @Override
        public String toString() {
            return "StringPosition{" +
                    "text='" + text + '\'' +
                    ", from='" + from + '\'' +
                    ", first=" + first +
                    ", last=" + last +
                    ", add=" + add +
                    '}';
        }
    }
}
