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

import org.jtheque.primary.od.able.Lending;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.impl.abstraction.AbstractPrimaryData;
import org.jtheque.primary.od.impl.temp.LendingTemporaryContext;
import org.jtheque.primary.utils.TempUtils;
import org.jtheque.utils.bean.IntDate;

/**
 * A lending.
 *
 * @author Baptiste Wicht
 */
public final class LendingImpl extends AbstractPrimaryData implements Lending {
    private IntDate date;
    private Person thePerson;
    private int theOther;

    private final LendingTemporaryContext temporaryContext = new LendingTemporaryContext();

    //Data methods

    @Override
    public void setDate(IntDate date) {
        this.date = date;
    }

    @Override
    public IntDate getDate() {
        return date;
    }

    @Override
    public void setThePerson(Person thePerson) {
        this.thePerson = thePerson;
    }

    @Override
    public Person getThePerson() {
        return thePerson;
    }

    @Override
    public void setTheOther(int other) {
        theOther = other;
    }

    @Override
    public int getTheOther() {
        return theOther;
    }

    //Utility methods

    @Override
    public LendingTemporaryContext getTemporaryContext() {
        return temporaryContext;
    }

    @Override
    public String getDisplayableText() {
        return "Lending of :  " + theOther;
    }

    @Override
    public int hashCode() {
        return TempUtils.hashCodeDirect(getId(), date, thePerson, getPrimaryImpl(), theOther);
    }

    @Override
    public boolean equals(Object obj) {
        Lending other = (Lending)obj;

        return TempUtils.areEqualsDirect(
                this, obj,
                getId(), date, theOther, getPrimaryImpl(),
                other.getId(), other.getDate(), other.getTheOther(), other.getPrimaryImpl());
    }
}