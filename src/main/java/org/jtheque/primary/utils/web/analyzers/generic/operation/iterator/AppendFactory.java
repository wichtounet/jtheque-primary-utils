package org.jtheque.primary.utils.web.analyzers.generic.operation.iterator;

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
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.value.BuilderPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetter;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetterFactory;
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Node;

/**
 * A factory for append iterator operation.
 *
 * @author Baptiste Wicht
 */
final class AppendFactory implements Factory<IteratorOperation> {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) {
        return "append".equals(element.getNodeName());
    }

    @Override
    public IteratorOperation factor(Node n, XMLReader<Node> reader) throws XMLException {
        return new AppendIteratorOperation(ValueGetterFactory.getValueGetter(n, reader));
    }

    /**
     * An operation which append some value to the builder.
     *
     * @author Baptiste Wicht
     */
    private static final class AppendIteratorOperation implements IteratorOperation {
        private final ValueGetter getter;

        /**
         * Construct a new AppendIteratorOperation.
         *
         * @param getter The value getter to use.
         */
        private AppendIteratorOperation(ValueGetter getter) {
            this.getter = getter;
        }

        @Override
        public String perform(String line, ScannerPossessor analyzer, BuilderPossessor iterator) {
            iterator.getBuilder().append(getter.getValue(line));

            return line;
        }
    }
}
