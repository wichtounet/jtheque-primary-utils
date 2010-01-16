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

import org.jdom.Element;
import org.jtheque.core.utils.file.XMLException;
import org.jtheque.core.utils.file.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;

/**
 * @author Baptiste Wicht
 */
final class LineEndPositionFactory implements Factory<Position> {
	@Override
	public boolean canFactor(Element element, XMLReader reader){
		return "lineEnd".equals(element.getName());
	}

	@Override
	public Position factor(Element n, XMLReader reader) throws XMLException{
		return new LineEndPosition();
	}

	/**
	 * A position who indicate the end of the line.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class LineEndPosition implements Position {
		@Override
		public int intValue(String line){
			return line.length();
		}
	}
}
