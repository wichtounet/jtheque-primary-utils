package org.jtheque.primary.impl.views.model;

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

import org.jtheque.persistence.able.IDaoNotes;
import org.jtheque.persistence.able.Note;

import javax.swing.DefaultComboBoxModel;

/**
 * A model for combo boxes. This model display all the notes.
 *
 * @author Baptiste Wicht
 */
public final class NotesComboBoxModel extends DefaultComboBoxModel {
	private final IDaoNotes daoNotes;

	/**
	 * Construct a new NotesComboBoxModel.
	 *
	 * @param daoNotes
	 */
	public NotesComboBoxModel(IDaoNotes daoNotes){
		super();

		this.daoNotes = daoNotes;
	}

	@Override
	public Object getElementAt(int index){
		return daoNotes.getNotes()[index];
	}

	@Override
	public int getSize(){
		return daoNotes.getNotes().length;
	}

	/**
	 * Return the selected data in the model.
	 *
	 * @return The data who's selected.
	 */
	public Note getSelectedNote(){
		return (Note) getSelectedItem();
	}
}