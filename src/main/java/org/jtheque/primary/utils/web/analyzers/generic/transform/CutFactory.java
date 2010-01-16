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

import org.jdom.Element;
import org.jtheque.core.utils.file.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;

/**
 * @author Baptiste Wicht
 */
final class CutFactory implements Factory<Transformer> {
	@Override
	public boolean canFactor(Element element, XMLReader reader){
		return "cut".equals(element.getName());
	}

	@Override
	public Transformer factor(Element element, XMLReader reader){
		return new Cut(Integer.parseInt(element.getText()));
	}

	/**
	 * A Transformer who take only the X first chars.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class Cut implements Transformer {
		private final int cut;

		/**
		 * Construct a new Cut object.
		 *
		 * @param cut The number of characters to take.
		 */
		private Cut(int cut){
			super();

			this.cut = cut;
		}

		@Override
		public String transform(String value){
			return value.substring(0, value.length() - cut);
		}

		@Override
		public String toString(){
			return "Cut{" +
					"cut=" + cut +
					'}';
		}
	}
}
