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
final class ContainNotFactory implements Factory<Condition> {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) {
        return "notcontains".equals(element.getNodeName());
    }

    @Override
    public Condition factor(Node element, XMLReader<Node> reader) {
        return new ContainNot(element.getTextContent());
    }

    /**
     * A contain not condition. It seems a condition who test if the line doesn't contain a text.
     *
     * @author Baptiste Wicht
     */
    private static final class ContainNot implements Condition {
        private final String text;

        /**
         * Construct a new Contain.
         *
         * @param text The text the line must contain to match the condition.
         */
        private ContainNot(String text) {
            super();

            this.text = text;
        }

        @Override
        public boolean match(String line) {
            return !line.contains(text);
        }
    }
}
