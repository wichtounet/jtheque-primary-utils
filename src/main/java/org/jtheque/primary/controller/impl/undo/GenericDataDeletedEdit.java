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

import org.jtheque.persistence.able.Entity;
import org.jtheque.primary.services.able.DataService;

import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a delete of a film.
 *
 * @author Baptiste Wicht
 */
public final class GenericDataDeletedEdit<T extends Entity> extends AbstractUndoableEdit {
	private final T entity;
	private final DataService<T> dataService;

    /**
	 * Construct a new DeletedFilmEdit.
	 *
	 * @param dataService The data service to use.
	 * @param entity The deleted entity.
	 */
	public GenericDataDeletedEdit(DataService<T> dataService, T entity){
		super();

		this.dataService = dataService;
		this.entity = entity;
	}

	@Override
	public void undo(){
		super.undo();

		dataService.create(entity);
	}

	@Override
	public void redo(){
		super.redo();

		dataService.delete(entity);
	}

	@Override
	public String getPresentationName(){
		return "undo.edits.delete";
	}
}