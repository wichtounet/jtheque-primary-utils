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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.undo.IUndoRedoManager;
import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.primary.controller.able.ISagaController;
import org.jtheque.primary.controller.impl.undo.CreatedSagaEdit;
import org.jtheque.primary.od.able.Saga;
import org.jtheque.primary.services.able.ISagasService;
import org.jtheque.primary.view.able.ISagaView;
import org.jtheque.primary.view.able.ViewMode;

import javax.annotation.Resource;

/**
 * A controller for the saga view.
 *
 * @author Baptiste Wicht
 */
public final class SagaController extends AbstractController implements ISagaController {
    private ViewMode state = ViewMode.NEW;
    private Saga currentSaga;

    @Resource
    private ISagasService sagasService;

    @Resource
    private ISagaView sagaView;

    @Override
    public ISagaView getView() {
        return sagaView;
    }

    @Override
    public void newSaga() {
        state = ViewMode.NEW;

        sagaView.reload();
        currentSaga = sagasService.getEmptySaga();
    }

    @Override
    public void editSaga(Saga saga) {
        state = ViewMode.EDIT;

        sagaView.reload(saga);
        currentSaga = saga;

        displayView();
    }

    @Override
    public void save(String name) {
        currentSaga.setName(name);

        if (state == ViewMode.NEW) {
            sagasService.create(currentSaga);

            Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedSagaEdit(currentSaga));
        } else {
            sagasService.save(currentSaga);
        }
    }
}