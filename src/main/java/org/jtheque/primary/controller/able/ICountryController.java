package org.jtheque.primary.controller.able;

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

import org.jtheque.core.managers.view.able.controller.Controller;
import org.jtheque.primary.od.able.Country;

/**
 * A country controller specification.
 *
 * @author Baptiste Wicht
 */
public interface ICountryController extends Controller {
    /**
     * Create a new country. Change the state of the controller and display the view to create a new
     * country.
     */
    void newCountry();

    /**
     * Edit a country. Change the state of the controller and display the view with the country to edit.
     *
     * @param country The country to edit.
     */
    void editCountry(Country country);

    /**
     * Save the current country.
     *
     * @param name The new name of the country.
     */
    void save(String name);

}
