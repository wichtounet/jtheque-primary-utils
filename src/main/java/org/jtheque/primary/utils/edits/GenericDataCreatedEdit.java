package org.jtheque.primary.utils.edits;

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

import org.jtheque.persistence.able.Entity;
import org.jtheque.primary.able.services.DataService;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a delete of a film.
 *
 * @author Baptiste Wicht
 */
public final class GenericDataCreatedEdit<T extends Entity> extends AbstractUndoableEdit {
    private final T movie;
    private final DataService<T> dataService;

    /**
     * Construct a new DeletedFilmEdit.
     *
     * @param dataService The data service to use.
     * @param movie       The deleted movie.
     */
    public GenericDataCreatedEdit(DataService<T> dataService, T movie) {
        super();

        this.dataService = dataService;
        this.movie = movie;
    }

    @Override
    public void undo() {
        super.undo();

        dataService.delete(movie);
    }

    @Override
    public void redo() {
        super.redo();

        dataService.create(movie);
    }

    @Override
    public String getPresentationName() {
        return "undo.edits.create";
	}
}