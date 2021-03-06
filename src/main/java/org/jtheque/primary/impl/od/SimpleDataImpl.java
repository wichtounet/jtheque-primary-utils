package org.jtheque.primary.impl.od;

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

import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.utils.AbstractData;
import org.jtheque.utils.bean.EqualsUtils;
import org.jtheque.utils.bean.HashCodeUtils;

/**
 * A simple data implementation
 *
 * @author Baptiste Wicht
 */
public class SimpleDataImpl extends AbstractData implements SimpleData {
    private String name;
    private final DataType type;

    /**
     * Construct a new SimpleDataImpl .
     *
     * @param type The data type.
     */
    public SimpleDataImpl(DataType type) {
        super();

        this.type = type;
    }

    /**
     * Construct a new SimpleDataImpl.
     *
     * @param name The name.
     * @param type The data type.
     */
    public SimpleDataImpl(String name, DataType type) {
        this(type);

        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public final DataType getType() {
        return type;
    }

    @Override
    public final String getDisplayableText() {
        return name;
    }

    @Override
    public final String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return HashCodeUtils.hashCodeDirect(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        SimpleData other = (SimpleData) obj;

        return EqualsUtils.areEqualsDirect(this, obj,
                name,
                other.getName());
    }
}