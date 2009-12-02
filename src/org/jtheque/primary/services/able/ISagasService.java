package org.jtheque.primary.services.able;

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.primary.od.able.Saga;

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
 * A sagas service specification.
 *
 * @author Baptiste Wicht
 */
public interface ISagasService extends DataContainer<Saga> {
    String DATA_TYPE = "Sagas";

    /**
     * Create a saga.
     *
     * @param saga The saga to create.
     */
    void create(Saga saga);

    /**
     * Save the saga.
     *
     * @param saga The saga to save.
     */
    void save(Saga saga);

    /**
     * Delete a saga.
     *
     * @param saga The saga to delete.
     * @return true if the saga has been deleted.
     */
    boolean delete(Saga saga);

    /**
     * Return an empty saga.
     *
     * @return An empty saga.
     */
    Saga getEmptySaga();
}