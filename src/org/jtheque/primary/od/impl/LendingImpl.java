package org.jtheque.primary.od.impl;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.properties.IPropertiesManager;
import org.jtheque.primary.od.impl.abstraction.AbstractLending;
import org.jtheque.primary.od.impl.temp.LendingTemporaryContext;
import org.jtheque.utils.bean.HashCodeUtils;

/**
 * A lending.
 *
 * @author Baptiste Wicht
 */
public final class LendingImpl extends AbstractLending {
    private final LendingTemporaryContext temporaryContext = new LendingTemporaryContext();

    @Override
    public LendingTemporaryContext getTemporaryContext() {
        return temporaryContext;
    }

    @Override
    public String getDisplayableText() {
        return "Lending of :  " + getTheOther();
    }

    @Override
    public int hashCode() {
        return HashCodeUtils.hashCode(this, "id", "date", "thePerson", "primaryImpl", "theOther");
    }

    @Override
    public boolean equals(Object obj) {
        return Managers.getManager(IPropertiesManager.class).areEquals(
                this, obj,
                "id", "date", "theOther", "primaryImpl", "theBorrower");
    }
}