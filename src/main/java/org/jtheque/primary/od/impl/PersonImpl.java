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
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.impl.abstraction.AbstractPerson;
import org.jtheque.primary.od.impl.temp.PersonTemporaryContext;
import org.jtheque.utils.bean.HashCodeUtils;

/**
 * A borrower implementation.
 *
 * @author Baptiste Wicht
 */
public final class PersonImpl extends AbstractPerson {
    private Person memento;
    private boolean mementoState;

    private final PersonTemporaryContext temporaryContext = new PersonTemporaryContext();

    @Override
    public PersonTemporaryContext getTemporaryContext() {
        return temporaryContext;
    }

    @Override
    public boolean hasCountry() {
        return getTheCountry() != null;
    }

    @Override
    public String getDisplayableText() {
        return getFirstName() + ' ' + getName();
    }

    @Override
    public String toString() {
        return getDisplayableText();
    }

    @Override
    public int hashCode() {
        return HashCodeUtils.hashCode(this, "id", "name", "note", "theCountry", "firstName", "email", "type");
    }

    @Override
    public boolean equals(Object obj) {
        return Managers.getManager(IPropertiesManager.class).areEquals(
                this, obj,
                "name", "note", "theCountry", "firstName", "email", "type");
    }

    @Override
    public void saveToMemento() {
        mementoState = true;

        memento = Managers.getManager(IPropertiesManager.class).createMemento(this);

        if (memento == null) {
            mementoState = false;
        }
    }

    @Override
    public void restoreMemento() {
        if (mementoState) {
            Managers.getManager(IPropertiesManager.class).restoreMemento(this, memento);
        }
    }
}