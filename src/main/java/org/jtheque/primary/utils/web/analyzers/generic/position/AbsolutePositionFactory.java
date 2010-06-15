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
import org.jtheque.xml.utils.IXMLReader;

import org.w3c.dom.Node;

/**
 * @author Baptiste Wicht
 */
final class AbsolutePositionFactory implements Factory<Position> {
    @Override
    public boolean canFactor(Node element, IXMLReader reader) {
        return "absolute".equals(element.getNodeName());
    }

    @Override
    public Position factor(Node n, IXMLReader reader) {
        return new AbsolutePosition(Integer.parseInt(n.getTextContent()));
    }

    /**
     * An absolute position.
     *
     * @author Baptiste Wicht
     */
    private static final class AbsolutePosition implements Position {
        private final int position;

        /**
         * Construct a new AbsolutePosition.
         *
         * @param position The position to return.
         */
        private AbsolutePosition(int position) {
            super();

            this.position = position;
        }

        @Override
        public int intValue(String line) {
            return position;
        }

        @Override
        public String toString() {
            return "AbsolutePosition{" +
                    "position=" + position +
                    '}';
        }
    }
}
