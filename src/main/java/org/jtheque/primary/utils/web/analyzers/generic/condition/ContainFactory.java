package org.jtheque.primary.utils.web.analyzers.generic.condition;

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

import org.w3c.dom.Node;

/**
 * @author Baptiste Wicht
 */
final class ContainFactory implements Factory<Condition> {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) {
        return "contains".equals(element.getNodeName());
    }

    @Override
    public Condition factor(Node element, XMLReader<Node> reader) {
        return new Contain(element.getTextContent());
    }

    /**
     * A contains condition. It seems a condition who test if the line contains a certain text or not.
     *
     * @author Baptiste Wicht
     */
    private static final class Contain implements Condition {
        private final String text;

        /**
         * Construct a new Contain.
         *
         * @param text The text the line must contain to match the condition.
         */
        private Contain(String text) {
            super();

            this.text = text;
        }

        @Override
        public boolean match(String line) {
            return line.contains(text);
        }

        @Override
        public String toString() {
            return "Contain{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }
}
