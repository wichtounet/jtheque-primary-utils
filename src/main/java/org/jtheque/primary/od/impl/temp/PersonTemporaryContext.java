package org.jtheque.primary.od.impl.temp;

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

import org.jtheque.core.managers.persistence.context.TemporaryContext;

/**
 * Temporary context of person.
 *
 * @author Baptiste Wicht
 */
public final class PersonTemporaryContext extends TemporaryContext {
	private int country;
	private int intNote;

	/**
	 * Return the note.
	 *
	 * @return The note.
	 */
	public int getIntNote(){
		return intNote;
	}

	/**
	 * Sets the note.
	 *
	 * @param intNote The note.
	 */
	public void setIntNote(int intNote){
		this.intNote = intNote;
	}

	/**
	 * Return the country id.
	 *
	 * @return The country id.
	 */
	public int getCountry(){
		return country;
	}

	/**
	 * Sets the temporary country id.
	 *
	 * @param country The country id.
	 */
	public void setCountry(int country){
		this.country = country;
	}
}