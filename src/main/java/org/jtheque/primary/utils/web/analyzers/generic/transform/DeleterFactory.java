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
import org.jtheque.xml.utils.IXMLReader;

import org.w3c.dom.Node;

/**
 * @author Baptiste Wicht
 */
public final class DeleterFactory implements Factory<Transformer> {
    @Override
    public boolean canFactor(Node element, IXMLReader reader) {
        return "deleter".equals(element.getNodeName());
    }

    @Override
    public Transformer factor(Node element, IXMLReader reader) {
        return new DeleterTransformer(element.getTextContent());
    }

    /**
     * A Transformer who delete some text of the value.
     *
     * @author Baptiste Wicht
     */
    private static final class DeleterTransformer implements Transformer {
        private final String text;

        /**
         * Construct a new Deleter.
         *
         * @param text The text to delete.
         */
        private DeleterTransformer(String text) {
            super();

            this.text = text;
        }

        @Override
        public String transform(String value) {
            return value.replace(text, "");
        }

        @Override
        public String toString() {
            return "DeleterTransformer{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }
}
