package org.jtheque.primary.impl;

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

import org.jtheque.primary.able.dao.IDaoLendings;
import org.jtheque.primary.able.dao.IDaoPersons;
import org.jtheque.primary.able.od.SimpleData.DataType;
import org.jtheque.schemas.utils.DefaultSchema;
import org.jtheque.utils.bean.Version;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The database schema for the Movies Module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryUtilsSchema extends DefaultSchema {
    /**
     * Construct a new MoviesSchema.
     */
    public PrimaryUtilsSchema() {
        super(new Version("1.1"), "PrimaryUtils-Schema", "jtheque-collection-schema");
    }

    @Override
    public void install() {
        createGeneralDataTables();
        createPrimaryDataTables();
        createReferentialIntegrityConstraints();
    }

    @Override
    public void update(Version from) {
        if ("1.0".equals(from.getVersion())) {
            createReferentialIntegrityConstraints();
            createPrimaryDataTables();
            convertBorrowersToPersons();
        }
    }

    /**
     * Create the primary data tables of the schema.
     */
    private void createPrimaryDataTables() {
        createTable(DataType.TYPE.getTable(), "ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL, IMPL VARCHAR(20) NOT NULL, CONSTRAINT UNIQUE_TYPE UNIQUE(NAME, IMPL)");
        createTable(DataType.KIND.getTable(), "ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL, IMPL VARCHAR(20) NOT NULL, CONSTRAINT UNIQUE_KIND UNIQUE(NAME, IMPL)");
        createTable(DataType.SAGA.getTable(), "ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL, IMPL VARCHAR(20) NOT NULL, CONSTRAINT UNIQUE_SAGA UNIQUE(NAME, IMPL)");
        createTable(IDaoLendings.TABLE, "ID INT IDENTITY PRIMARY KEY, DATE INT NOT NULL, THE_OTHER_FK INT NOT NULL, THE_PERSON_FK INT NOT NULL, IMPL VARCHAR(20) NOT NULL");

        updateTable(DataType.KIND.getTable(), "CREATE INDEX KINDS_IDX ON {} (ID)");
        updateTable(DataType.SAGA.getTable(), "CREATE INDEX SAGAS_IDX ON {} (ID)");
        updateTable(DataType.TYPE.getTable(), "CREATE INDEX TYPES_IDX ON {} (ID)");
        updateTable(IDaoLendings.TABLE, "CREATE INDEX LENDINGS_IDX ON {} (ID)");
    }

    /**
     * Create the general data tables of the schema.
     */
    private void createGeneralDataTables() {
        createTable(DataType.COUNTRY.getTable(), "ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(150) NOT NULL UNIQUE");
        createTable(DataType.LANGUAGE.getTable(), "ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(100) NOT NULL UNIQUE");
        createTable(IDaoPersons.TABLE, "ID INT IDENTITY PRIMARY KEY, NAME VARCHAR(100) NOT NULL, FIRST_NAME VARCHAR(100) NOT NULL, EMAIL VARCHAR(100), NOTE INT, THE_COUNTRY_FK INT, TYPE VARCHAR(25), CONSTRAINT UNIQUE_PERSON UNIQUE(NAME, FIRST_NAME, TYPE)");

        update("CREATE INDEX PERSONS_IDX ON " + IDaoPersons.TABLE + "(ID)");
        update("CREATE INDEX LANGUAGES_IDX ON " + DataType.LANGUAGE.getTable() + "(ID)");
        update("CREATE INDEX COUNTRIES_IDX ON " + DataType.COUNTRY.getTable() + "(ID)");
    }

    /**
     * Create the constraints for referential integrity.
     */
    private void createReferentialIntegrityConstraints() {
        alterTable(IDaoPersons.TABLE, "ADD FOREIGN KEY (THE_COUNTRY_FK) REFERENCES " + DataType.COUNTRY.getTable() + " (ID) ON UPDATE SET NULL");
        alterTable(IDaoLendings.TABLE, "ADD FOREIGN KEY (THE_PERSON_FK) REFERENCES " + IDaoPersons.TABLE + " (ID) ON UPDATE SET NULL");
    }

    /**
     * Convert the borrowers old db table to the new table Persons.
     */
    private void convertBorrowersToPersons() {
        List<Object[]> borrowers = getJdbcTemplate().query("SELECT * FROM T_BORROWERS", new BorrowerRowMapper());

        String query = "INSERT INTO " + IDaoPersons.TABLE + "(NAME, FIRST_NAME, EMAIL, THE_COUNTRY_FK, TYPE) VALUES (?,?,?,?,?)";

        for (Object[] borrower : borrowers) {
            getJdbcTemplate().update(query, borrower[1], borrower[2], borrower[0], borrower[3], PrimaryConstants.BORROWER);
        }

        getJdbcTemplate().update("DROP TABLE IF EXISTS T_BORROWERS");
    }

    /**
     * A simple row mapper for the old Borrowers db table.
     *
     * @author Baptiste Wicht
     */
    private static final class BorrowerRowMapper implements RowMapper<Object[]> {
        @Override
        public Object[] mapRow(ResultSet rs, int i) throws SQLException {
            Object[] borrower = new Object[4];

            borrower[0] = rs.getString("EMAIL");
            borrower[1] = rs.getString("NAME");
            borrower[2] = rs.getString("FIRSTNAME");
            borrower[3] = rs.getInt("THE_COUNTRY_FK");

            return borrower;
        }
    }
}