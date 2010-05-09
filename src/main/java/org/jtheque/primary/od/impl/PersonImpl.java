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

import org.jtheque.core.utils.PropertiesUtils;
import org.jtheque.persistence.able.Note;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.od.impl.abstraction.AbstractPrimaryData;
import org.jtheque.primary.od.impl.temp.PersonTemporaryContext;
import org.jtheque.utils.bean.EqualsUtils;
import org.jtheque.utils.bean.HashCodeUtils;

/**
 * A borrower implementation.
 *
 * @author Baptiste Wicht
 */
public final class PersonImpl extends AbstractPrimaryData implements Person {
	private String name;
	private String firstName;
	private SimpleData theCountry;
	private Note note;
	private String type;
	private String email;

	private Person memento;
	private boolean mementoState;

	private final PersonTemporaryContext temporaryContext = new PersonTemporaryContext();

	//Data methods

	@Override
	public void setName(String name){
		this.name = name;
	}

	@Override
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	@Override
	public void setTheCountry(SimpleData country){
		theCountry = country;
	}

	@Override
	public SimpleData getTheCountry(){
		return theCountry;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public String getFirstName(){
		return firstName;
	}

	@Override
	public void setNote(Note note){
		this.note = note;
	}

	@Override
	public Note getNote(){
		return note;
	}

	@Override
	public String getType(){
		return type;
	}

	@Override
	public void setType(String type){
		this.type = type;
	}

	@Override
	public void setEmail(String email){
		this.email = email;
	}

	@Override
	public String getEmail(){
		return email;
	}

	//Utility methods

	@Override
	public PersonTemporaryContext getTemporaryContext(){
		return temporaryContext;
	}

	@Override
	public boolean hasCountry(){
		return theCountry != null;
	}

	@Override
	public String getDisplayableText(){
		return firstName + ' ' + name;
	}

	@Override
	public String toString(){
		return getDisplayableText();
	}

	@Override
	public int hashCode(){
		return HashCodeUtils.hashCodeDirect(this, getId(), name, note, theCountry, firstName, email, type);
	}

	@Override
	public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

		Person other = (Person) obj;

		return EqualsUtils.areEqualsDirect(
				this, obj,
				name, note, theCountry, firstName, email, type,
				other.getName(), other.getNote(), other.getTheCountry(), other.getFirstName(), other.getEmail(), other.getType());
	}

	@Override
	public void saveToMemento(){
		mementoState = true;

		memento = PropertiesUtils.createMemento(this);

		if (memento == null){
			mementoState = false;
		}
	}

	@Override
	public void restoreMemento(){
		if (mementoState){
			PropertiesUtils.restoreMemento(this, memento);
		}
	}
}