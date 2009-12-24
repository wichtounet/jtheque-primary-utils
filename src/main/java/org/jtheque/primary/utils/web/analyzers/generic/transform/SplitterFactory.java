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
import org.jtheque.core.utils.file.XMLException;
import org.jtheque.core.utils.file.XMLReader;
import org.jtheque.primary.utils.web.analyzers.generic.Factory;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetter;
import org.jtheque.primary.utils.web.analyzers.generic.value.ValueGetterFactory;
import org.jtheque.utils.StringUtils;

/**
 * @author Baptiste Wicht
 */
final class SplitterFactory implements Factory<Transformer> {
    @Override
    public boolean canFactor(Element element, XMLReader reader) {
        return "splitter".equals(element.getName());
    }

    @Override
    public Transformer factor(Element n, XMLReader reader) throws XMLException {
        Splitter splitter = new Splitter();

        splitter.setSplitter(reader.readString("split", n));
        splitter.setSplitReplace(reader.readString("new", n));
        splitter.setGetter(ValueGetterFactory.getValueGetter(n, reader));

        return splitter;
    }

    /**
     * A Transformer who split the value and rebuild the value in a different String.
     *
     * @author Baptiste Wicht
     */
    private static final class Splitter implements Transformer {
        private String splitter;
        private String splitReplace;
        private ValueGetter getter;

        @Override
        public String transform(String value) {
            if (!StringUtils.isEmpty(value)) {
                StringBuilder builder = new StringBuilder(50);

                String[] temp = value.split(splitter);

                boolean first = true;

                for (String v : temp) {
                    if (StringUtils.isEmpty(v)) {
                        continue;
                    }

                    String real = getter.getValue(v);

                    if (first) {
                        first = false;
                    } else {
                        builder.append(splitReplace);
                    }

                    builder.append(real);
                }

                return builder.toString();
            }

            return value;
        }

        /**
         * Set the String with which we split the value.
         *
         * @param splitter The split string.
         */
        public void setSplitter(String splitter) {
            this.splitter = splitter;
        }

        /**
         * Set the new separator.
         *
         * @param splitReplace The new separator.
         */
        public void setSplitReplace(String splitReplace) {
            this.splitReplace = splitReplace;
        }

        /**
         * Set the ValueGetter to take the value.
         *
         * @param getter The value getter.
         */
        public void setGetter(ValueGetter getter) {
            this.getter = getter;
        }

        @Override
        public String toString() {
            return "Splitter{" +
                    "splitter='" + splitter + '\'' +
                    ", splitReplace='" + splitReplace + '\'' +
                    ", getter=" + getter +
                    '}';
        }
    }
}
