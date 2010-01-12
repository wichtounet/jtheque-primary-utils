package org.jtheque.primary.view.impl.renderers;

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

import org.jdesktop.swingx.JXImagePanel;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.resource.IResourceManager;
import org.jtheque.core.utils.db.DaoNotes;
import org.jtheque.core.utils.db.Note;
import org.jtheque.core.utils.ui.Borders;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;

/**
 * A combo box renderer to display star(s) depending on the note.
 *
 * @author Baptiste Wicht
 */
public final class NoteComboRenderer extends JXImagePanel implements ListCellRenderer {
    /**
     * Construct a new <code>NoteComboRenderer</code>.
     */
    public NoteComboRenderer() {
        super();

        setOpaque(false);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            setImage(DaoNotes.getImage((Note) value));
        }

        if (isSelected) {
            setBorder(BorderFactory.createLineBorder(Managers.getManager(IResourceManager.class).getColor("filthyInputBorderColor"), 2));
        } else {
            setBorder(Borders.EMPTY_BORDER);
        }

        return this;
    }
}