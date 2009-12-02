package org.jtheque.primary.od.impl.abstraction;

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
import org.jtheque.primary.od.impl.temp.LendingTemporaryContext;
import org.jtheque.utils.bean.IntDate;

/**
 * An abstract lending.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractLending extends AbstractPrimaryData implements Lending {
    private IntDate date;
    private Person thePerson;
    private int theOther;

    @Override
    public final void setDate(IntDate date) {
        this.date = date;
    }

    @Override
    public final IntDate getDate() {
        return date;
    }

    @Override
    public final void setThePerson(Person thePerson) {
        this.thePerson = thePerson;
    }

    @Override
    public final Person getThePerson() {
        return thePerson;
    }

    @Override
    public final void setTheOther(int other) {
        theOther = other;
    }

    @Override
    public final int getTheOther() {
        return theOther;
    }

    @Override
    public abstract LendingTemporaryContext getTemporaryContext();
}