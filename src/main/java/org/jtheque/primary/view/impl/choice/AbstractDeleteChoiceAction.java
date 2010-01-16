package org.jtheque.primary.view.impl.choice;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.undo.IUndoRedoManager;

import javax.swing.undo.UndoableEdit;
import java.util.Arrays;
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
 * An abstract delete action for the choice view.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractDeleteChoiceAction extends AbstractChoiceAction {
    private Collection<Deleter> deleters;

    /**
     * Set the deleters to use to execute the action.
     *
     * @param deleters The deleters to use.
     */
    protected final void setDeleters(Deleter... deleters) {
        this.deleters = Arrays.asList(deleters);
    }

    @Override
    public final boolean canDoAction(String action) {
        return "delete".equals(action);
    }

    /**
     * Delete the current content of the view.
     */
    protected final void delete() {
        for (Deleter deleter : deleters) {
            if (deleter.canDelete(getContent())) {
                deleter.delete(getSelectedItem());
            }
        }
    }

    /**
     * Add an edit to undo-redo manager if deleted.
     *
     * @param deleted The boolean tag of the delete operation.
     * @param edit    The undoable edit.
     */
    protected static void addEditIfDeleted(boolean deleted, UndoableEdit edit) {
        if (deleted) {
            Managers.getManager(IUndoRedoManager.class).addEdit(edit);
        }
    }
}