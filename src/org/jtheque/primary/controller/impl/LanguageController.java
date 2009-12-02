package org.jtheque.primary.controller.impl;

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
import org.jtheque.core.managers.undo.IUndoRedoManager;
import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.primary.controller.able.ILanguageController;
import org.jtheque.primary.controller.impl.undo.CreatedLanguageEdit;
import org.jtheque.primary.od.able.Language;
import org.jtheque.primary.services.able.ILanguagesService;
import org.jtheque.primary.view.able.ILanguageView;
import org.jtheque.primary.view.able.ViewMode;

import javax.annotation.Resource;

/**
 * A Language Controller.
 *
 * @author Baptiste Wicht
 */
public final class LanguageController extends AbstractController implements ILanguageController {
    private ViewMode state = ViewMode.NEW;
    private Language currentLanguage;

    @Resource
    private ILanguagesService languagesService;

    @Resource
    private ILanguageView languageView;

    @Override
    public void newLanguage() {
        state = ViewMode.NEW;

        languageView.reload();
        currentLanguage = languagesService.getEmptyLanguage();
    }

    @Override
    public void editLanguage(Language language) {
        state = ViewMode.EDIT;

        languageView.reload(language);
        currentLanguage = language;

        displayView();
    }

    @Override
    public void save(String name) {
        currentLanguage.setName(name);

        if (state == ViewMode.NEW) {
            languagesService.create(currentLanguage);

            Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedLanguageEdit(currentLanguage));
        } else {
            languagesService.save(currentLanguage);
        }
    }

    @Override
    public ILanguageView getView() {
        return languageView;
    }
}