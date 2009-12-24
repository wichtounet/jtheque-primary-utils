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
import org.jtheque.primary.od.able.Language;
import org.jtheque.primary.services.able.ILanguagesService;

import javax.annotation.Resource;
import javax.swing.undo.AbstractUndoableEdit;

/**
 * An edit corresponding to a creation of a country.
 *
 * @author Baptiste Wicht
 */
public final class CreatedLanguageEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = -3037357344759861059L;

    private final Language language;

    @Resource
    private ILanguagesService languagesService;

    /**
     * Construct a new CreatedLanguageEdit.
     *
     * @param language The created language.
     */
    public CreatedLanguageEdit(Language language) {
        super();

        Managers.getManager(IBeansManager.class).inject(this);

        this.language = language;
    }

    @Override
    public void undo() {
        super.undo();

        languagesService.delete(language);
    }

    @Override
    public void redo() {
        super.redo();

        languagesService.create(language);
    }

    @Override
    public String getPresentationName() {
        return Managers.getManager(ILanguageManager.class).getMessage("undo.edits.create");
    }
}