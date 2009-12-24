package org.jtheque.primary.dao.able;

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

import org.jtheque.core.managers.persistence.able.JThequeDao;
import org.jtheque.primary.od.able.Lending;

import java.util.Collection;

/**
 * A DAO for types specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoLendings extends JThequeDao {
    String TABLE = "T_LENDINGS";

    /**
     * Return the lending with the specified ID.
     *
     * @param id The ID of the searched lending.
     * @return The lending.
     */
    Lending getLending(int id);

    /**
     * Create the lending.
     *
     * @param lending The lending to create.
     */
    void create(Lending lending);

    /**
     * Delete the lending.
     *
     * @param lending The lending to delete.
     * @return true if the object is deleted else false.
     */
    boolean delete(Lending lending);

    /**
     * Return all the lendings.
     *
     * @return All the lendings.
     */
    Collection<Lending> getLendings();

    /**
     * Save the specified lending.
     *
     * @param lending The lending to save.
     */
    void save(Lending lending);

    /**
     * Create the lending.
     *
     * @return The created lending.
     */
    Lending createLending();
}