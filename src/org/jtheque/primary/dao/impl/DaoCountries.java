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
import org.jtheque.primary.dao.able.IDaoCountries;
import org.jtheque.primary.od.able.Country;
import org.jtheque.primary.od.impl.CountryImpl;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * A Data Access Object implementation for countries.
 *
 * @author Baptiste Wicht
 */
public final class DaoCountries extends GenericDao<Country> implements IDaoCountries {
    private final ParameterizedRowMapper<Country> rowMapper = new CountryRowMapper();
    private final QueryMapper queryMapper = new CountryQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    @Resource
    private SimpleJdbcTemplate jdbcTemplate;

    /**
     * Create a new DaoCountries.
     */
    public DaoCountries() {
        super(TABLE);
    }

    @Override
    public Collection<Country> getCountries() {
        return getAll();
    }

    @Override
    public Country getCountry(int id) {
        return get(id);
    }

    @Override
    public Country getCountry(String name) {
        List<Country> countries = jdbcTemplate.query("SELECT * FROM " + TABLE + " WHERE NAME = ?", rowMapper, name);

        if (countries.isEmpty()) {
            return null;
        }

        Country country = countries.get(0);

        if (isNotInCache(country.getId())) {
            getCache().put(country.getId(), country);
        }

        return getCache().get(country.getId());
    }

    @Override
    public boolean exist(Country country) {
        return getCountry(country.getName()) != null;
    }

    @Override
    public Country createCountry() {
        return new CountryImpl();
    }

    @Override
    protected void loadCache() {
        Collection<Country> countries = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Country country : countries) {
            getCache().put(country.getId(), country);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        Country country = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, country);
    }

    @Override
    protected ParameterizedRowMapper<Country> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    /**
     * A row mapper to map resultset to country.
     *
     * @author Baptiste Wicht
     */
    private final class CountryRowMapper implements ParameterizedRowMapper<Country> {
        @Override
        public Country mapRow(ResultSet rs, int i) throws SQLException {
            Country country = createCountry();

            country.setId(rs.getInt("ID"));
            country.setName(rs.getString("NAME"));

            return country;
        }
    }

    /**
     * A query mapper to map country to query.
     *
     * @author Baptiste Wicht
     */
    private static final class CountryQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Country country = (Country) entity;

            String query = "INSERT INTO " + TABLE + " (NAME) VALUES(?)";

            Object[] parameters = {country.getName()};

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Country country = (Country) entity;

            String query = "UPDATE " + TABLE + " SET NAME = ? WHERE ID = ?";

            Object[] parameters = {
                    country.getName(),
                    country.getId()};

            return new Query(query, parameters);
        }
    }
}