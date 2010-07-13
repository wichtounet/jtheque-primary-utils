package org.jtheque.primary.impl.views.controllers;

import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.able.services.IPersonService;
import org.jtheque.primary.able.views.IBorrowerView;
import org.jtheque.primary.able.views.ViewMode;
import org.jtheque.primary.utils.edits.GenericDataCreatedEdit;
import org.jtheque.ui.utils.AbstractController;
import org.jtheque.undo.able.IUndoRedoService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

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

public class BorrowerController extends AbstractController {
    @Resource
    private IBorrowerView borrowerView;

    @Resource
    private IPersonService borrowersService;

    @Resource
    private IUndoRedoService undoRedoService;

    private ViewMode state = ViewMode.NEW;

    @Override
    protected Map<String, String> getTranslations() {
        Map<String, String> translations = new HashMap<String, String>(2);

        translations.put("menu.others.borrower", "newBorrower");
        translations.put("borrower.actions.ok", "save");
        translations.put("borrower.actions.cancel", "cancel");
        translations.put("edit", "edit");

        return translations;
    }

    private void newBorrower() {
        state = ViewMode.NEW;

        borrowerView.getModel().setBorrower(borrowersService.getEmptyPerson());
        borrowerView.reload();
    }

    private void edit() {
        state = ViewMode.EDIT;

        borrowerView.reload();
        borrowerView.display();
    }

    private void save() {
        if (borrowerView.validateContent()) {
            Person borrower = borrowerView.getModel().getBorrower();

            borrower.setFirstName(borrowerView.getFieldFirstName().getText());
            borrower.setName(borrowerView.getFieldNom().getText());
            borrower.setEmail(borrowerView.getFieldEmail().getText());

            if (state == ViewMode.NEW) {
                borrowersService.create(borrower);

                undoRedoService.addEdit(new GenericDataCreatedEdit<Person>(borrowersService, borrower));
            } else {
                borrowersService.save(borrower);
            }

            borrowerView.closeDown();
        }
    }

    private void cancel(){
        borrowerView.closeDown();
    }
}