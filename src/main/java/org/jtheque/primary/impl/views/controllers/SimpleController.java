package org.jtheque.primary.impl.views.controllers;

import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.able.services.ISimpleDataService;
import org.jtheque.primary.able.views.ISimpleDataView;
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

public class SimpleController extends AbstractController<ISimpleDataView> {
    @Resource
    private IUndoRedoService undoRedoService;

    private ViewMode state = ViewMode.NEW;
    private ISimpleDataService simpleDataService;

    public SimpleController() {
        super(ISimpleDataView.class);
    }

    @Override
    protected Map<String, String> getTranslations() {
        Map<String, String> translations = new HashMap<String, String>(7);

        translations.put("menu.new.kind", "newKind");
        translations.put("menu.new.type", "newType");
        translations.put("menu.new.language", "newLanguage");
        translations.put("menu.new.country", "newCountry");
        translations.put("menu.new.borrower", "newBorrower");
        translations.put("menu.new.saga", "newSaga");
        translations.put("data.view.actions.ok", "validate");
        translations.put("edit", "edit");
        translations.put("data.view.actions.cancel", "cancel");

        return translations;
    }

    private void newKind() {
        newSimple("kindsService");
    }

    private void newType() {
        newSimple("typesService");
    }

    private void newLanguage() {
        newSimple("languagesService");
    }

    private void newCountry() {
        newSimple("countriesService");
    }

    private void newBorrower() {
        newSimple("borrowersService");
    }

    private void newSaga() {
        newSimple("sagasService");
    }

    private void newSimple(String service) {
        state = ViewMode.NEW;

        simpleDataService = getContext().getBean(service, ISimpleDataService.class);

        getView().getModel().setSimpleData(simpleDataService.getEmptySimpleData());
        getView().reload();

        getView().display();
    }

    private void validate() {
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

    private void edit() {
        state = ViewMode.EDIT;

        getView().reload();

        getView().display();
    }

    private void cancel() {
        getView().closeDown();
    }
}
