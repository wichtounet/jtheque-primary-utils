package org.jtheque.primary.view.impl.choice;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.core.managers.undo.IUndoRedoManager;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.utils.CoreUtils;

import javax.swing.undo.UndoableEdit;
import java.util.ArrayList;
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
	private final Collection<Deleter<? extends Entity>> deleters = new ArrayList<Deleter<? extends Entity>>(10);

	/**
	 * Set the deleters to use to execute the action.
	 *
	 * @param deleters The deleters to use.
	 */
	protected final void addDeleters(Deleter<? extends Entity>... deleters){
		this.deleters.addAll(Arrays.asList(deleters));
	}

	@Override
	public final boolean canDoAction(String action){
		return "delete".equals(action);
	}

    @Override
    public void execute() {
        final boolean yes = Managers.getManager(IViewManager.class).askUserForConfirmation(
                CoreUtils.getMessage("choice.dialogs.delete") + ' ' + getSelectedItem().toString(),
                CoreUtils.getMessage("choice.dialogs.delete.title"));

        if (yes) {
			for (Deleter<? extends Entity> deleter : deleters){
				if (deleter.canDelete(getContent())){
					deleter.delete(getSelectedItem());

					break;
				}
			}
		}
    }

	/**
	 * Add an edit to undo-redo manager if deleted.
	 *
	 * @param deleted The boolean tag of the delete operation.
	 * @param edit The undoable edit.
	 */
	protected static void addEditIfDeleted(boolean deleted, UndoableEdit edit){
		if (deleted){
			Managers.getManager(IUndoRedoManager.class).addEdit(edit);
		}
	}
}