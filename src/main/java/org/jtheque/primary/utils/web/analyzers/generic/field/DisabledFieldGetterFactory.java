package org.jtheque.primary.utils.web.analyzers.generic.field;

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
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;

/**
 * @author Baptiste Wicht
 */
final class DisabledFieldGetterFactory implements Factory<FieldGetter> {
	@Override
	public boolean canFactor(Element element, XMLReader reader) throws XMLException{
		return "true".equals(reader.readString("@disabled", element));
	}

	@Override
	public FieldGetter factor(Element element, XMLReader reader) throws XMLException{
		return new DisabledFieldGetter(reader.readString("@field", element));
	}

	/**
	 * A disabled field getter. It seems a field getter who never read a line.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class DisabledFieldGetter implements FieldGetter {
		private final String fieldName;

		/**
		 * Construct a new DisabledFieldGetter.
		 *
		 * @param fieldName The name of the field.
		 */
		private DisabledFieldGetter(String fieldName){
			this.fieldName = fieldName;
		}

		@Override
		public String getFieldName(){
			return fieldName;
		}

		@Override
		public boolean mustGet(String line){
			return false;
		}

		@Override
		public String getValue(String line){
			return null;
		}

		@Override
		public String performOperations(String line, ScannerPossessor analyzer){
			return null;
		}
	}
}
