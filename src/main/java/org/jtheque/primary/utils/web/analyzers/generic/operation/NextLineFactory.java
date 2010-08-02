package org.jtheque.primary.utils.web.analyzers.generic.operation;

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
final class NextLineFactory implements Factory<Operation> {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) {
        return "nextline".equals(element.getNodeName());
    }

    @Override
    public Operation factor(Node element, XMLReader<Node> reader) {
        return new NextLineOperation();
    }

    /**
     * An operation which pass to the next line.
     *
     * @author Baptiste Wicht
     */
    static final class NextLineOperation implements Operation {
        @Override
        public String perform(String line, ScannerPossessor analyzer) {
            return analyzer.getScanner().nextLine();
        }
    }
}
