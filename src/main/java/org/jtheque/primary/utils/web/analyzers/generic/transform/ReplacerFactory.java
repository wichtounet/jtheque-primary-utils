package org.jtheque.primary.utils.web.analyzers.generic.transform;

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
import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.javax.XMLReader;

import org.w3c.dom.Node;

/**
 * A factory for replacer objects.
 *
 * @author Baptiste Wicht
 */
final class ReplacerFactory implements Factory<Transformer> {
    @Override
    public boolean canFactor(Node element, XMLReader reader) {
        return "replacer".equals(element.getNodeName());
    }

    @Override
    public Transformer factor(Node n, XMLReader reader) throws XMLException {
        return new Replacer(reader.readString("from", n), reader.readString("to", n));
    }

    /**
     * A Transformer who replace all the occurrences of a char sequence with an another.
     *
     * @author Baptiste Wicht
     */
    private static final class Replacer implements Transformer {
        private final String from;
        private final String to;

        /**
         * Construct a new Replacer.
         *
         * @param from The char sequence to replace.
         * @param to   The char to replace with.
         */
        private Replacer(String from, String to) {
            super();

            this.from = from;
            this.to = to;
        }

        @Override
        public String transform(String value) {
            return value.replace(from, to);
        }

        @Override
        public String toString() {
            return "Replacer{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
					'}';
		}
	}
}
