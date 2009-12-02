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
import org.jtheque.primary.od.able.Country;

import java.util.Collection;

/**
 * A DAO for countries specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoCountries extends JThequeDao {
    String TABLE = "T_COUNTRIES";

    /**
     * Return all the countries.
     *
     * @return A <code>Collection</code> containing all the countries.
     */
    Collection<Country> getCountries();

    /**
     * Return the country of the specified id.
     *
     * @param id The searched id.
     * @return The country of the specified id or <code>null</code> if there is no country with this id.
     */
    Country getCountry(int id);

    /**
     * Return the country of the specified title.
     *
     * @param title The searched title.
     * @return The country of the specified title or <code>null</code> if there is no country with this title.
     */
    Country getCountry(String title);

    /**
     * Indicate if the country exist in the Dao.
     *
     * @param country The country we must test if it's exist.
     * @return <code>true</code> if the country exist else <code>false</code>.
     */
    boolean exist(Country country);

    /**
     * Delete a country.
     *
     * @param country The country to delete
     * @return <code>true</code> if the object has been deleted else <code>false</code>.
     */
    boolean delete(Country country);

    /**
     * Create the country.
     *
     * @param country The country to create
     */
    void create(Country country);

    /**
     * Save the country on the database.
     *
     * @param country The country to save.
     */
    void save(Country country);

    /**
     * Create a new <code>Country</code>.
     *
     * @return The created <code>Country</code>.
     */
    Country createCountry();
}