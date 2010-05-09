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

import org.jtheque.primary.controller.able.ISimpleController;
import org.jtheque.primary.controller.impl.undo.GenericDataCreatedEdit;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.services.able.ISimpleDataService;
import org.jtheque.primary.view.able.ISimpleDataView;
import org.jtheque.primary.view.able.ViewMode;
import org.jtheque.undo.IUndoRedoService;
import org.jtheque.views.impl.AbstractController;

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
	private ISimpleDataView simpleDataView;

	@Resource
	private IUndoRedoService undoRedoService;

	/**
	 * Construct a new SimpleController.
	 *
	 * @param simpleService The simple service to use
	 */
	public SimpleController(ISimpleDataService simpleService){
		super();

		this.simpleService = simpleService;
    }

	@Override
	public void create(){
		state = ViewMode.NEW;

        simpleDataView.getModel().setCurrentController(this);
        simpleDataView.getModel().setSimpleData(simpleService.getEmptySimpleData());
		simpleDataView.reload();
	}

	@Override
	public void edit(SimpleData data){
		state = ViewMode.EDIT;

        simpleDataView.getModel().setCurrentController(this);
        simpleDataView.getModel().setSimpleData(data);
		simpleDataView.reload();

		displayView();
	}

	@Override
	public void save(String name){
		simpleDataView.getModel().getSimpleData().setName(name);

		if (state == ViewMode.NEW){
			simpleService.create(simpleDataView.getModel().getSimpleData());

			undoRedoService.addEdit(new GenericDataCreatedEdit<SimpleData>(simpleService, simpleDataView.getModel().getSimpleData()));
		} else {
			simpleService.save(simpleDataView.getModel().getSimpleData());
		}
	}

	@Override
	public ISimpleDataView getView(){
		return simpleDataView;
	}
}