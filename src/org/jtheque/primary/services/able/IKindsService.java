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
import org.jtheque.primary.od.able.Kind;

import java.util.Collection;

/**
 * A kinds service specification.
 *
 * @author Baptiste Wicht
 */
public interface IKindsService extends DataContainer<Kind> {
    String DATA_TYPE = "Kinds";

    /**
     * Return the default kind.
     *
     * @return The default kind.
     */
    Kind getDefaultKind();

    /**
     * Return the kinds.
     *
     * @return A List containing all the kinds.
     */
    Collection<Kind> getKinds();

    /**
     * Create the kind.
     *
     * @param kind The kind to create.
     */
    void create(Kind kind);

    /**
     * Delete the kind.
     *
     * @param kind The kind to delete.
     * @return true if the kind has been deleted.
     */
    boolean delete(Kind kind);

    /**
     * Save the kind.
     *
     * @param kind The kind to save.
     */
    void save(Kind kind);

    /**
     * Return the kind with the name.
     *
     * @param name The name to search form.
     * @return The searched kind.
     */
    Kind getKind(String name);

    /**
     * Indicate if exists a kind with the name.
     *
     * @param name The name of the kind.
     * @return true if the kind exists else false.
     */
    boolean exists(String name);

    /**
     * Test the kind exists or not.
     *
     * @param kind The kind to test.
     * @return <code>true</code> if the kind exists else <code>false</code>.
     */
    boolean exists(Kind kind);

    /**
     * Test if there is no kinds in the database.
     *
     * @return <code>true</code> if there is no kinds else <code>false</code>.
     */
    boolean hasNoKinds();

    /**
     * Create all the kinds.
     *
     * @param kinds The kinds to create.
     */
    void createAll(Iterable<Kind> kinds);

    /**
     * Return an empty kind.
     *
     * @return An empty kind.
     */
    Kind getEmptyKind();
}