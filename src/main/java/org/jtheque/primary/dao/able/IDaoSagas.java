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
import org.jtheque.primary.od.able.Saga;

import java.util.Collection;

/**
 * A DAO for sagas specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoSagas extends JThequeDao {
    String TABLE = "T_SAGAS";

    /**
     * Return all the sagas.
     *
     * @return A List containing all the sagas.
     */
    Collection<Saga> getSagas();

    /**
     * Return the saga with the specified ID.
     *
     * @param id The ID of the searched saga.
     * @return The saga.
     */
    Saga getSaga(int id);

    /**
     * Save the saga.
     *
     * @param saga The saga to save.
     */
    void save(Saga saga);

    /**
     * Create the saga.
     *
     * @param saga The saga to create.
     */
    void create(Saga saga);

    /**
     * Delete the saga.
     *
     * @param saga The saga to delete.
     * @return true if the object is deleted else false.
     */
    boolean delete(Saga saga);

    /**
     * Create a saga.
     *
     * @return The created saga.
     */
    Saga createSaga();
}