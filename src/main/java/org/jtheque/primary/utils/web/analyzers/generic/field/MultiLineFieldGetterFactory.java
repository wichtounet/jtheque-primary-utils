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
import org.jtheque.io.XMLException;
import org.jtheque.io.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.condition.Condition;
import org.jtheque.primary.utils.web.analyzers.generic.condition.ConditionUtils;
import org.jtheque.primary.utils.web.analyzers.generic.field.SimpleFieldGetterFactory.SimpleFieldGetter;
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.transform.Transformer;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetterFactory;

/**
 * @author Baptiste Wicht
 */
final class MultiLineFieldGetterFactory extends AbstractFieldGetterFactory {
	@Override
	public boolean canFactor(Element element, XMLReader reader) throws XMLException {
		return "true".equals(reader.readString("@multiline", element));
	}

	@Override
	public FieldGetter factor(Element element, XMLReader reader) throws XMLException{
		MultiLineFieldGetter getter = new MultiLineFieldGetter();

		getter.setFieldName(reader.readString("@field", element));
		getter.setValueGetter(ValueGetterFactory.getValueGetter(element, reader));
		getter.setStartCondition(ConditionUtils.getCondition(element, "startcondition", reader));
		getter.setStopCondition(ConditionUtils.getCondition(element, "stopcondition", reader));
		getter.setGetCondition(ConditionUtils.getCondition(element, "getcondition", reader));
		initTransformers(getter, element, reader);
		initOperations(getter, element, reader);

		return getter;
	}

	/**
	 * A multi line field getter. It seems a field getter who get the value of the field on several
	 * different lines.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class MultiLineFieldGetter extends SimpleFieldGetter {
		private boolean parse;
		private boolean first = true;
		private boolean done;

		private Condition startCondition;
		private Condition stopCondition;
		private Condition getCondition;

		private final StringBuilder builder = new StringBuilder(100);

		@Override
		public boolean mustGet(String line){
			if (done){
				return false;
			}

			testStartCondition(line);
			testStopCondition(line);
			appendLineIfNecessary(line);

			return done;
		}

		/**
		 * Test the stop condition on the line.
		 *
		 * @param line The line to test.
		 */
		private void testStopCondition(String line){
			if (parse && stopCondition.match(line)){
				parse = false;
				done = true;
			}
		}

		/**
		 * Test the start condition on the line.
		 *
		 * @param line The line to test.
		 */
		private void testStartCondition(String line){
			if (startCondition.match(line)){
				parse = true;
			}
		}

		/**
		 * Append the line if necessary.
		 *
		 * @param line The line to test.
		 */
		private void appendLineIfNecessary(String line){
			if (parse && getCondition.match(line)){
				if (first){
					first = false;
				} else {
					builder.append("%%%");
				}

				builder.append(getValueGetter().getValue(line));
			}
		}

		@Override
		public String getValue(String line){
			if (done){
				done = false;
				parse = false;
				first = true;

				String value = builder.toString();

				if (!getTransformers().isEmpty()){
					for (Transformer transformer : getTransformers()){
						value = transformer.transform(value);
					}
				}

				return value;
			}

			return null;
		}

		@Override
		public String performOperations(String line, ScannerPossessor analyzer){
			return line;
		}

		/**
		 * Set the start condition.
		 *
		 * @param startCondition The condition who indicate when the parse must be started.
		 */
		public void setStartCondition(Condition startCondition){
			this.startCondition = startCondition;
		}

		/**
		 * Set the stop condition.
		 *
		 * @param stopCondition The condition who indicate when the parse must be stopped.
		 */
		public void setStopCondition(Condition stopCondition){
			this.stopCondition = stopCondition;
		}

		/**
		 * Set the get condition. This condition indicate to the getter when it must analyze the line
		 * to get a value.
		 *
		 * @param getCondition The condition who indicate when the parser must get a value.
		 */
		public void setGetCondition(Condition getCondition){
			this.getCondition = getCondition;
		}

		@Override
		public String toString(){
			return "MultiLineFieldGetter{" +
					"parse=" + parse +
					", first=" + first +
					", startCondition=" + startCondition +
					", stopCondition=" + stopCondition +
					", getCondition=" + getCondition +
					", builder=" + builder +
					'}';
		}
	}
}
