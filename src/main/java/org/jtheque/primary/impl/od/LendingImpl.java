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

import org.jtheque.primary.able.od.Lending;
import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.impl.od.temp.LendingTemporaryContext;
import org.jtheque.utils.bean.EqualsUtils;
import org.jtheque.utils.bean.HashCodeUtils;
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
	public void setDate(IntDate date){
		this.date = date;
	}

	@Override
	public IntDate getDate(){
		return date;
	}

	@Override
	public void setThePerson(Person thePerson){
		this.thePerson = thePerson;
	}

	@Override
	public Person getThePerson(){
		return thePerson;
	}

	@Override
	public void setTheOther(int other){
		theOther = other;
	}

	@Override
	public int getTheOther(){
		return theOther;
	}

	//Utility methods

	@Override
	public LendingTemporaryContext getTemporaryContext(){
		return temporaryContext;
	}

	@Override
	public String getDisplayableText(){
		return "Lending of :  " + theOther;
	}

	@Override
	public int hashCode(){
		return HashCodeUtils.hashCodeDirect(getId(), date, thePerson, getPrimaryImpl(), theOther);
	}

	@Override
	public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

		Lending other = (Lending) obj;

		return EqualsUtils.areEqualsDirect(
				this, obj,
				getId(), date, theOther, getPrimaryImpl(),
				other.getId(), other.getDate(), other.getTheOther(), other.getPrimaryImpl());
	}
}