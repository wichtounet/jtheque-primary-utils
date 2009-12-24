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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.primary.od.able.Saga;
import org.jtheque.primary.services.able.ISagasService;

import javax.annotation.Resource;
import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a delete of a saga.
 *
 * @author Baptiste Wicht
 */
public final class DeletedSagaEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = -3037357344759861059L;

    private final Saga saga;

    @Resource
    private ISagasService sagasService;

    /**
     * Construct a new DeletedSagaEdit.
     *
     * @param saga The deleted saga.
     */
    public DeletedSagaEdit(Saga saga) {
        super();

        Managers.getManager(IBeansManager.class).inject(this);

        this.saga = saga;
    }

    @Override
    public void undo() {
        super.undo();

        sagasService.create(saga);
    }

    @Override
    public void redo() {
        super.redo();

        sagasService.delete(saga);
    }

    @Override
    public String getPresentationName() {
        return Managers.getManager(ILanguageManager.class).getMessage("undo.edits.delete");
    }
}