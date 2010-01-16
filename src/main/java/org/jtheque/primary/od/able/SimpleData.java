package org.jtheque.primary.od.able;

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

/**
 * A simple data specification.
 *
 * @author Baptiste Wicht
 */
public interface SimpleData extends Data {
	/**
	 * The data type of the simple datas.
	 *
	 * @author Baptiste Wicht
	 */
	enum DataType {
		LANGUAGE("T_LANGUAGES", "Languages", false),
		COUNTRY("T_COUNTRIES", "Countries", false),
		KIND("T_KINDS", "Kinds", true),
		SAGA("T_SAGAS", "Sagas", true),
		TYPE("T_TYPES", "Types", true);

		private final String table;
		private final String dataType;
		private final boolean primary;

		/**
		 * Construct a new DataType.
		 *
		 * @param table The table in the database.
		 * @param dataType The data type.
		 * @param primary Indicate if the simple data is a primary data or not.
		 */
		DataType(String table, String dataType, boolean primary){
			this.table = table;
			this.dataType = dataType;
			this.primary = primary;
		}

		/**
		 * Return the table in the database of this data type.
		 *
		 * @return The table
		 */
		public String getTable(){
			return table;
		}

		/**
		 * Indicate if this data type refer to a primary data or not.
		 *
		 * @return true if this data type refers to a primary data else false.
		 */
		public boolean isPrimary(){
			return primary;
		}

		/**
		 * Return the data type of this data type.
		 *
		 * @return The data type of this data type.
		 */
		public String getDataType(){
			return dataType;
		}
	}

	/**
	 * Return the name of the type.
	 *
	 * @return The name of the type.
	 */
	String getName();

	/**
	 * Set the name of the type.
	 *
	 * @param name The name of the type.
	 */
	void setName(String name);

	/**
	 * Return the data type of the simple data.
	 *
	 * @return The data type of this simple data.
	 */
	DataType getType();
}