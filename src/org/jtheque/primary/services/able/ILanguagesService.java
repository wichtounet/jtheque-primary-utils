package org.jtheque.primary.services.able;

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.primary.od.able.Language;

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
 * A languages service specification.
 *
 * @author Baptiste Wicht
 */
public interface ILanguagesService extends DataContainer<Language> {
    String DATA_TYPE = "Languages";

    /**
     * Return the default language.
     *
     * @return The default language.
     */
    Language getDefaultLanguage();

    /**
     * Create the language.
     *
     * @param language The language to create.
     */
    void create(Language language);

    /**
     * Delete the language.
     *
     * @param language The language to delete.
     * @return true if the language has been deleted.
     */
    boolean delete(Language language);

    /**
     * Save the language.
     *
     * @param language The language.
     */
    void save(Language language);

    /**
     * Return the language.
     *
     * @param name The name of the language.
     * @return The searched language.
     */
    Language getLanguage(String name);

    /**
     * Indicate if the language exist.
     *
     * @param language The language to test if exist.
     * @return true if the language exist else false.
     */
    boolean exist(Language language);

    /**
     * Create all the languages.
     *
     * @param languages The languages to create.
     */
    void createAll(Iterable<Language> languages);

    /**
     * Return all the languages.
     *
     * @return A List containing all the languages.
     */
    Collection<Language> getLanguages();

    /**
     * Return an empty <code>Language</code>.
     *
     * @return An empty language.
     */
    Language getEmptyLanguage();
}