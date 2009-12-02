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
import org.jtheque.primary.od.able.Type;

import java.util.Collection;

/**
 * A DAO for types specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoTypes extends JThequeDao {
    String TABLE = "T_TYPES";

    /**
     * Return all the types.
     *
     * @return A List containing all the types.
     */
    Collection<Type> getTypes();

    /**
     * Return the type with the specified ID.
     *
     * @param id The ID of the searched type.
     * @return The type.
     */
    Type getType(int id);

    /**
     * Return the type with the specified name.
     *
     * @param name The searched name.
     * @return The type.
     */
    Type getType(String name);

    /**
     * Save the type.
     *
     * @param type The type to save.
     */
    void save(Type type);

    /**
     * Create the type.
     *
     * @param type The type to create.
     */
    void create(Type type);

    /**
     * Delete the type.
     *
     * @param type The type to delete.
     * @return true if the object is deleted else false.
     */
    boolean delete(Type type);

    /**
     * Indicate if this type exists or not.
     *
     * @param type The type to test.
     * @return <code>true</code> if the type exists else <code>false</code>.
     */
    boolean exists(Type type);

    /**
     * Create a new <code>Type</code>.
     *
     * @return The create <code>Type</code>.
     */
    Type createType();
}