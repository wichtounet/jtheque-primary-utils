package org.jtheque.primary.services.able;

import org.jtheque.persistence.able.DataContainer;
import org.jtheque.primary.od.able.SimpleData;

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
 * A countries service specification.
 *
 * @author Baptiste Wicht
 */
public interface ISimpleDataService extends DataContainer<SimpleData>, DataService<SimpleData> {
	String DATA_TYPE = "SimpleDatas";

	/**
	 * Return the simple data of the specified name.
	 *
	 * @param name The name to search.
	 *
	 * @return The simple data of the specified name if there is one else null.
	 */
	SimpleData getSimpleData(String name);

	/**
	 * Create all the simple datas.
	 *
	 * @param datas The simple datas to create.
	 */
	void createAll(Iterable<SimpleData> datas);

	/**
	 * Indicate if the specified simple data exists.
	 *
	 * @param data The simple data to test.
	 *
	 * @return <code>true</code> if the data exists else <code>false</code>.
	 */
	boolean exist(SimpleData data);

	/**
	 * Return an empty simple data.
	 *
	 * @return An empty simple data.
	 */
	SimpleData getEmptySimpleData();

	/**
	 * Return the default simple data.
	 *
	 * @return The default simple data.
	 */
	SimpleData getDefaultSimpleData();

	/**
	 * Test if a simple data exists with the specified name or not.
	 *
	 * @param name The name to search for.
	 *
	 * @return <code>true</code> if a data exists else <code>false</code>.
	 */
	boolean exist(String name);

	/**
	 * Indicate if there is no datas.
	 *
	 * @return <code>true</code> if there is no datas else <code>false</code>.
	 */
	boolean hasNoDatas();
}