package org.jtheque.primary.view.impl.models;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.utils.db.DaoNotes;
import org.jtheque.core.utils.db.Note;

import javax.swing.DefaultComboBoxModel;

/**
 * A model for combo boxes. This model display all the notes.
 *
 * @author Baptiste Wicht
 */
public final class NotesComboBoxModel extends DefaultComboBoxModel {
    private final DaoNotes daoNotes = DaoNotes.getInstance();

    /**
     * Construct a new NotesComboBoxModel.
     */
    public NotesComboBoxModel() {
        super();

        Managers.getManager(IBeansManager.class).inject(this);
    }

    @Override
    public Object getElementAt(int index) {
        return daoNotes.getNotes()[index];
    }

    @Override
    public int getSize() {
        return daoNotes.getNotes().length;
    }

    /**
     * Return the selected data in the model.
     *
     * @return The data who's selected.
     */
    public Note getSelectedNote() {
        return (Note) getSelectedItem();
    }
}