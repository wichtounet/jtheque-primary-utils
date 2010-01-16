package org.jtheque.primary;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.schema.AbstractSchema;
import org.jtheque.core.managers.schema.HSQLImporter;
import org.jtheque.core.managers.schema.Insert;
import org.jtheque.primary.dao.able.IDaoCollections;
import org.jtheque.primary.dao.able.IDaoLendings;
import org.jtheque.primary.dao.able.IDaoPersons;
import org.jtheque.primary.od.able.SimpleData.DataType;
import org.jtheque.utils.bean.Version;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The database schema for the Movies Module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryUtilsSchema extends AbstractSchema {
	@Resource
	private SimpleJdbcTemplate jdbcTemplate;

	private static final String[] DEPENDENCIES = {};

	/**
	 * Construct a new MoviesSchema.
	 */
	public PrimaryUtilsSchema(){
		super();

		Managers.getManager(IBeansManager.class).inject(this);
	}

	@Override
	public Version getVersion(){
		return new Version("1.1");
	}

	@Override
	public String getId(){
		return "PrimaryUtils-Schema";
	}

	@Override
	public String[] getDependencies(){
		return DEPENDENCIES;
	}

	@Override
	public void install(){
		createGeneralDataTables();
		createPrimaryDataTables();
		createReferentialIntegrityConstraints();
	}

	@Override
	public void update(Version from){
		if ("1.0".equals(from.getVersion())){
			createReferentialIntegrityConstraints();
			createPrimaryDataTables();
			convertBorrowersToPersons();
		}
	}

	/**
	 * Create the primary data tables of the schema.
	 */
	private void createPrimaryDataTables(){
		jdbcTemplate.update("CREATE TABLE " + DataType.TYPE.getTable() + " (ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL, IMPL VARCHAR(20) NOT NULL, CONSTRAINT UNIQUE_TYPE UNIQUE(NAME, IMPL))");
		jdbcTemplate.update("CREATE TABLE " + DataType.KIND.getTable() + " (ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL, IMPL VARCHAR(20) NOT NULL, CONSTRAINT UNIQUE_KIND UNIQUE(NAME, IMPL))");
		jdbcTemplate.update("CREATE TABLE " + DataType.SAGA.getTable() + " (ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL, IMPL VARCHAR(20) NOT NULL, CONSTRAINT UNIQUE_SAGA UNIQUE(NAME, IMPL))");
		jdbcTemplate.update("CREATE TABLE " + DataType.LANGUAGE.getTable() + " (ID INT IDENTITY PRIMARY KEY, DATE INT NOT NULL, THE_PERSON_FK INT NOT NULL, IMPL VARCHAR(20) NOT NULL)");
		jdbcTemplate.update("CREATE TABLE " + IDaoCollections.TABLE + " (ID INT IDENTITY PRIMARY KEY, TITLE VARCHAR(150) NOT NULL UNIQUE, PROTECTED BOOLEAN, PASSWORD VARCHAR(150), IMPL VARCHAR(20) NOT NULL)");

		jdbcTemplate.update("CREATE INDEX KINDS_IDX ON " + DataType.KIND.getTable() + "(ID)");
		jdbcTemplate.update("CREATE INDEX SAGAS_IDX ON " + DataType.SAGA.getTable() + "(ID)");
		jdbcTemplate.update("CREATE INDEX LENDINGS_IDX ON " + IDaoLendings.TABLE + "(ID)");
		jdbcTemplate.update("CREATE INDEX TYPES_IDX ON " + DataType.TYPE.getTable() + "(ID)");
		jdbcTemplate.update("CREATE INDEX COLLECTIONS_IDX ON " + IDaoCollections.TABLE + "(ID)");
	}

	/**
	 * Create the general data tables of the schema.
	 */
	private void createGeneralDataTables(){
		jdbcTemplate.update("CREATE TABLE " + DataType.COUNTRY.getTable() + " (ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL UNIQUE)");
		jdbcTemplate.update("CREATE TABLE " + DataType.LANGUAGE.getTable() + " (ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(100) NOT NULL UNIQUE)");
		jdbcTemplate.update("CREATE TABLE " + IDaoPersons.TABLE + " (ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(100) NOT NULL, FIRST_NAME VARCHAR(100) NOT NULL, EMAIL VARCHAR(100), NOTE INT, THE_COUNTRY_FK INT, TYPE VARCHAR(25), CONSTRAINT UNIQUE_PERSON UNIQUE(NAME, FIRST_NAME, TYPE))");

		jdbcTemplate.update("CREATE INDEX PERSONS_IDX ON " + IDaoPersons.TABLE + "(ID)");
		jdbcTemplate.update("CREATE INDEX LANGUAGES_IDX ON " + DataType.LANGUAGE.getTable() + "(ID)");
		jdbcTemplate.update("CREATE INDEX COUNTRIES_IDX ON " + DataType.COUNTRY.getTable() + "(ID)");
	}

	/**
	 * Create the constraints for referential integrity.
	 */
	private void createReferentialIntegrityConstraints(){
		jdbcTemplate.update("ALTER TABLE " + IDaoPersons.TABLE + " ADD FOREIGN KEY (THE_COUNTRY_FK) REFERENCES  " + DataType.COUNTRY.getTable() + "  (ID) ON UPDATE SET NULL");
		jdbcTemplate.update("ALTER TABLE " + IDaoLendings.TABLE + " ADD FOREIGN KEY (THE_PERSON_FK) REFERENCES  " + IDaoPersons.TABLE + "  (ID) ON UPDATE SET NULL");
	}

	/**
	 * Convert the borrowers old db table to the new table Persons.
	 */
	private void convertBorrowersToPersons(){
		List<Object[]> borrowers = jdbcTemplate.query("SELECT * FROM T_BORROWERS", new BorrowerRowMapper());

		String query = "INSERT INTO " + IDaoPersons.TABLE + "(NAME, FIRST_NAME, EMAIL, THE_COUNTRY_FK, TYPE) VALUES (?,?,?,?,?)";

		for (Object[] borrower : borrowers){
			jdbcTemplate.update(query, borrower[1], borrower[2], borrower[0], borrower[3], PrimaryConstants.BORROWER);
		}

		jdbcTemplate.update("DROP TABLE IF EXISTS T_BORROWERS");
	}

	/**
	 * A simple row mapper for the old Borrowers db table.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class BorrowerRowMapper implements ParameterizedRowMapper<Object[]> {
		@Override
		public Object[] mapRow(ResultSet rs, int i) throws SQLException{
			Object[] borrower = new Object[4];

			borrower[0] = rs.getString("EMAIL");
			borrower[1] = rs.getString("NAME");
			borrower[2] = rs.getString("FIRSTNAME");
			borrower[3] = rs.getInt("THE_COUNTRY_FK");

			return borrower;
		}
	}

	@Override
	public void importDataFromHSQL(Iterable<Insert> inserts){
		HSQLImporter importer = new HSQLImporter();

		importer.match("OD_COUNTRY", "INSERT INTO " + DataType.COUNTRY.getTable() + " (ID, NAME) VALUES (?,?,?)", 0, 2);
		importer.match("OD_LANGUAGE", "INSERT INTO " + DataType.LANGUAGE.getTable() + " (ID, NAME) VALUES(?, ?)", 0, 2);
		importer.match("OD_BORROWER", "INSERT INTO " + IDaoPersons.TABLE + " (ID, NAME, FIRST_NAME, EMAIL, THE_COUNTRY_FK, TYPE) VALUES(?,?,?,?,?,?)", PrimaryConstants.BORROWER, 0, 3, 2, 4, 5);

		importer.importInserts(inserts);
	}
}