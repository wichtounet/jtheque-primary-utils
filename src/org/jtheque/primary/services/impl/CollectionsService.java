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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.persistence.able.DataListener;
import org.jtheque.primary.PrimaryUtils;
import org.jtheque.primary.dao.able.IDaoCollections;
import org.jtheque.primary.od.able.Collection;
import org.jtheque.primary.services.able.ICollectionsService;
import org.jtheque.utils.StringUtils;
import org.jtheque.utils.io.FileUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * The implementation of the collections service.
 *
 * @author Baptiste Wicht
 */
public final class CollectionsService implements ICollectionsService {
    @Resource
    private IDaoCollections daoCollections;

    /**
     * Return an empty collection.
     *
     * @return An empty collection.
     */
    private Collection getEmptyCollection() {
        Collection emptyCollection = daoCollections.createCollection();

        emptyCollection.setTitle(Managers.getManager(ILanguageManager.class).getMessage("generic.view.actions.new"));
        emptyCollection.setProtection(false);
        emptyCollection.setPassword("");

        return emptyCollection;
    }

    /**
     * Indicate if a login is correct or not
     *
     * @param title    The title of the collection.
     * @param password The password to login to the collection.
     * @return true if the login is correct else false.
     */
    private boolean isLoginCorrect(String title, String password) {
        Collection collection = daoCollections.getCollection(title);

        if (collection == null) {
            return false;
        }

        if (collection.isProtection()) {
            String encrypted = FileUtils.encryptKey(password);

            if (!encrypted.equals(collection.getPassword())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Create a collection.
     *
     * @param title    The title of the collection.
     * @param password The password of the collection.
     */
    @Transactional
    private void createCollection(String title, String password) {
        Collection collection = getEmptyCollection();

        collection.setPrimaryImpl(PrimaryUtils.getPrimaryImpl());
        collection.setTitle(title);

        if (StringUtils.isEmpty(password)) {
            collection.setProtection(false);
        } else {
            collection.setProtection(true);
            collection.setPassword(FileUtils.encryptKey(password));
        }

        daoCollections.create(collection);
    }

    @Override
    public void createCollectionAndUse(String title, String password) {
        createCollection(title, password);
        daoCollections.setCurrentCollection(daoCollections.getCollection(title));
    }

    @Override
    public boolean login(String title, String password) {
        if (!isLoginCorrect(title, password)) {
            return false;
        }

        daoCollections.setCurrentCollection(daoCollections.getCollection(title));

        return true;
    }

    @Override
    public java.util.Collection<Collection> getDatas() {
        return daoCollections.getCollections();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoCollections.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoCollections.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}