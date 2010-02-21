package org.jtheque.primary.dao.able;

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

import org.jtheque.core.managers.persistence.able.Dao;
import org.jtheque.primary.od.able.SimpleData;

import java.util.Collection;

/**
 * A dao for simple data specification.
 *
 * @author Baptiste Wicht
 */
public interface IDaoSimpleDatas extends Dao<SimpleData> {
	/**
	 * Return all the simple datas.
	 *
	 * @return A Collection containing all the simple datas.
	 */
	Collection<SimpleData> getSimpleDatas();

	/**
	 * Return the simple data of the specified id.
	 *
	 * @param id The id to search.
	 *
	 * @return The simple data with the specified id.
	 */
	SimpleData getSimpleData(int id);

	/**
	 * Return the simple data of the specified title.
	 *
	 * @param title The title to search.
	 *
	 * @return The simple data with the specified title.
	 */
	SimpleData getSimpleData(String title);
}