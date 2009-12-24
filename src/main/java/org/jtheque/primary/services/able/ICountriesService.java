package org.jtheque.primary.services.able;

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.primary.od.able.Country;

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
 * A countries service specification.
 *
 * @author Baptiste Wicht
 */
public interface ICountriesService extends DataContainer<Country> {
    String DATA_TYPE = "Countries";

    /**
     * Return the default country.
     *
     * @return The default country.
     */
    Country getDefaultCountry();

    /**
     * Save the country.
     *
     * @param country The country to save.
     */
    void save(Country country);

    /**
     * Create the country.
     *
     * @param country The country to create.
     */
    void create(Country country);

    /**
     * Delete the country.
     *
     * @param country The country to delete.
     * @return true if the country has been deleted else false.
     */
    boolean delete(Country country);

    /**
     * Return all the countries.
     *
     * @return A List containing all the countries.
     */
    Collection<Country> getCountries();

    /**
     * Return the country with the name.
     *
     * @param name The name of the searched country.
     * @return The country else null if the country doesn't exist.
     */
    Country getCountry(String name);

    /**
     * Create all the countries.
     *
     * @param countries The countries to create.
     */
    void createAll(Iterable<Country> countries);

    /**
     * Indicate if a country exist or not.
     *
     * @param country The country to test.
     * @return <code>true</code> if the country exist else <code>false</code>.
     */
    boolean exist(Country country);

    /**
     * Return an empty country.
     *
     * @return The empty country.
     */
    Country getEmptyCountry();

    /**
     * Indicate if a country with the specified exists.
     *
     * @param name The name of the country.
     * @return <code>true</code> if the country exists else <code>false</code>.
     */
    boolean exist(String name);
}