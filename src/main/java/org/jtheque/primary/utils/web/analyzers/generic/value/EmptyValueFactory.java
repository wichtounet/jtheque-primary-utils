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
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Node;

/**
 * @author Baptiste Wicht
 */
final class EmptyValueFactory implements Factory<ValueGetter> {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) throws XMLException {
        return !reader.getNodes("emptyValue", element).isEmpty();
    }

    @Override
    public ValueGetter factor(Node element, XMLReader<Node> reader) {
        return new EmptyValue();
    }

    /**
     * A value getter that always return an empty value.
     *
     * @author Baptiste Wicht
     */
    private static final class EmptyValue implements ValueGetter {
        @Override
        public String getValue(String line) {
            return "";
        }
    }
}
