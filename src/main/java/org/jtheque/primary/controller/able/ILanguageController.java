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
import org.jtheque.primary.od.able.Language;

/**
 * A language controller specification.
 *
 * @author Baptiste Wicht
 */
public interface ILanguageController extends Controller {
    /**
     * Create a new language. Change the mode of the controller and open the view to add a new language.
     */
    void newLanguage();

    /**
     * Edit a language. Change the mode of the controller and open the view to edit a language.
     *
     * @param language The language to edit.
     */
    void editLanguage(Language language);

    /**
     * Save the current language with a new name.
     *
     * @param name The new name of the language.
     */
    void save(String name);

}
