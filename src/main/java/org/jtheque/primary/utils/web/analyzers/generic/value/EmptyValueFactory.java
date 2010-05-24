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

import org.jdom.Element;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;
import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.XMLReader;

/**
 * @author Baptiste Wicht
 */
final class EmptyValueFactory implements Factory<ValueGetter> {
	@Override
	public boolean canFactor(Element element, XMLReader reader) throws XMLException {
		return !reader.getNodes("emptyValue", element).isEmpty();
	}

	@Override
	public ValueGetter factor(Element element, XMLReader reader){
		return new EmptyValue();
	}

	/**
	 * A value getter that always return an empty value.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class EmptyValue implements ValueGetter {
		@Override
		public String getValue(String line){
			return "";
		}
	}
}
