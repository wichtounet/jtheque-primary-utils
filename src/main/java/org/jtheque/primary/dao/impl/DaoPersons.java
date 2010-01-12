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
import org.jtheque.core.utils.db.DaoNotes;
import org.jtheque.primary.dao.able.IDaoPersons;
import org.jtheque.primary.dao.able.IDaoSimpleDatas;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.impl.PersonImpl;
import org.jtheque.utils.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A Data Access Object implementation for borrowers.
 *
 * @author Baptiste Wicht
 */
public final class DaoPersons extends GenericDao<Person> implements IDaoPersons {
    private final ParameterizedRowMapper<Person> rowMapper = new PersonRowMapper();
    private final QueryMapper queryMapper = new PersonQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    @Resource
    private SimpleJdbcTemplate jdbcTemplate;

    @Resource
    private IDaoSimpleDatas daoCountries;

    private final DaoNotes daoNotes = DaoNotes.getInstance();

    /**
     * Construct a new DaoBorrowers.
     */
    public DaoPersons() {
        super(TABLE);
    }

    @Override
    public Collection<Person> getPersons(String type) {
        return getAll(type);
    }

    /**
     * Return all the persons of the specified type.
     *
     * @param type The searched type.
     * @return Return a Collection containing all the persons of the specified type.
     */
    private Collection<Person> getAll(CharSequence type) {
        if (StringUtils.isEmpty(type)) {
            return getAll();
        }

        load();

        Collection<Person> persons = new ArrayList<Person>(getCache().size() / 3);

        for (Person person : getCache().values()) {
            if (type.equals(person.getType())) {
                persons.add(person);
            }
        }

        return persons;
    }

    @Override
    public Person getPerson(int id) {
        return get(id);
    }

    @Override
    public Person getPerson(String firstName, String name, String type) {
        load();

        if (getCache().isEmpty()) {
            return null;
        }

        for (Person person : getCache().values()) {
            if (type.equals(person.getType()) && firstName.equals(person.getFirstName()) && name.equals(person.getName())) {
                return person;
            }
        }

        return null;
    }

    @Override
    public boolean exists(String firstName, String name, String type) {
        return getPerson(firstName, name, type) != null;
    }

    @Override
    public boolean exist(Person person) {
        return getPerson(person.getFirstName(), person.getName(), person.getType()) != null;
    }

    @Override
    public Person createPerson() {
        return new PersonImpl();
    }

    @Override
    public void clearAll(String type) {
        Collection<Person> persons = getPersons(type);

        jdbcTemplate.update("DELETE FROM " + TABLE + " WHERE TYPE = ?", type);

        for (Person person : persons) {
            getCache().remove(person.getId());
        }
    }

    @Override
    protected void loadCache() {
        Collection<Person> persons = persistenceContext.getSortedList(TABLE, rowMapper);

        for (Person borrower : persons) {
            getCache().put(borrower.getId(), borrower);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        Person person = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, person);
    }

    @Override
    protected ParameterizedRowMapper<Person> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    /**
     * A row mapper to map resultset to borrower.
     *
     * @author Baptiste Wicht
     */
    private final class PersonRowMapper implements ParameterizedRowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person person = createPerson();

            person.setId(rs.getInt("ID"));
            person.setName(rs.getString("NAME"));
            person.setFirstName(rs.getString("FIRST_NAME"));
            person.setEmail(rs.getString("EMAIL"));
            person.setType(rs.getString("TYPE"));
            person.setTheCountry(daoCountries.getSimpleData(rs.getInt("THE_COUNTRY_FK")));

            if (StringUtils.isNotEmpty(rs.getString("NOTE"))) {
                person.setNote(daoNotes.getNote(DaoNotes.NoteType.getEnum(rs.getInt("NOTE"))));
            }

            return person;
        }
    }

    /**
     * A query mapper to map borrower to query.
     *
     * @author Baptiste Wicht
     */
    private static final class PersonQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            Person person = (Person) entity;

            String query = "INSERT INTO " + TABLE + " (NAME, FIRST_NAME, EMAIL, NOTE, THE_COUNTRY_FK, TYPE) VALUES(?,?,?,?,?,?)";

            Object[] parameters = {
                    person.getName(),
                    person.getFirstName(),
                    person.getEmail(),
                    person.getNote() == null ? 0 : person.getNote().getValue().intValue(),
                    person.getTheCountry() == null ? 0 : person.getTheCountry().getId(),
                    person.getType()};

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            Person person = (Person) entity;

            String query = "UPDATE " + TABLE + " SET NAME = ?, FIRST_NAME = ?, EMAIL = ?, NOTE = ?, THE_COUNTRY_FK = ?, TYPE = ? WHERE ID = ?";

            Object[] parameters = {
                    person.getName(),
                    person.getFirstName(),
                    person.getEmail(),
                    person.getNote() == null ? 0 : person.getNote().getValue().intValue(),
                    person.getTheCountry() == null ? 0 : person.getTheCountry().getId(),
                    person.getType(),
                    person.getId()};

            return new Query(query, parameters);
        }
    }
}