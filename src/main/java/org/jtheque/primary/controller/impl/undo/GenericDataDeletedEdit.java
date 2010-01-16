package org.jtheque.primary.controller.impl.undo;

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

import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.core.utils.CoreUtils;
import org.jtheque.primary.services.able.DataService;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a delete of a film.
 *
 * @author Baptiste Wicht
 */
public final class GenericDataDeletedEdit<T extends Entity> extends AbstractUndoableEdit {
	private final T movie;
	private final String dataService;

	/**
	 * Construct a new DeletedFilmEdit.
	 *
	 * @param dataService The data service to use.
	 * @param movie The deleted movie.
	 */
	public GenericDataDeletedEdit(String dataService, T movie){
		super();

		this.dataService = dataService;
		this.movie = movie;
	}

	@Override
	public void undo(){
		super.undo();

		CoreUtils.<DataService<T>>getBean(dataService).create(movie);
	}

	@Override
	public void redo(){
		super.redo();

		CoreUtils.<DataService<T>>getBean(dataService).delete(movie);
	}

	@Override
	public String getPresentationName(){
		return CoreUtils.getMessage("undo.edits.delete");
	}
}