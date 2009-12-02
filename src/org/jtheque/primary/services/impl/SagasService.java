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
import org.jtheque.primary.dao.able.IDaoSagas;
import org.jtheque.primary.od.able.Saga;
import org.jtheque.primary.services.able.ISagasService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * A sagas service implementation.
 *
 * @author Baptiste Wicht
 */
public final class SagasService implements ISagasService {
    @Resource
    private IDaoSagas daoSagas;

    @Override
    @Transactional
    public void create(Saga saga) {
        daoSagas.create(saga);
    }

    @Override
    @Transactional
    public void save(Saga saga) {
        daoSagas.save(saga);
    }

    @Override
    @Transactional
    public boolean delete(Saga saga) {
        return daoSagas.delete(saga);
    }

    @Override
    public Saga getEmptySaga() {
        return daoSagas.createSaga();
    }

    @Override
    public Collection<Saga> getDatas() {
        return daoSagas.getSagas();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoSagas.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoSagas.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}