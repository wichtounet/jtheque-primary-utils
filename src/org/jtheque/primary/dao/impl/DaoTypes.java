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
import org.jtheque.primary.dao.able.IDaoTypes;
import org.jtheque.primary.od.able.Type;
import org.jtheque.primary.od.impl.TypeImpl;
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
 * A Data Access Object implementation for types.
 *
 * @author Baptiste Wicht
 */
public final class DaoTypes extends GenericDao<Type> implements IDaoTypes {
    private final ParameterizedRowMapper<Type> rowMapper = new TypeRowMapper();
    private final QueryMapper queryMapper = new TypeQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    @Resource
    private SimpleJdbcTemplate jdbcTemplate;

    /**
     * Construct a new DaoTypes.
     */
    public DaoTypes() {
        super(TABLE);
    }

    @Override
    public void create(Type entity) {
        entity.setPrimaryImpl(PrimaryUtils.getPrimaryImpl());

        super.create(entity);
    }

    @Override
    public boolean exists(Type type) {
        return getType(type.getName()) != null;
    }

    @Override
    public Collection<Type> getTypes() {
        return getTypes(PrimaryUtils.getPrimaryImpl());
    }

    /**
     * Return all the types of the specific primary implementation.
     *
     * @param impl The primary implementation.
     * @return A Collection containing all the types of the specific primary implementation.
     */
    private Collection<Type> getTypes(CharSequence impl) {
        if (StringUtils.isEmpty(impl)) {
            return getAll();
        }

        load();

        Collection<Type> types = new ArrayList<Type>(getCache().size() / 2);

        for (Type type : getCache().values()) {
            if (impl.equals(type.getPrimaryImpl())) {
                types.add(type);
            }
        }

        return types;
    }

    @Override
    public Type getType(int id) {
        return get(id);
    }

    @Override
    public Type getType(String name) {
        List<Type> types = jdbcTemplate.query("SELECT * FROM " + TABLE + " WHERE NAME = ? AND IMPL = ?",
                rowMapper, name, PrimaryUtils.getPrimaryImpl());

        if (types.isEmpty()) {
            return null;
        }

        Type type = types.get(0);

        if (isNotInCache(type.getId())) {
            getCache().put(type.getId(), type);
        }

        return getCache().get(type.getId());
    }

    @Override
    protected ParameterizedRowMapper<Type> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    @Override
    protected void loadCache() {
        Collection<Type> types = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Type type : types) {
            getCache().put(type.getId(), type);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        Type type = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, type);
    }

    @Override
    public Type createType() {
        return new TypeImpl();
    }

    /**
     * A mapper to map a resultset to a type.
     *
     * @author Baptiste Wicht
     */
    private final class TypeRowMapper implements ParameterizedRowMapper<Type> {
        @Override
        public Type mapRow(ResultSet rs, int i) throws SQLException {
            Type type = createType();

            type.setId(rs.getInt("ID"));
            type.setName(rs.getString("NAME"));
            type.setPrimaryImpl(rs.getString("IMPL"));

            return type;
        }
    }

    /**
     * A mapper to map a type to a query.
     *
     * @author Baptiste Wicht
     */
    private static final class TypeQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Type type = (Type) entity;

            String query = "INSERT INTO " + TABLE + " (NAME, IMPL) VALUES(?, ?)";

            Object[] parameters = {
                    type.getName(),
                    type.getPrimaryImpl()
            };

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Type type = (Type) entity;

            String query = "UPDATE " + TABLE + " SET NAME = ?, IMPL = ? WHERE ID = ?";

            Object[] parameters = {
                    type.getName(),
                    type.getPrimaryImpl(),
                    type.getId()};

            return new Query(query, parameters);
        }
    }
}