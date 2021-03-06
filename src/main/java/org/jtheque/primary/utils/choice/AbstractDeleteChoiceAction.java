package org.jtheque.primary.utils.choice;

import org.jtheque.i18n.LanguageService;
import org.jtheque.persistence.Entity;
import org.jtheque.ui.UIUtils;
import org.jtheque.undo.IUndoRedoService;

import javax.annotation.Resource;
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

    @Resource
    private LanguageService languageService;

    @Resource
    private IUndoRedoService undoRedoService;

    @Resource
    private UIUtils uiUtils;

    /**
     * Set the deleters to use to execute the action.
     *
     * @param deleters The deleters to use.
     */
    protected final void addDeleters(Deleter<? extends Entity>... deleters) {
        this.deleters.addAll(Arrays.asList(deleters));
    }

    @Override
    public final boolean canDoAction(String action) {
        return "delete".equals(action);
    }

    @Override
    public void execute() {
        final boolean yes = uiUtils.getDelegate().askUserForConfirmation(
                languageService.getMessage("choice.dialogs.delete") + ' ' + getSelectedItem().toString(),
                languageService.getMessage("choice.dialogs.delete.title"));

        if (yes) {
            for (Deleter<? extends Entity> deleter : deleters) {
                if (deleter.canDelete(getContent())) {
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
     * @param edit    The undoable edit.
     */
    protected final void addEditIfDeleted(boolean deleted, UndoableEdit edit) {
        if (deleted) {
            undoRedoService.addEdit(edit);
        }
    }
}