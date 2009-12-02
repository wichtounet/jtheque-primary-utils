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
import org.jtheque.primary.PrimaryUtils;
import org.jtheque.primary.dao.able.IDaoTypes;
import org.jtheque.primary.od.able.Type;
import org.jtheque.primary.services.able.ITypesService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * An types service implementation.
 *
 * @author Baptiste Wicht
 */
public final class TypesService implements ITypesService {
    @Resource
    private IDaoTypes daoTypes;

    private Type defaultType;

    @Override
    @Transactional
    public Type getDefaultType() {
        if (defaultType == null) {
            defaultType = daoTypes.getType("Base");

            if (defaultType == null) {
                defaultType = getEmptyType();
                defaultType.setName("Base");
                defaultType.setPrimaryImpl(PrimaryUtils.getPrimaryImpl());

                daoTypes.create(defaultType);
            }
        }

        return defaultType;
    }

    @Override
    public Collection<Type> getTypes() {
        return daoTypes.getTypes();
    }

    @Override
    @Transactional
    public void create(Type type) {
        daoTypes.create(type);
    }

    @Override
    @Transactional
    public boolean delete(Type type) {
        return daoTypes.delete(type);
    }

    @Override
    @Transactional
    public void save(Type type) {
        daoTypes.save(type);
    }

    @Override
    public boolean exists(Type type) {
        return daoTypes.exists(type);
    }

    @Override
    public Type getType(String name) {
        return daoTypes.getType(name);
    }

    @Override
    public boolean hasNoTypes() {
        return daoTypes.getTypes().isEmpty();
    }

    @Override
    @Transactional
    public void createAll(Iterable<Type> types) {
        for (Type type : types) {
            daoTypes.create(type);
        }
    }

    @Override
    public Type getEmptyType() {
        return daoTypes.createType();
    }

    @Override
    public Collection<Type> getDatas() {
        return daoTypes.getTypes();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoTypes.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoTypes.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}