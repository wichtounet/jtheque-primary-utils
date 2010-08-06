package org.jtheque.primary.impl.views.controllers;

import org.jtheque.primary.able.controller.IChoiceController;
import org.jtheque.primary.able.views.IChoiceView;
import org.jtheque.primary.utils.choice.ChoiceAction;
import org.jtheque.primary.utils.choice.ChoiceActionFactory;
import org.jtheque.ui.able.Action;
import org.jtheque.ui.utils.AbstractController;
import org.jtheque.utils.bean.Pair;
import org.jtheque.utils.collections.CollectionUtils;

import java.util.Map;

import static org.jtheque.primary.able.od.SimpleData.DataType.*;
import static org.jtheque.primary.impl.PrimaryConstants.ChoiceActions.DELETE;
import static org.jtheque.primary.impl.PrimaryConstants.ChoiceActions.EDIT;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ChoiceController extends AbstractController<IChoiceView> implements IChoiceController {
    private final Map<String, Pair<String, String>> actions = CollectionUtils.newHashMap(10);

    private String action;
    private String content;

    public ChoiceController() {
        super(IChoiceView.class);
    }

    @Override
    public void registerAction(String name, String action, String datatype) {
        actions.put(name, new Pair<String, String>(action, datatype));
    }

    @Override
    public void handleAction(String actionName) {
        if(actions.containsKey(actionName)){
            Pair<String, String> pair = actions.get(actionName);

            display(pair.getA(), pair.getB());
        } else {
            super.handleAction(actionName);
        }
    }

    @Action("menu.edit.kind")
    public void editKind() {
        display(EDIT, KIND.getDataType());
    }

    @Action("menu.edit.type")
    public void editType() {
        display(EDIT, TYPE.getDataType());
    }

    @Action("menu.edit.language")
    public void editLanguage() {
        display(EDIT, LANGUAGE.getDataType());
    }

    @Action("menu.edit.country")
    public void editCountry() {
        display(EDIT, COUNTRY.getDataType());
    }

    @Action("menu.edit.borrower")
    public void editBorrower() {
        display(EDIT, "Borrowers");
    }

    @Action("menu.edit.saga")
    public void editSaga() {
        display(EDIT, SAGA.getDataType());
    }

    @Action("menu.delete.kind")
    public void deleteKind() {
        display(DELETE, KIND.getDataType());
    }

    @Action("menu.delete.type")
    public void deleteType() {
        display(DELETE, TYPE.getDataType());
    }

    @Action("menu.delete.language")
    public void deleteLanguage() {
        display(DELETE, LANGUAGE.getDataType());
    }

    @Action("menu.delete.country")
    public void deleteCountry() {
        display(DELETE, COUNTRY.getDataType());
    }

    @Action("menu.delete.borrower")
    public void deleteBorrower() {
        display(DELETE, "Borrowers");
    }

    @Action("menu.delete.saga")
    public void deleteSaga(){
        display(DELETE, SAGA.getDataType());
    }

    @Action("choice.actions.cancel")
    public void cancel() {
        getView().closeDown();
    }

    @Action("choice.actions.validate")
    public void validate(){
        if (getView().validateContent()) {
            ChoiceAction choiceAction = ChoiceActionFactory.getChoiceAction(action);
            choiceAction.setSelectedItem(getView().getSelectedItem());
            choiceAction.setContent(content);
            choiceAction.execute();

            getView().closeDown();
        }
    }

    private void display(String action, String content) {
        this.action = action;
        this.content = content;

        getView().display(content);
    }
}