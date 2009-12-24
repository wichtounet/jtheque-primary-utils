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
import org.jtheque.primary.od.able.Type;

import java.util.Collection;

/**
 * A service for the types functions.
 *
 * @author Baptiste Wicht
 */
public interface ITypesService extends DataContainer<Type> {
    String DATA_TYPE = "Types";

    /**
     * Return the default type.
     *
     * @return The default type
     */
    Type getDefaultType();

    /**
     * Return all the types.
     *
     * @return A List containing all the types.
     */
    Collection<Type> getTypes();

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
     * @return true if the type has been deleted.
     */
    boolean delete(Type type);

    /**
     * Save the type.
     *
     * @param type The type to save.
     */
    void save(Type type);

    /**
     * Tests if the type exists or not.
     *
     * @param type The type to test.
     * @return <code>true</code> if the type exists else <code>false</code>.
     */
    boolean exists(Type type);

    /**
     * Return the type with the specified name.
     *
     * @param name The searched name.
     * @return The type with the specified name if there is one else <code>null</code>.
     */
    Type getType(String name);

    /**
     * Indicate if there is no types in the database.
     *
     * @return <code>true</code> if there is no type else <code>false</code>.
     */
    boolean hasNoTypes();

    /**
     * Create all the types.
     *
     * @param types The types to creates.
     */
    void createAll(Iterable<Type> types);

    /**
     * Return an empty type.
     *
     * @return An empty type.
     */
    Type getEmptyType();
}