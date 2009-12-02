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
import org.jtheque.primary.dao.able.IDaoPersons;
import org.jtheque.primary.od.able.Person;

import javax.annotation.Resource;
import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a creation of an editor.
 *
 * @author Baptiste Wicht
 */
public final class CreatedPersonEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = -3037357344759861059L;

    private final Person person;

    @Resource
    private IDaoPersons daoPersons;

    /**
     * Construct a new CreatedEditorEdit.
     *
     * @param person The created borrower.
     */
    public CreatedPersonEdit(Person person) {
        super();

        Managers.getManager(IBeansManager.class).inject(this);

        this.person = person;
    }

    @Override
    public void undo() {
        super.undo();

        daoPersons.delete(person);
    }

    @Override
    public void redo() {
        super.redo();

        daoPersons.create(person);
    }

    @Override
    public String getPresentationName() {
        return Managers.getManager(ILanguageManager.class).getMessage("undo.edits.create");
    }
}