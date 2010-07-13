package org.jtheque.primary.utils.views;

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
import org.jtheque.ui.able.components.Borders;

import org.jdesktop.swingx.JXImagePanel;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.Component;

import static org.jtheque.ui.able.components.filthy.FilthyConstants.*;

/**
 * A combo box renderer to display star(s) depending on the note.
 *
 * @author Baptiste Wicht
 */
public final class NoteComboRenderer extends JXImagePanel implements ListCellRenderer {
    private final IDaoNotes daoNotes;

    /**
     * Construct a new <code>NoteComboRenderer</code>.
     *
     * @param daoNotes The dao notes.
     */
    public NoteComboRenderer(IDaoNotes daoNotes) {
        super();

        this.daoNotes = daoNotes;

        setOpaque(false);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            setImage(daoNotes.getImage((Note) value));
        }

        if (isSelected) {
            setBorder(BorderFactory.createLineBorder(INPUT_BORDER_COLOR, 2));
        } else {
            setBorder(Borders.EMPTY_BORDER);
        }

        return this;
    }
}