package org.jtheque.primary.view.impl.actions.language;

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

import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.primary.controller.able.IChoiceController;
import org.jtheque.primary.services.able.ILanguagesService;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to edit a language.
 *
 * @author Baptiste Wicht
 */
public final class AcEditLanguage extends JThequeAction {
    private static final long serialVersionUID = 1753710836498190374L;

    @Resource
    private IChoiceController choiceController;

    /**
     * Construct a new AcEditLanguage.
     *
     * @param key The internationalization key.
     */
    public AcEditLanguage(String key) {
        super(key);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        choiceController.setAction("edit");
        choiceController.setContent(ILanguagesService.DATA_TYPE);
        choiceController.displayView();
    }
}