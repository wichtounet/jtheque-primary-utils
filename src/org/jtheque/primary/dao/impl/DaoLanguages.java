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
import org.jtheque.primary.dao.able.IDaoLanguages;
import org.jtheque.primary.od.able.Language;
import org.jtheque.primary.od.impl.LanguageImpl;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * A Data Access Object implementation for languages.
 *
 * @author Baptiste Wicht
 */
public final class DaoLanguages extends GenericDao<Language> implements IDaoLanguages {
    private final ParameterizedRowMapper<Language> rowMapper = new LanguageRowMapper();
    private final QueryMapper queryMapper = new LanguageQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    @Resource
    private SimpleJdbcTemplate jdbcTemplate;

    /**
     * Create a new DaoLanguages.
     */
    public DaoLanguages() {
        super(TABLE);
    }

    @Override
    public Collection<Language> getLanguages() {
        return getAll();
    }

    @Override
    public Language getLanguage(int id) {
        return get(id);
    }

    @Override
    public Language getLanguage(String name) {
        List<Language> languages = jdbcTemplate.query("SELECT * FROM " + TABLE + " WHERE NAME = ?", rowMapper, name);

        if (languages.isEmpty()) {
            return null;
        }

        Language language = languages.get(0);

        if (isNotInCache(language.getId())) {
            getCache().put(language.getId(), language);
        }

        return getCache().get(language.getId());
    }

    @Override
    public boolean exist(Language language) {
        return getLanguage(language.getName()) != null;
    }

    @Override
    public Language createLanguage() {
        return new LanguageImpl();
    }

    @Override
    protected ParameterizedRowMapper<Language> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    @Override
    protected void loadCache() {
        Collection<Language> languages = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Language language : languages) {
            getCache().put(language.getId(), language);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        Language language = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, language);
    }

    /**
     * A row mapper to map resultset to language.
     *
     * @author Baptiste Wicht
     */
    private final class LanguageRowMapper implements ParameterizedRowMapper<Language> {
        @Override
        public Language mapRow(ResultSet rs, int i) throws SQLException {
            Language language = createLanguage();

            language.setId(rs.getInt("ID"));
            language.setName(rs.getString("NAME"));

            return language;
        }
    }

    /**
     * A query mapper to map language to query.
     *
     * @author Baptiste Wicht
     */
    private static final class LanguageQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Language borrower = (Language) entity;

            String query = "INSERT INTO " + TABLE + " (NAME) VALUES(?)";

            Object[] parameters = {
                    borrower.getName()};

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Language borrower = (Language) entity;

            String query = "UPDATE " + TABLE + " SET NAME = ? WHERE ID = ?";

            Object[] parameters = {
                    borrower.getName(),
                    borrower.getId()};

            return new Query(query, parameters);
        }
    }
}