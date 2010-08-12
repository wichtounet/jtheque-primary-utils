package org.jtheque.primary.impl.dao;

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

import org.jtheque.persistence.Entity;
import org.jtheque.persistence.DaoPersistenceContext;
import org.jtheque.persistence.QueryMapper;
import org.jtheque.persistence.utils.CachedJDBCDao;
import org.jtheque.persistence.utils.Query;
import org.jtheque.primary.able.IPrimaryUtils;
import org.jtheque.primary.able.dao.IDaoLendings;
import org.jtheque.primary.able.dao.IDaoPersons;
import org.jtheque.primary.able.od.Lending;
import org.jtheque.primary.impl.od.LendingImpl;
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
public final class DaoLendings extends CachedJDBCDao<Lending> implements IDaoLendings {
    private final ParameterizedRowMapper<Lending> rowMapper = new LendingRowMapper();
    private final QueryMapper queryMapper = new LendingQueryMapper();

    @Resource
    private DaoPersistenceContext daoPersistenceContext;

    @Resource
    private IDaoPersons daoPersons;

    @Resource
    private IPrimaryUtils primaryUtils;


    /**
     * Construct a new DaoLendings.
     */
    public DaoLendings() {
        super(TABLE);
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
        Collection<Lending> lendings = daoPersistenceContext.getSortedList(TABLE, rowMapper);

        for (Lending lending : lendings) {
            getCache().put(lending.getId(), lending);
        }
    }

    @Override
    public Collection<Lending> getLendings() {
        return getLendings(primaryUtils.getPrimaryImpl());
    }

    @Override
    public Collection<Lending> getAllLendings() {
        return getAll();
    }

    /**
     * Return all the lendings of the specific primary implementation.
     *
     * @param impl The primary implementation.
     *
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
    public Lending create() {
        return new LendingImpl();
    }

    @Override
    public boolean exists(Lending entity) {
        return get(entity.getId()) != null;
    }

    /**
     * A mapper to map resultset to lending.
     *
     * @author Baptiste Wicht
     */
    private final class LendingRowMapper implements ParameterizedRowMapper<Lending> {
        @Override
        public Lending mapRow(ResultSet rs, int i) throws SQLException {
            Lending lending = create();

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
            String query = "INSERT INTO " + TABLE + " (THE_OTHER_FK, THE_PERSON_FK, DATE, IMPL) VALUES(?,?,?, ?)";

            return new Query(query, fillArray((Lending) entity, false));
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            String query = "UPDATE " + TABLE + " SET THE_OTHER_FK = ?, THE_BORROWER_FK = ?, DATE = ?, IMPL = ? WHERE ID = ?";

            return new Query(query, fillArray((Lending) entity, true));
        }

        /**
         * Fill the array with the informations of the lending.
         *
         * @param lending The lending to use to fill the array.
         * @param id      Indicate if we must add the id to the array.
         *
         * @return The filled array.
         */
        private static Object[] fillArray(Lending lending, boolean id) {
            Object[] values = new Object[4 + (id ? 1 : 0)];

            values[0] = lending.getTheOther();
            values[1] = lending.getThePerson().getId();
            values[2] = lending.getDate().intValue();
            values[3] = lending.getPrimaryImpl();

            if (id) {
                values[4] = lending.getId();
            }

            return values;
        }
    }
}