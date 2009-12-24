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
import org.jtheque.primary.controller.able.IKindController;
import org.jtheque.primary.controller.impl.undo.CreatedKindEdit;
import org.jtheque.primary.od.able.Kind;
import org.jtheque.primary.services.able.IKindsService;
import org.jtheque.primary.view.able.IKindView;
import org.jtheque.primary.view.able.ViewMode;

import javax.annotation.Resource;

/**
 * The controller for the kind view.
 *
 * @author Baptiste Wicht
 */
public final class KindController extends AbstractController implements IKindController {
    private ViewMode state = ViewMode.NEW;
    private Kind currentKind;

    @Resource
    private IKindsService kindsService;

    @Resource
    private IKindView kindView;

    @Override
    public IKindView getView() {
        return kindView;
    }

    @Override
    public void newKind() {
        state = ViewMode.NEW;

        kindView.reload();
        currentKind = kindsService.getEmptyKind();
    }

    @Override
    public void editKind(Kind kind) {
        state = ViewMode.EDIT;

        kindView.reload(kind);
        currentKind = kind;

        displayView();
    }

    @Override
    public void save(String name) {
        currentKind.setName(name);

        if (state == ViewMode.NEW) {
            kindsService.create(currentKind);

            Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedKindEdit(currentKind));
        } else {
            kindsService.save(currentKind);
        }
    }
}