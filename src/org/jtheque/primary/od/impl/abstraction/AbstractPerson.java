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

import org.jtheque.core.utils.db.Note;
import org.jtheque.primary.od.able.Country;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.impl.temp.PersonTemporaryContext;

/**
 * A person.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractPerson extends AbstractData implements Person {
    private String name;
    private String firstName;
    private Country theCountry;
    private Note note;
    private String type;
    private String email;

    @Override
    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public final void setTheCountry(Country country) {
        theCountry = country;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getFirstName() {
        return firstName;
    }

    @Override
    public final Country getTheCountry() {
        return theCountry;
    }

    @Override
    public final void setNote(Note note) {
        this.note = note;
    }

    @Override
    public final Note getNote() {
        return note;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public final void setEmail(String email) {
        this.email = email;
    }

    @Override
    public final String getEmail() {
        return email;
    }

    @Override
    public abstract PersonTemporaryContext getTemporaryContext();
}