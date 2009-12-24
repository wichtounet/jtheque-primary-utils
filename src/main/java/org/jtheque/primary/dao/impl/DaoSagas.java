package org.jtheque.primary.dao.impl;

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

import org.jtheque.core.managers.persistence.GenericDao;
import org.jtheque.core.managers.persistence.Query;
import org.jtheque.core.managers.persistence.QueryMapper;
import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.core.managers.persistence.context.IDaoPersistenceContext;
import org.jtheque.primary.PrimaryUtils;
import org.jtheque.primary.dao.able.IDaoSagas;
import org.jtheque.primary.od.able.Saga;
import org.jtheque.primary.od.impl.SagaImpl;
import org.jtheque.utils.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A Data Access Object implementation for sagas.
 *
 * @author Baptiste Wicht
 */
public final class DaoSagas extends GenericDao<Saga> implements IDaoSagas {
    private final ParameterizedRowMapper<Saga> rowMapper = new SagaRowMapper();
    private final QueryMapper queryMapper = new SagaQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    /**
     * Construct a new DaoSagas.
     */
    public DaoSagas() {
        super(TABLE);
    }

    @Override
    public void create(Saga entity) {
        entity.setPrimaryImpl(PrimaryUtils.getPrimaryImpl());

        super.create(entity);
    }

    @Override
    public Saga createSaga() {
        return new SagaImpl();
    }

    @Override
    public Collection<Saga> getSagas() {
        return getSagas(PrimaryUtils.getPrimaryImpl());
    }

    /**
     * Return all the sagas of the specific primary implementation.
     *
     * @param impl The primary implementation.
     * @return A Collection containing all the sagas of the specific primary implementation.
     */
    private Collection<Saga> getSagas(CharSequence impl) {
        if (StringUtils.isEmpty(impl)) {
            return getAll();
        }

        load();

        Collection<Saga> sagas = new ArrayList<Saga>(getCache().size() / 2);

        for (Saga saga : getCache().values()) {
            if (impl.equals(saga.getPrimaryImpl())) {
                sagas.add(saga);
            }
        }

        return sagas;
    }

    @Override
    public Saga getSaga(int id) {
        return get(id);
    }

    @Override
    protected ParameterizedRowMapper<Saga> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    @Override
    protected void loadCache() {
        Collection<Saga> sagas = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Saga saga : sagas) {
            getCache().put(saga.getId(), saga);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        Saga editor = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, editor);
    }

    /**
     * A mapper to map a resultset to a saga.
     *
     * @author Baptiste Wicht
     */
    private final class SagaRowMapper implements ParameterizedRowMapper<Saga> {
        @Override
        public Saga mapRow(ResultSet rs, int i) throws SQLException {
            Saga saga = createSaga();

            saga.setId(rs.getInt("ID"));
            saga.setName(rs.getString("NAME"));
            saga.setPrimaryImpl(rs.getString("IMPL"));

            return saga;
        }
    }

    /**
     * A mapper to map a saga to an SQL query.
     *
     * @author Baptiste Wicht
     */
    private static final class SagaQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Saga saga = (Saga) entity;

            String query = "INSERT INTO " + TABLE + " (NAME, IMPL) VALUES(?, ?)";

            Object[] parameters = {
                    saga.getName(),
                    saga.getPrimaryImpl()
            };

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Saga saga = (Saga) entity;

            String query = "UPDATE " + TABLE + " SET NAME = ?, IMPL = ? WHERE ID = ?";

            Object[] parameters = {
                    saga.getName(),
                    saga.getPrimaryImpl(),
                    saga.getId()};

            return new Query(query, parameters);
        }
    }
}