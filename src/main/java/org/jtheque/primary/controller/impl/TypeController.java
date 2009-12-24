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
import org.jtheque.primary.controller.able.ITypeController;
import org.jtheque.primary.controller.impl.undo.CreatedTypeEdit;
import org.jtheque.primary.od.able.Type;
import org.jtheque.primary.services.able.ITypesService;
import org.jtheque.primary.view.able.ITypeView;
import org.jtheque.primary.view.able.ViewMode;

import javax.annotation.Resource;

/**
 * A controller for the type view.
 *
 * @author Baptiste Wicht
 */
public final class TypeController extends AbstractController implements ITypeController {
    private ViewMode state = ViewMode.NEW;
    private Type currentType;

    @Resource
    private ITypesService typesService;

    @Resource
    private ITypeView typeView;

    @Override
    public ITypeView getView() {
        return typeView;
    }

    @Override
    public void newType() {
        state = ViewMode.NEW;

        typeView.reload();
        currentType = typesService.getEmptyType();
    }

    @Override
    public void editType(Type type) {
        state = ViewMode.EDIT;

        typeView.reload(type);
        currentType = type;

        displayView();
    }

    @Override
    public void save(String name) {
        currentType.setName(name);

        if (state == ViewMode.NEW) {
            typesService.create(currentType);

            Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedTypeEdit(currentType));
        } else {
            typesService.save(currentType);
        }
    }
}