package org.jtheque.primary.services.able;

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

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.primary.od.able.Collection;

/**
 * A service for the collections functions.
 *
 * @author Baptiste Wicht
 */
public interface ICollectionsService extends DataContainer<Collection> {
    String DATA_TYPE = "Collections";

    /**
     * Create the collection and directly set it as the current collection.
     *
     * @param collection The collection.
     * @param password   The password.
     */
    void createCollectionAndUse(String collection, String password);

    /**
     * Login using the specified collection and password.
     *
     * @param collection The collection.
     * @param password   The password.
     * @return <code>true</code> if the login is correct else <code>false</code>.
     */
    boolean login(String collection, String password);
}