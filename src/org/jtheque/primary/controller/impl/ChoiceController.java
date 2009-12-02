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

import org.jtheque.core.managers.view.able.IView;
import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.primary.controller.able.IChoiceController;
import org.jtheque.primary.view.able.IChoiceView;
import org.jtheque.primary.view.impl.choice.ChoiceAction;
import org.jtheque.primary.view.impl.choice.ChoiceActionFactory;

import javax.annotation.Resource;

/**
 * A Choice Controller.
 *
 * @author Baptiste Wicht
 */
public final class ChoiceController extends AbstractController implements IChoiceController {
    private String action;
    private String content;

    @Resource
    private IChoiceView choiceView;

    @Override
    public void doAction(Object selectedItem) {
        ChoiceAction choiceAction = ChoiceActionFactory.getChoiceAction(action);
        choiceAction.setSelectedItem(selectedItem);
        choiceAction.setContent(content);
        choiceAction.execute();

        closeView();
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void displayView() {
        choiceView.display(content);
    }

    @Override
    public IView getView() {
        return choiceView;
    }
}