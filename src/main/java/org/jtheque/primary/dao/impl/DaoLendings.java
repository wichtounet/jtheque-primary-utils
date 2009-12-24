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
import org.jtheque.primary.dao.able.IDaoLendings;
import org.jtheque.primary.dao.able.IDaoPersons;
import org.jtheque.primary.od.able.Lending;
import org.jtheque.primary.od.impl.LendingImpl;
import org.jtheque.utils.StringUtils;
import org.jtheque.utils.bean.IntDate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A Data Access Object implementation for lendings.
 *
 * @author Baptiste Wicht
 */
public final class DaoLendings extends GenericDao<Lending> implements IDaoLendings {
    private final ParameterizedRowMapper<Lending> rowMapper = new LendingRowMapper();
    private final QueryMapper queryMapper = new LendingQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    @Resource
    private IDaoPersons daoPersons;

    /**
     * Construct a new DaoLendings.
     */
    public DaoLendings() {
        super(TABLE);
    }

    @Override
    public void create(Lending entity) {
        entity.setPrimaryImpl(PrimaryUtils.getPrimaryImpl());

        super.create(entity);
    }

    @Override
    public Lending getLending(int id) {
        return get(id);
    }

    @Override
    protected ParameterizedRowMapper<Lending> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    @Override
    protected void loadCache() {
        Collection<Lending> lendings = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Lending lending : lendings) {
            getCache().put(lending.getId(), lending);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    public Collection<Lending> getLendings() {
        return getLendings(PrimaryUtils.getPrimaryImpl());
    }

    /**
     * Return all the lendings of the specific primary implementation.
     *
     * @param impl The primary implementation.
     * @return A Collection containing all the lendings of the specific primary implementation.
     */
    private Collection<Lending> getLendings(CharSequence impl) {
        if (StringUtils.isEmpty(impl)) {
            return getAll();
        }

        load();

        Collection<Lending> lendings = new ArrayList<Lending>(getCache().size() / 2);

        for (Lending lending : getCache().values()) {
            if (impl.equals(lending.getPrimaryImpl())) {
                lendings.add(lending);
            }
        }

        return lendings;
    }

    @Override
    protected void load(int i) {
        Lending lending = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, lending);
    }

    @Override
    public Lending createLending() {
        return new LendingImpl();
    }

    /**
     * A mapper to map resultset to lending.
     *
     * @author Baptiste Wicht
     */
    private final class LendingRowMapper implements ParameterizedRowMapper<Lending> {
        @Override
        public Lending mapRow(ResultSet rs, int i) throws SQLException {
            Lending lending = createLending();

            lending.setId(rs.getInt("ID"));
            lending.setTheOther(rs.getInt("THE_OTHER_FK"));
            lending.setThePerson(daoPersons.getPerson(rs.getInt("THE_PERSON_FK")));
            lending.setDate(new IntDate(rs.getInt("DATE")));
            lending.setPrimaryImpl(rs.getString("IMPL"));

            return lending;
        }
    }

    /**
     * A mapper to map lending to query.
     *
     * @author Baptiste Wicht
     */
    private static final class LendingQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Lending lending = (Lending) entity;

            String query = "INSERT INTO " + TABLE + " (THE_OTHER_FK, THE_PERSON_FK, DATE, IMPL) VALUES(?,?,?, ?)";

            Object[] parameters = {
                    lending.getTheOther(),
                    lending.getThePerson().getId(),
                    lending.getDate().intValue(),
                    lending.getPrimaryImpl()
            };

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Lending lending = (Lending) entity;

            String query = "UPDATE " + TABLE + " SET THE_OTHER_FK = ?, THE_BORROWER_FK = ?, DATE = ?, IMPL = ? WHERE ID = ?";

            Object[] parameters = {
                    lending.getTheOther(),
                    lending.getThePerson().getId(),
                    lending.getDate().intValue(),
                    lending.getPrimaryImpl(),
                    lending.getId()};

            return new Query(query, parameters);
        }
    }
}