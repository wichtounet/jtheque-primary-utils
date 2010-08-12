package org.jtheque.primary.impl.views.controllers;

import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.able.services.IPersonService;
import org.jtheque.primary.able.views.IBorrowerView;
import org.jtheque.primary.able.views.ViewMode;
import org.jtheque.primary.utils.edits.GenericDataCreatedEdit;
import org.jtheque.ui.Action;
import org.jtheque.ui.utils.AbstractController;
import org.jtheque.undo.IUndoRedoService;

import javax.annotation.Resource;

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

public class BorrowerController extends AbstractController<IBorrowerView> {
    @Resource
    private IPersonService borrowersService;

    @Resource
    private IUndoRedoService undoRedoService;

    private ViewMode state = ViewMode.NEW;

    public BorrowerController() {
        super(IBorrowerView.class);
    }

    @Action("menu.others.borrower")
    public void newBorrower() {
        state = ViewMode.NEW;

        getView().getModel().setBorrower(borrowersService.getEmptyPerson());
        getView().reload();
    }

    @Action("edit")
    public void edit() {
        state = ViewMode.EDIT;

        getView().reload();
        getView().display();
    }

    @Action("menu.others.ok")
    public void save() {
        if (getView().validateContent()) {
            Person borrower = getView().getModel().getBorrower();

            borrower.setFirstName(getView().getFieldFirstName().getText());
            borrower.setName(getView().getFieldNom().getText());
            borrower.setEmail(getView().getFieldEmail().getText());

            if (state == ViewMode.NEW) {
                borrowersService.create(borrower);

                undoRedoService.addEdit(new GenericDataCreatedEdit<Person>(borrowersService, borrower));
            } else {
                borrowersService.save(borrower);
            }

            getView().closeDown();
        }
    }

    @Action("borrower.actions.cancel")
    public void cancel() {
        getView().closeDown();
    }
}