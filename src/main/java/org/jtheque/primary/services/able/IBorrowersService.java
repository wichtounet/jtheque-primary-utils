package org.jtheque.primary.services.able;

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.primary.od.able.Person;

import java.util.Collection;

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

/**
 * A borrowers service specification.
 *
 * @author Baptiste Wicht
 */
public interface IBorrowersService extends DataContainer<Person> {
    String DATA_TYPE = "Borrowers";
    String PERSON_TYPE = "Borrower";

    /**
     * Return an empty borrower.
     *
     * @return An empty borrower.
     */
    Person getEmptyBorrower();

    /**
     * Delete a borrower.
     *
     * @param borrower The borrower to delete.
     * @return true if the borrower has been deleted else false.
     */
    boolean delete(Person borrower);

    /**
     * Create a borrower.
     *
     * @param borrower The borrower to create.
     */
    void create(Person borrower);

    /**
     * Return the borrowers.
     *
     * @return A List containing all the borrowers.
     */
    Collection<Person> getBorrowers();

    /**
     * Save the borrower.
     *
     * @param borrower The borrower to save.
     */
    void save(Person borrower);

    /**
     * Indicate if there is no borrowers.
     *
     * @return true if there is no borrowers else false.
     */
    boolean hasNoBorrowers();

    /**
     * Create all the borrowers.
     *
     * @param borrowers The borrowers to create.
     */
    void createAll(Iterable<Person> borrowers);
}