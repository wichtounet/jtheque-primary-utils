package org.jtheque.primary.able.services;

import org.jtheque.persistence.able.DataContainer;
import org.jtheque.primary.able.od.Lending;

import java.util.Collection;

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
 * A lendings service specification.
 *
 * @author Baptiste Wicht
 */
public interface ILendingsService extends DataContainer<Lending>, DataService<Lending> {
	String DATA_TYPE = "Lendings";

	/**
	 * Return the lending with this id.
	 *
	 * @param id The id to search the kind with.
	 *
	 * @return The lending.
	 */
	Lending getLending(int id);

	/**
	 * Indicate if there is no lendings.
	 *
	 * @return true if there is no lendings else false.
	 */
	boolean hasNoLendings();

	/**
	 * Return all the lendings.
	 *
	 * @return A List containing all the lendings.
	 */
	Collection<Lending> getLendings();

	/**
	 * Test if the lending is late or not.
	 *
	 * @param lending The lendings to test.
	 * @param days The days limit for lendings.
	 *
	 * @return <code>true</code> if the lending is late else <code>false</code>.
	 */
	boolean isLate(Lending lending, int days);

	/**
	 * Return an empty lending.
	 *
	 * @return An empty lending.
	 */
	Lending getEmptyLending();
}