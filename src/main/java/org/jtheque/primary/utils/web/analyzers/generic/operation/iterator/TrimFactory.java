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

import org.jdom.Element;
import org.jtheque.io.XMLException;
import org.jtheque.io.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.value.BuilderPossessor;

/**
 * @author Baptiste Wicht
 */
final class TrimFactory implements Factory<IteratorOperation> {
	@Override
	public boolean canFactor(Element element, XMLReader reader){
		return "trim".equals(element.getName());
	}

	@Override
	public IteratorOperation factor(Element n, XMLReader reader) throws XMLException {
		return new TrimIteratorOperation();
	}

	/**
	 * An operation which trim the line.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class TrimIteratorOperation implements IteratorOperation {
		@Override
		public String perform(String line, ScannerPossessor analyzer, BuilderPossessor iterator){
			return line.trim();
		}
	}
}