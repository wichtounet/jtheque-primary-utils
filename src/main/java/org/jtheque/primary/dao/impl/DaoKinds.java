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
import org.jtheque.primary.dao.able.IDaoKinds;
import org.jtheque.primary.od.able.Kind;
import org.jtheque.primary.od.impl.KindImpl;
import org.jtheque.utils.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A Data Access Object implementation for kinds.
 *
 * @author Baptiste Wicht
 */
public final class DaoKinds extends GenericDao<Kind> implements IDaoKinds {
    private final ParameterizedRowMapper<Kind> rowMapper = new KindRowMapper();
    private final QueryMapper queryMapper = new KindQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    @Resource
    private SimpleJdbcTemplate jdbcTemplate;

    /**
     * Construct a new DaoKinds.
     */
    public DaoKinds() {
        super(TABLE);
    }

    @Override
    public void create(Kind entity) {
        entity.setPrimaryImpl(PrimaryUtils.getPrimaryImpl());

        super.create(entity);
    }

    @Override
    public Kind createKind() {
        return new KindImpl();
    }

    @Override
    public boolean exists(String name) {
        return getKind(name) != null;
    }

    @Override
    public Collection<Kind> getKinds() {
        return getKinds(PrimaryUtils.getPrimaryImpl());
    }

    /**
     * Return all the kinds of the specific primary implementation.
     *
     * @param impl The implementation.
     * @return A Collection containing all the kinds of the specific primary implementation.
     */
    private Collection<Kind> getKinds(CharSequence impl) {
        if (StringUtils.isEmpty(impl)) {
            return getAll();
        }

        load();

        Collection<Kind> kinds = new ArrayList<Kind>(getCache().size() / 2);

        for (Kind kind : getCache().values()) {
            if (impl.equals(kind.getPrimaryImpl())) {
                kinds.add(kind);
            }
        }

        return kinds;
    }

    @Override
    public Kind getKind(int id) {
        return get(id);
    }

    @Override
    public Kind getKind(String name) {
        List<Kind> kinds = jdbcTemplate.query("SELECT * FROM " + TABLE + " WHERE NAME = ? AND IMPL = ?",
                rowMapper, name, PrimaryUtils.getPrimaryImpl());

        if (kinds.isEmpty()) {
            return null;
        }

        Kind kind = kinds.get(0);

        if (isNotInCache(kind.getId())) {
            getCache().put(kind.getId(), kind);
        }

        return getCache().get(kind.getId());
    }

    @Override
    protected ParameterizedRowMapper<Kind> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    @Override
    protected void loadCache() {
        Collection<Kind> kinds = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Kind kind : kinds) {
            getCache().put(kind.getId(), kind);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        Kind kind = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, kind);
    }

    /**
     * A mapper to map resultset to kind.
     *
     * @author Baptiste Wicht
     */
    private final class KindRowMapper implements ParameterizedRowMapper<Kind> {
        @Override
        public Kind mapRow(ResultSet rs, int i) throws SQLException {
            Kind kind = createKind();

            kind.setId(rs.getInt("ID"));
            kind.setName(rs.getString("NAME"));
            kind.setPrimaryImpl(rs.getString("IMPL"));

            return kind;
        }
    }

    /**
     * A mapper to map kind to query.
     *
     * @author Baptiste Wicht
     */
    private static final class KindQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Kind kind = (Kind) entity;

            String query = "INSERT INTO " + TABLE + " (NAME, IMPL) VALUES(?, ?)";

            Object[] parameters = {
                    kind.getName(),
                    kind.getPrimaryImpl()
            };

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Kind kind = (Kind) entity;

            String query = "UPDATE " + TABLE + " SET NAME = ?, IMPL = ? WHERE ID = ?";

            Object[] parameters = {
                    kind.getName(),
                    kind.getPrimaryImpl(),
                    kind.getId()};

            return new Query(query, parameters);
        }
    }
}