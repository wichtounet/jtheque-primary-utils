package org.jtheque.primary.impl.views.controllers;

import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.able.services.ISimpleDataService;
import org.jtheque.primary.able.views.ISimpleDataView;
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

public class SimpleController extends AbstractController<ISimpleDataView> {
    @Resource
    private IUndoRedoService undoRedoService;

    private ViewMode state = ViewMode.NEW;
    private ISimpleDataService simpleDataService;

    public SimpleController() {
        super(ISimpleDataView.class);
    }

    @Action("menu.new.kind")
    public void newKind() {
        newSimple("kindsService");
    }

    @Action("menu.new.type")
    public void newType() {
        newSimple("typesService");
    }

    @Action("menu.new.language")
    public void newLanguage() {
        newSimple("languagesService");
    }

    @Action("menu.new.country")
    public void newCountry() {
        newSimple("countriesService");
    }

    @Action("menu.new.borrower")
    public void newBorrower() {
        newSimple("borrowersService");
    }

    @Action("menu.new.saga")
    public void newSaga() {
        newSimple("sagasService");
    }

    private void newSimple(String service) {
        state = ViewMode.NEW;

        simpleDataService = getContext().getBean(service, ISimpleDataService.class);

        getView().getModel().setSimpleData(simpleDataService.getEmptySimpleData());
        getView().reload();

        getView().display();
    }

    @Action("data.view.actions.ok")
    public void validate() {
        if (getView().validateContent()) {
            getView().getModel().getSimpleData().setName(getView().getDataName());

            if (state == ViewMode.NEW) {
                simpleDataService.create(getView().getModel().getSimpleData());

                undoRedoService.addEdit(new GenericDataCreatedEdit<SimpleData>(
                        simpleDataService, getView().getModel().getSimpleData()));
            } else {
                simpleDataService.save(getView().getModel().getSimpleData());
            }

            getView().closeDown();
        }
    }

    @Action("edit")
    public void edit() {
        state = ViewMode.EDIT;

        getView().reload();

        getView().display();
    }

    @Action("data.view.actions.cancel")
    public void cancel() {
        getView().closeDown();
    }
}
