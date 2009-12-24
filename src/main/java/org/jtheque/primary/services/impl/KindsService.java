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
import org.jtheque.primary.dao.able.IDaoKinds;
import org.jtheque.primary.od.able.Kind;
import org.jtheque.primary.services.able.IKindsService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * A kinds service implementation.
 *
 * @author Baptiste Wicht
 */
public final class KindsService implements IKindsService {
    private Kind defaultKind;

    @Resource
    private IDaoKinds daoKinds;

    @Override
    @Transactional
    public Kind getDefaultKind() {
        if (defaultKind == null) {
            defaultKind = daoKinds.getKind("Suspense");

            if (defaultKind == null) {
                defaultKind = getEmptyKind();
                defaultKind.setName("Suspense");
                daoKinds.create(defaultKind);
            }
        }

        return defaultKind;
    }

    @Override
    public Collection<Kind> getKinds() {
        return daoKinds.getKinds();
    }

    @Override
    @Transactional
    public void create(Kind kind) {
        daoKinds.create(kind);
    }

    @Override
    @Transactional
    public boolean delete(Kind kind) {
        return daoKinds.delete(kind);
    }

    @Override
    @Transactional
    public void save(Kind kind) {
        daoKinds.save(kind);
    }

    @Override
    public Kind getKind(String name) {
        return daoKinds.getKind(name);
    }

    @Override
    public boolean exists(String name) {
        return daoKinds.exists(name);
    }

    @Override
    public boolean exists(Kind kind) {
        return exists(kind.getName());
    }

    @Override
    public boolean hasNoKinds() {
        return daoKinds.getKinds().isEmpty();
    }

    @Override
    @Transactional
    public void createAll(Iterable<Kind> kinds) {
        for (Kind kind : kinds) {
            daoKinds.create(kind);
        }
    }

    @Override
    public Kind getEmptyKind() {
        return daoKinds.createKind();
    }

    @Override
    public Collection<Kind> getDatas() {
        return daoKinds.getKinds();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoKinds.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoKinds.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}