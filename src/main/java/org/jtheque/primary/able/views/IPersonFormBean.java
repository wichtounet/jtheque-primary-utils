package org.jtheque.primary.able.views;

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

import org.jtheque.persistence.able.Note;
import org.jtheque.primary.able.controller.FormBean;
import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.able.od.SimpleData;

/**
 * @author Baptiste Wicht
 */
public interface IPersonFormBean extends FormBean {
	/**
	 * Set the name of the author.
	 *
	 * @param name The name of the author.
	 */
	void setName(String name);

	/**
	 * Set the firstName of the author.
	 *
	 * @param firstName The first name of the author.
	 */
	void setFirstName(String firstName);

	/**
	 * Set the note of the realizer.
	 *
	 * @param note The note to set.
	 */
	void setNote(Note note);

	/**
	 * Set the country of the realizer.
	 *
	 * @param country The country to set.
	 */
	void setCountry(SimpleData country);

	/**
	 * Fill the specified author with the informations of the form bean.
	 *
	 * @param author The author object to fill.
	 */
	void fillPerson(Person author);
}