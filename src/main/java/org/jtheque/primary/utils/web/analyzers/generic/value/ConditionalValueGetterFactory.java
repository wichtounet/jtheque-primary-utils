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
import org.jtheque.primary.utils.web.analyzers.generic.condition.Condition;
import org.jtheque.primary.utils.web.analyzers.generic.condition.ConditionUtils;
import org.jtheque.xml.utils.XMLException;
import org.jtheque.xml.utils.XMLReader;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Baptiste Wicht
 */
final class ConditionalValueGetterFactory implements Factory<ValueGetter> {
	@Override
	public boolean canFactor(Element element, XMLReader reader) throws XMLException {
		return !reader.getNodes("conditional", element).isEmpty();
	}

	@Override
	public ValueGetter factor(Element element, XMLReader reader) throws XMLException{
		Element n = reader.getNode("conditional", element);

		ConditionalValueGetter conditionalGetter = new ConditionalValueGetter();

		conditionalGetter.addValue(getIf(reader.getNode("if", n), reader));

		for (Element elseIfNode : reader.getNodes("elseif", n)){
			conditionalGetter.addValue(getIf(elseIfNode, reader));
		}

		Element elseNode = reader.getNode("else", n);

		if (elseNode != null){
			conditionalGetter.addValue(getElse(elseNode, reader));
		}

		return conditionalGetter;
	}

	/**
	 * Return an Else object in a specific node.
	 *
	 * @param node The nod to search in.
	 * @param reader The XML reader.
	 *
	 * @return The else object.
	 *
	 * @throws XMLException If an errors occurs during the parse of the XML Elements.
	 */
	private static ConditionalValue getElse(Element node, XMLReader reader) throws XMLException{
		Else elseCondition = new Else();

		elseCondition.setGetter(ValueGetterFactory.getValueGetter(node, reader));

		return elseCondition;
	}

	/**
	 * Return an If object in a specific node.
	 *
	 * @param node The nod to search in.
	 * @param reader The XML reader.
	 *
	 * @return The
	 *
	 * @throws XMLException If an errors occurs during the parse of the XML Elements.
	 */
	private static ConditionalValue getIf(Element node, XMLReader reader) throws XMLException{
		If ifCondition = new If();

		ifCondition.setCondition(ConditionUtils.getCondition(node, "condition", reader));
		ifCondition.setGetter(ValueGetterFactory.getValueGetter(node, reader));

		return ifCondition;
	}

	/**
	 * A conditional value getter.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class ConditionalValueGetter implements ValueGetter {
		private final Collection<ConditionalValue> values;

		/**
		 * Construct a new ConditionalValueGetter.
		 */
		private ConditionalValueGetter(){
			super();

			values = new ArrayList<ConditionalValue>(5);
		}

		/**
		 * Add a conditional value to the getter.
		 *
		 * @param value The conditional value to add.
		 */
		public void addValue(ConditionalValue value){
			values.add(value);
		}

		@Override
		public String getValue(String line){
			String value = null;

			for (ConditionalValue v : values){
				if (v.match(line)){
					value = v.getValue(line);
					break;
				}
			}

			return value;
		}
	}

	/**
	 * A else conditional value.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class Else implements ConditionalValue {
		private ValueGetter getter;

		/**
		 * Set the value getter.
		 *
		 * @param getter The value getter.
		 */
		private void setGetter(ValueGetter getter){
			this.getter = getter;
		}

		@Override
		public String getValue(String line){
			return getter.getValue(line);
		}

		@Override
		public boolean match(String line){
			return true;
		}
	}

	/**
	 * A if conditional value.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class If implements ConditionalValue {
		private Condition condition;
		private ValueGetter getter;

		@Override
		public String getValue(String line){
			return getter.getValue(line);
		}

		@Override
		public boolean match(String line){
			return condition.match(line);
		}

		/**
		 * Set the condition of the if.
		 *
		 * @param condition The condition.
		 */
		public void setCondition(Condition condition){
			this.condition = condition;
		}

		/**
		 * Set the value getter of the if.
		 *
		 * @param getter The value getter.
		 */
		public void setGetter(ValueGetter getter){
			this.getter = getter;
		}
	}
}
