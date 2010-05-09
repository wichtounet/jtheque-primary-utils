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

import org.jtheque.persistence.CachedJDBCDao;
import org.jtheque.persistence.Query;
import org.jtheque.persistence.able.Entity;
import org.jtheque.persistence.able.IDaoNotes;
import org.jtheque.persistence.able.QueryMapper;
import org.jtheque.persistence.context.IDaoPersistenceContext;
import org.jtheque.persistence.impl.DaoNotes;
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
public final class DaoPersons extends CachedJDBCDao<Person> implements IDaoPersons {
	private final ParameterizedRowMapper<Person> rowMapper = new PersonRowMapper();
	private final QueryMapper queryMapper = new PersonQueryMapper();

	@Resource
	private IDaoPersistenceContext daoPersistenceContext;

	@Resource
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource
	private IDaoSimpleDatas daoCountries;

	@Resource
	private IDaoNotes daoNotes;

	/**
	 * Construct a new DaoBorrowers.
	 */
	public DaoPersons(){
		super(TABLE);
	}

	@Override
	public Collection<Person> getPersons(){
		return getAll();
	}

	@Override
	public Collection<Person> getPersons(String type){
		return getAll(type);
	}

    @Override
    public Person getPersonByTemporaryId(int id) {
        for(Person person : getAll()){
            if(person.getTemporaryContext().getId() == id){
                return person;
            }
        }

        return null;
    }

    /**
	 * Return all the persons of the specified type.
	 *
	 * @param type The searched type.
	 *
	 * @return Return a Collection containing all the persons of the specified type.
	 */
	private Collection<Person> getAll(CharSequence type){
		if (StringUtils.isEmpty(type)){
			return getAll();
		}

		load();

		Collection<Person> persons = new ArrayList<Person>(getCache().size() / 3);

		for (Person person : getCache().values()){
			if (type.equals(person.getType())){
				persons.add(person);
			}
		}

		return persons;
	}

	@Override
	public Person getPerson(int id){
		return get(id);
	}

	@Override
	public Person getPerson(String firstName, String name, String type){
		load();

		if (getCache().isEmpty()){
			return null;
		}

		for (Person person : getCache().values()){
			if (type.equals(person.getType()) && firstName.equals(person.getFirstName()) && name.equals(person.getName())){
				return person;
			}
		}

		return null;
	}

	@Override
	public boolean exists(String firstName, String name, String type){
		return getPerson(firstName, name, type) != null;
	}

	@Override
	public boolean exists(Person person){
		return getPerson(person.getFirstName(), person.getName(), person.getType()) != null;
	}

	@Override
	public Person create(){
		return new PersonImpl();
	}

	@Override
	public void clearAll(String type){
		Collection<Person> persons = getPersons(type);

		jdbcTemplate.update("DELETE FROM " + TABLE + " WHERE TYPE = ?", type);

		for (Person person : persons){
			getCache().remove(person.getId());
		}
	}

	@Override
	protected void loadCache(){
		Collection<Person> persons = daoPersistenceContext.getSortedList(TABLE, rowMapper);

		for (Person borrower : persons){
			getCache().put(borrower.getId(), borrower);
		}

		setCacheEntirelyLoaded();
	}

	@Override
	protected void load(int i){
		Person person = daoPersistenceContext.getDataByID(TABLE, i, rowMapper);

		getCache().put(i, person);
	}

	@Override
	protected ParameterizedRowMapper<Person> getRowMapper(){
		return rowMapper;
	}

	@Override
	protected QueryMapper getQueryMapper(){
		return queryMapper;
	}

	/**
	 * A row mapper to map resultset to borrower.
	 *
	 * @author Baptiste Wicht
	 */
	private final class PersonRowMapper implements ParameterizedRowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int i) throws SQLException{
			Person person = create();

			person.setId(rs.getInt("ID"));
			person.setName(rs.getString("NAME"));
			person.setFirstName(rs.getString("FIRST_NAME"));
			person.setEmail(rs.getString("EMAIL"));
			person.setType(rs.getString("TYPE"));
			person.setTheCountry(daoCountries.getSimpleData(rs.getInt("THE_COUNTRY_FK")));

			if (StringUtils.isNotEmpty(rs.getString("NOTE"))){
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
		public Query constructInsertQuery(Entity entity){
			String query = "INSERT INTO " + TABLE + " (NAME, FIRST_NAME, EMAIL, NOTE, THE_COUNTRY_FK, TYPE) VALUES(?,?,?,?,?,?)";

			return new Query(query, fillArray((Person) entity, false));
		}

		@Override
		public Query constructUpdateQuery(Entity entity){
			String query = "UPDATE " + TABLE + " SET NAME = ?, FIRST_NAME = ?, EMAIL = ?, NOTE = ?, THE_COUNTRY_FK = ?, TYPE = ? WHERE ID = ?";

			return new Query(query, fillArray((Person) entity, true));
		}

		/**
		 * Fill the array with the informations of the person.
		 *
		 * @param person The person to use to fill the array.
		 * @param id Indicate if we must add the id to the array.
		 *
		 * @return The filled array.
		 */
		private static Object[] fillArray(Person person, boolean id){
			Object[] values = new Object[6 + (id ? 1 : 0)];

			values[0] = person.getName();
			values[1] = person.getFirstName();
			values[2] = person.getEmail();
			values[3] = person.getNote() == null ? 0 : person.getNote().getValue().intValue();
			values[4] = person.getTheCountry() == null ? null : person.getTheCountry().getId();
			values[5] = person.getType();

			if (id){
				values[6] = person.getId();
			}

			return values;
		}
	}
}