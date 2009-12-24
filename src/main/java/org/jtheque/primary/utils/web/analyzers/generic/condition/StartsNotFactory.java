package org.jtheque.primary.utils.web.analyzers.generic.condition;

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
final class StartsNotFactory implements Factory<Condition> {
    @Override
    public boolean canFactor(Element element, XMLReader reader) throws XMLException {
        return "notstarts".equals(element.getName());
    }

    @Override
    public Condition factor(Element element, XMLReader reader) {
        return new StartsNot(element.getText());
    }

    /**
     * A starts not condition. It seems a condition who test if the line starts not with a certain text.
     *
     * @author Baptiste Wicht
     */
    private static final class StartsNot implements Condition {
        private final String text;

        /**
         * Construct a new StartsNot.
         *
         * @param text The text the line must starts with to match the condition.
         */
        private StartsNot(String text) {
            super();

            this.text = text;
        }

        @Override
        public boolean match(String line) {
            return !line.startsWith(text);
        }
    }
}