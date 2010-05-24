package org.jtheque.primary.able.dao;

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

import org.jtheque.persistence.able.Dao;
import org.jtheque.primary.able.od.Person;

import java.util.Collection;

/**
 * A DAO for persons specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoPersons extends Dao<Person> {
	String TABLE = "T_PERSONS";

	Collection<Person> getPersons();

	/**
	 * Return all the persons.
	 *
	 * @param type The type of persons to search for.
	 *
	 * @return All the persons.
	 */
	Collection<Person> getPersons(String type);

	/**
	 * Return the person of the specified id.
	 *
	 * @param id The person borrower id.
	 *
	 * @return The person corresponding the id or <code>null</code> if there is no person with this id.
	 */
	Person getPerson(int id);

	/**
	 * Return the person with the specified name and first name.
	 *
	 * @param firstName The searched first name.
	 * @param name The searched name.
	 * @param type The type of persons to search for.
	 *
	 * @return The person if there is no for this arguments else <code>null</code>.
	 */
	Person getPerson(String firstName, String name, String type);

	/**
	 * Indicate if a person with this firstName and this name exists in the application.
	 *
	 * @param firstName The searched first name.
	 * @param name The searched name.
	 * @param type The type of persons to search for.
	 *
	 * @return <code>true</code> if an person exist with first name and this name else <code>false</code>.
	 */
	boolean exists(String firstName, String name, String type);

	/**
	 * Clear all the entities with the specified type.
	 *
	 * @param type The type of entities to delete.
	 */
	void clearAll(String type);

    Person getPersonByTemporaryId(int id);
}