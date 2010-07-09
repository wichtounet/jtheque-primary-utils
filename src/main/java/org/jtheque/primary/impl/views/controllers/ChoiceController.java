package org.jtheque.primary.impl.views.controllers;

import org.jtheque.primary.able.controller.IChoiceController;
import org.jtheque.primary.able.views.IChoiceView;
import org.jtheque.primary.utils.choice.ChoiceAction;
import org.jtheque.primary.utils.choice.ChoiceActionFactory;
import org.jtheque.ui.utils.AbstractController;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.jtheque.primary.able.od.SimpleData.DataType.*;
import static org.jtheque.primary.impl.PrimaryConstants.ChoiceActions.*;

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

public class ChoiceController extends AbstractController implements IChoiceController {
    @Resource
    private IChoiceView choiceView;

    private String action;
    private String content;

    @Override
    protected Map<String, String> getTranslations() {
        Map<String, String> translations = new HashMap<String, String>(8);

        translations.put("choice.actions.validate", "validate");
        translations.put("choice.actions.cancel", "cancel");
        translations.put("menu.delete.kind", "deleteKind");
        translations.put("menu.delete.type", "deleteType");
        translations.put("menu.delete.language", "deleteLanguage");
        translations.put("menu.delete.country", "deleteCountry");
        translations.put("menu.delete.borrower", "deleteBorrower");
        translations.put("menu.delete.saga", "deleteSaga");
        translations.put("menu.edit.kind", "editKind");
        translations.put("menu.edit.type", "editType");
        translations.put("menu.edit.language", "editLanguage");
        translations.put("menu.edit.country", "editCountry");
        translations.put("menu.edit.borrower", "editBorrower");
        translations.put("menu.edit.saga", "editSaga");

        return translations;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    private void editKind() {
        display(EDIT, KIND.getDataType());
    }

    private void editType() {
        display(EDIT, TYPE.getDataType());
    }

    private void editLanguage() {
        display(EDIT, LANGUAGE.getDataType());
    }

    private void editCountry() {
        display(EDIT, COUNTRY.getDataType());
    }

    private void editBorrower() {
        display(EDIT, "Borrowers");
    }

    private void editSaga() {
        display(EDIT, SAGA.getDataType());
    }

    private void deleteKind() {
        display(DELETE, KIND.getDataType());
    }

    private void deleteType() {
        display(DELETE, TYPE.getDataType());
    }

    private void deleteLanguage() {
        display(DELETE, LANGUAGE.getDataType());
    }

    private void deleteCountry() {
        display(DELETE, COUNTRY.getDataType());
    }

    private void deleteBorrower() {
        display(DELETE, "Borrowers");
    }

    private void deleteSaga(){
        display(DELETE, SAGA.getDataType());
    }

    private void cancel() {
        choiceView.closeDown();
    }

    private void validate(){
        if (choiceView.validateContent()) {
            ChoiceAction choiceAction = ChoiceActionFactory.getChoiceAction(action);
            choiceAction.setSelectedItem(choiceView.getSelectedItem());
            choiceAction.setContent(content);
            choiceAction.execute();
            
            choiceView.closeDown();
        }
    }

    private void display(String action, String content) {
        this.action = action;
        this.content = content;

        choiceView.display(content);
    }
}