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

public interface SimpleData extends Data{
    enum DataType {
        LANGUAGE("T_LANGUAGES", "Languages", false),
        COUNTRY("T_COUNTRIES", "Countries", false),
        KIND("T_KINDS", "Kinds", true),
        SAGA("T_SAGAS", "Sagas", true),
        TYPE("T_TYPES", "Types", true);

        private final String table;
        private final String dataType;
        private final boolean primary;

        DataType(String table, String dataType, boolean primary){
            this.table = table;
            this.dataType = dataType;
            this.primary = primary;
        }

        public String getTable(){
            return table;
        }

        public boolean isPrimary(){
            return primary;
        }

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

    DataType getType();
}