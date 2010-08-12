package org.jtheque.primary.impl.services;

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

import org.jtheque.persistence.DataListener;
import org.jtheque.primary.able.dao.IDaoLendings;
import org.jtheque.primary.able.od.Lending;
import org.jtheque.primary.able.services.ILendingsService;
import org.jtheque.utils.bean.IntDate;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Collection;

/**
 * A lendings service implementation.
 *
 * @author Baptiste Wicht
 */
public final class LendingsService implements ILendingsService {
    @Resource
    private IDaoLendings daoLendings;

    @Override
    public Lending getLending(int id) {
        return daoLendings.getLending(id);
    }

    @Override
    public boolean hasNoLendings() {
        return daoLendings.getLendings().isEmpty();
    }

    @Override
    public Collection<Lending> getLendings() {
        return daoLendings.getLendings();
    }

    @Override
    @Transactional
    public void save(Lending lending) {
        daoLendings.save(lending);
    }

    @Override
    public Collection<Lending> getDatas() {
        return daoLendings.getLendings();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoLendings.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoLendings.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }

    @Override
    @Transactional
    public boolean delete(Lending lending) {
        return daoLendings.delete(lending);
    }

    @Override
    @Transactional
    public void create(Lending lending) {
        daoLendings.save(lending);
    }

    @Override
    public boolean isLate(Lending lending, int days) {
        IntDate date = lending.getDate();

        date.add(IntDate.Fields.DAY, days);

        return date.intValue() > IntDate.today().intValue();
    }

    @Override
    public Lending getEmptyLending() {
        return daoLendings.create();
    }
}