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

import org.jtheque.primary.utils.web.analyzers.generic.condition.Condition;
import org.jtheque.primary.utils.web.analyzers.generic.condition.ConditionUtils;
import org.jtheque.primary.utils.web.analyzers.generic.operation.Operation;
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.transform.Transformer;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetter;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetterFactory;
import org.jtheque.xml.utils.XMLReader;
import org.jtheque.xml.utils.XMLException;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Baptiste Wicht
 */
final class SimpleFieldGetterFactory extends AbstractFieldGetterFactory {
    @Override
    public boolean canFactor(Node element, XMLReader<Node> reader) {
        return "getter".equals(element.getNodeName());
    }

    @Override
    public FieldGetter factor(Node element, XMLReader<Node> reader) throws XMLException {
        SimpleFieldGetter getter = new SimpleFieldGetter();

        getter.setFieldName(reader.readString("@field", element));
        getter.setLineCondition(ConditionUtils.getCondition(element, "condition", reader));
        getter.setValueGetter(ValueGetterFactory.getValueGetter(element, reader));
        initTransformers(getter, element, reader);
        initOperations(getter, element, reader);

        return getter;
    }

    /**
     * A Field Getter. It's an object who's responsible to get the value of a field of a film in the site.
     *
     * @author Baptiste Wicht
     */
    static class SimpleFieldGetter implements FieldGetter {
        private String fieldName;
        private Condition lineCondition;
        private ValueGetter valueGetter;
        private final Collection<Transformer> transformers;
        private final Collection<Operation> operations;

        /**
         * Construct a new FieldGetter.
         */
        SimpleFieldGetter() {
            super();

            transformers = new ArrayList<Transformer>(5);
            operations = new ArrayList<Operation>(5);
        }

        /**
         * Set the name of the field for which the getter is made.
         *
         * @param fieldName The name of the field.
         */
        public final void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public final String getFieldName() {
            return fieldName;
        }

        /**
         * Set the condition to test if the getter must analyze the line or not.
         *
         * @param lineCondition The condition.
         */
        public final void setLineCondition(Condition lineCondition) {
            this.lineCondition = lineCondition;
        }

        /**
         * Set the value getter of the field getter. This object is responsible to get the value of the field if the
         * condition match the line.
         *
         * @param valueGetter The value getter.
         */
        public final void setValueGetter(ValueGetter valueGetter) {
            this.valueGetter = valueGetter;
        }

        /**
         * Return the value getter of the field getter.
         *
         * @return The value getter.
         */
        final ValueGetter getValueGetter() {
            return valueGetter;
        }

        @Override
        public boolean mustGet(String line) {
            return lineCondition.match(line);
        }

        @Override
        public String getValue(String line) {
            String value = valueGetter.getValue(line);

            if (!transformers.isEmpty()) {
                for (Transformer transformer : transformers) {
                    value = transformer.transform(value);
                }
            }

            return value;
        }

        /**
         * Add a transformer to the getter.
         *
         * @param transformer The transformer to add.
         */
        public final void addTransformer(Transformer transformer) {
            transformers.add(transformer);
        }

        /**
         * Add an operation to the getter.
         *
         * @param operation The operation to add.
         */
        public final void addOperation(Operation operation) {
            operations.add(operation);
        }

        @Override
        public String performOperations(String line, ScannerPossessor analyzer) {
            String performed = line;

            for (Operation operation : operations) {
                performed = operation.perform(performed, analyzer);
            }

            return performed;
        }

        @Override
        public String toString() {
            return "SimpleFieldGetter{" +
                    "fieldName='" + fieldName + '\'' +
                    ", lineCondition=" + lineCondition +
                    ", valueGetter=" + valueGetter +
                    ", transformers=" + transformers +
                    ", operations=" + operations +
                    '}';
        }

        /**
         * Return all the transformers of the field getter.
         *
         * @return A collection of all the transformers of the getter.
         */
        public final Collection<Transformer> getTransformers() {
            return transformers;
        }
    }
}
