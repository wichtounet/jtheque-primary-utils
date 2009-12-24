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
import org.jtheque.primary.od.able.Language;

import java.util.Collection;

/**
 * A DAO for languages specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoLanguages extends JThequeDao {
    String TABLE = "T_LANGUAGES";

    /**
     * Return all the countries.
     *
     * @return A <code>Collection</code> containing all the languages.
     */
    Collection<Language> getLanguages();

    /**
     * Return the language of the specified id.
     *
     * @param id The searched id.
     * @return The language of the specified id or <code>null</code> if there is no language with this id.
     */
    Language getLanguage(int id);

    /**
     * Return the language of the specified title.
     *
     * @param title The searched title.
     * @return The language of the specified title or <code>null</code> if there is no language with this title.
     */
    Language getLanguage(String title);

    /**
     * Indicate if the language exist in the Dao.
     *
     * @param language The language we must test if it's exist.
     * @return <code>true</code> if the language exist else <code>false</code>.
     */
    boolean exist(Language language);

    /**
     * Create a new language.
     *
     * @param language The language to create
     */
    void create(Language language);

    /**
     * Save the language.
     *
     * @param language The language to save
     */
    void save(Language language);

    /**
     * Delete a language.
     *
     * @param language The language to delete
     * @return <code>true</code> if the object has been deleted else <code>false</code>.
     */
    boolean delete(Language language);

    /**
     * Create a new <code>Language</code>.
     *
     * @return The created <code>Language</code>.
     */
    Language createLanguage();
}