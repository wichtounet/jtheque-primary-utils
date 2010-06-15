package org.jtheque.primary.impl.controller;

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

import org.jtheque.primary.able.controller.ISimpleController;
import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.able.services.ISimpleDataService;
import org.jtheque.primary.able.views.ISimpleDataView;
import org.jtheque.primary.able.views.ViewMode;
import org.jtheque.primary.utils.edits.GenericDataCreatedEdit;
import org.jtheque.spring.utils.SwingSpringProxy;
import org.jtheque.undo.able.IUndoRedoService;
import org.jtheque.views.utils.AbstractController;

import javax.annotation.Resource;

/**
 * A Country Controller.
 *
 * @author Baptiste Wicht
 */
public final class SimpleController extends AbstractController implements ISimpleController {
    private ViewMode state = ViewMode.NEW;

    private final ISimpleDataService simpleService;

    @Resource
    private IUndoRedoService undoRedoService;

    private final SwingSpringProxy<ISimpleDataView> simpleDataView;

    /**
     * Construct a new SimpleController.
     *
     * @param simpleService  The simple service to use.
     * @param simpleDataView The simple data view.
     */
    public SimpleController(ISimpleDataService simpleService, SwingSpringProxy<ISimpleDataView> simpleDataView) {
        super();

        this.simpleService = simpleService;
        this.simpleDataView = simpleDataView;
    }

    @Override
    public void create() {
        state = ViewMode.NEW;

        simpleDataView.get().getModel().setCurrentController(this);
        simpleDataView.get().getModel().setSimpleData(simpleService.getEmptySimpleData());
        simpleDataView.get().reload();
    }

    @Override
    public void edit(SimpleData data) {
        state = ViewMode.EDIT;

        simpleDataView.get().getModel().setCurrentController(this);
        simpleDataView.get().getModel().setSimpleData(data);
        simpleDataView.get().reload();

        displayView();
    }

    @Override
    public void save(String name) {
        simpleDataView.get().getModel().getSimpleData().setName(name);

        if (state == ViewMode.NEW) {
            simpleService.create(simpleDataView.get().getModel().getSimpleData());

            undoRedoService.addEdit(new GenericDataCreatedEdit<SimpleData>(simpleService, simpleDataView.get().getModel().getSimpleData()));
        } else {
            simpleService.save(simpleDataView.get().getModel().getSimpleData());
        }
    }

    @Override
    public ISimpleDataView getView() {
        return simpleDataView.get();
    }
}