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
import org.jtheque.primary.od.able.Kind;
import org.jtheque.primary.services.able.IKindsService;

import javax.annotation.Resource;
import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a creation of an editor.
 *
 * @author Baptiste Wicht
 */
public final class CreatedKindEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = -3037357344759861059L;

    private final Kind kind;

    @Resource
    private IKindsService kindsService;

    /**
     * Construct a new CreatedEditorEdit.
     *
     * @param kind The created editor.
     */
    public CreatedKindEdit(Kind kind) {
        super();

        Managers.getManager(IBeansManager.class).inject(this);

        this.kind = kind;
    }

    @Override
    public void undo() {
        super.undo();

        kindsService.delete(kind);
    }

    @Override
    public void redo() {
        super.redo();

        kindsService.create(kind);
    }

    @Override
    public String getPresentationName() {
        return Managers.getManager(ILanguageManager.class).getMessage("undo.edits.create");
    }
}