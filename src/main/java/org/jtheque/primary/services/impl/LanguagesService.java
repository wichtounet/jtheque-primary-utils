package org.jtheque.primary.services.impl;

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

import org.jtheque.core.managers.persistence.able.DataListener;
import org.jtheque.primary.dao.able.IDaoLanguages;
import org.jtheque.primary.od.able.Language;
import org.jtheque.primary.services.able.ILanguagesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * A languages service implementation.
 *
 * @author Baptiste Wicht
 */
@Service
public final class LanguagesService implements ILanguagesService {
    @Resource
    private IDaoLanguages daoLanguages;

    /* Variables */
    private Language defaultLanguage;

    @Override
    public Language getDefaultLanguage() {
        if (defaultLanguage == null) {
            defaultLanguage = daoLanguages.getLanguage("Français");

            if (defaultLanguage == null) {
                createDefaultLanguage();
            }
        }

        return defaultLanguage;
    }

    /**
     * Create the default language.
     */
    @Transactional
    private void createDefaultLanguage() {
        defaultLanguage = getEmptyLanguage();

        defaultLanguage.setName("Français");
        daoLanguages.create(defaultLanguage);
    }

    @Override
    public Collection<Language> getDatas() {
        return daoLanguages.getLanguages();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoLanguages.addDataListener(listener);
    }

    @Override
    @Transactional
    public void create(Language language) {
        daoLanguages.create(language);
    }

    @Override
    @Transactional
    public boolean delete(Language language) {
        return daoLanguages.delete(language);
    }

    @Override
    @Transactional
    public void save(Language language) {
        daoLanguages.save(language);
    }

    @Override
    public Language getLanguage(String name) {
        return daoLanguages.getLanguage(name);
    }

    @Override
    public boolean exist(Language language) {
        return daoLanguages.exist(language);
    }

    @Override
    @Transactional
    public void createAll(Iterable<Language> languages) {
        for (Language language : languages) {
            daoLanguages.create(language);
        }
    }

    @Override
    public Collection<Language> getLanguages() {
        return daoLanguages.getLanguages();
    }

    @Override
    public Language getEmptyLanguage() {
        return daoLanguages.createLanguage();
    }

    @Override
    @Transactional
    public void clearAll() {
        daoLanguages.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}