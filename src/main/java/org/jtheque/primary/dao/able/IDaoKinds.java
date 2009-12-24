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
import org.jtheque.primary.od.able.Kind;

import java.util.Collection;

/**
 * A DAO for kinds specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoKinds extends JThequeDao {
    String TABLE = "T_KINDS";

    /**
     * Return all the kinds.
     *
     * @return A List containing all the kinds.
     */
    Collection<Kind> getKinds();

    /**
     * Return the kind with the specified ID.
     *
     * @param id The ID of the searched kind.
     * @return The kind.
     */
    Kind getKind(int id);

    /**
     * Return the kind with the specified name.
     *
     * @param name The searched name.
     * @return The kind.
     */
    Kind getKind(String name);

    /**
     * Indicate if a kind with this name exists in the application.
     *
     * @param name The searched name.
     * @return true if a kind exist with this name.
     */
    boolean exists(String name);

    /**
     * Save the kind.
     *
     * @param kind The kind to save.
     */
    void save(Kind kind);

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
     * @return true if the object is deleted else false.
     */
    boolean delete(Kind kind);

    /**
     * Create a new <code>Kind</code>.
     *
     * @return The created <code>Kind</code>.
     */
    Kind createKind();
}