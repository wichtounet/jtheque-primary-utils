package org.jtheque.primary.able.services;

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

import org.jtheque.persistence.able.DataContainer;
import org.jtheque.primary.able.od.Person;

import java.util.Collection;

/**
 * A service for the person functions.
 *
 * @author Baptiste Wicht
 */
public interface IPersonService extends DataContainer<Person>, DataService<Person> {
    /**
     * Return an empty person.
     *
     * @return An empty person.
     */
    Person getDefaultPerson();

    /**
     * Indicate if the service has nos persons.
     *
     * @return true if there is no persons else false.
     */
    boolean hasNoPerson();

    /**
     * Return all the persons.
     *
     * @return A List containing all the persons.
     */
    Collection<Person> getPersons();

    /**
     * Indicate if an person exists or not.
     *
     * @param person The person to test.
     * @return true if the person exists else false.
     */
    boolean exist(Person person);

    /**
     * Return the actor denoted by this first name and name.
     *
     * @param firstName The first name of the person.
     * @param name      The name of the person.
     * @return The person.
     */
    Person getPerson(String firstName, String name);

    /**
     * Indicate if a person with this first name and name exist or not.
     *
     * @param firstName The first name of the person.
     * @param name      The name of the person.
     * @return true if the person exists else false.
     */
    boolean exist(String firstName, String name);

    /**
     * Return an empty person.
     *
     * @return An empty person.
     */
    Person getEmptyPerson();

    /**
     * Create all the persons.
     *
     * @param persons The persons to create.
     */
    void createAll(Iterable<Person> persons);

    /**
     * Return the type of person managed by this service.
     *
     * @return The person type.
	 */
	String getPersonType();
}