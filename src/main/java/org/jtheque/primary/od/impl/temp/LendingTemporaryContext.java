package org.jtheque.primary.od.impl.temp;

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

import org.jtheque.core.managers.persistence.context.TemporaryContext;

/**
 * Temporary context of lending.
 *
 * @author Baptiste Wicht
 */
public final class LendingTemporaryContext extends TemporaryContext {
    private int film;
    private int borrower;

    /**
     * Return the borrower of the context.
     *
     * @return the borrower.
     */
    public int getBorrower() {
        return borrower;
    }

    /**
     * Set the borrower of the context.
     *
     * @param borrower the borrower.
     */
    public void setBorrower(int borrower) {
        this.borrower = borrower;
    }

    /**
     * Return the film of the context.
     *
     * @return the film.
     */
    public int getFilm() {
        return film;
    }

    /**
     * Set the film of the context.
     *
     * @param film the film.
     */
    public void setFilm(int film) {
        this.film = film;
    }
}