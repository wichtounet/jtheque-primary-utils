package org.jtheque.primary.utils.web.analyzers.generic;

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
 * A container for the pages of a generic web getter.
 *
 * @author Baptiste Wicht
 */
public final class Pages {
	private Page filmsPage;
	private Page actorsPage;
	private Page resultsPage;

	/**
	 * Return the page for the Films page.
	 *
	 * @return The page for the films page.
	 */
	public Page getFilmsPage(){
		return filmsPage;
	}

	/**
	 * Set the films page.
	 *
	 * @param filmsPage The page for the films.
	 */
	public void setFilmsPage(Page filmsPage){
		this.filmsPage = filmsPage;
	}

	/**
	 * Return the page for the actors if there is one.
	 *
	 * @return The page for the actors else null if there is no page for the actors.
	 */
	public Page getActorsPage(){
		return actorsPage;
	}

	/**
	 * Set the actors page.
	 *
	 * @param actorsPage The page for the actors.
	 */
	public void setActorsPage(Page actorsPage){
		this.actorsPage = actorsPage;
	}

	/**
	 * Return the page for the results.
	 *
	 * @return The page for the results.
	 */
	public Page getResultsPage(){
		return resultsPage;
	}

	/**
	 * Set the results page.
	 *
	 * @param resultsPage The page for the results.
	 */
	public void setResultsPage(Page resultsPage){
		this.resultsPage = resultsPage;
	}
}