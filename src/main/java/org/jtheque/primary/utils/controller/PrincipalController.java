package org.jtheque.primary.utils.controller;

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

import org.jtheque.primary.able.controller.ControllerState;
import org.jtheque.primary.able.controller.FormBean;
import org.jtheque.primary.able.controller.IPrincipalController;
import org.jtheque.primary.able.od.Data;
import org.jtheque.ui.utils.AbstractController;
import org.jtheque.views.able.IViews;

import javax.annotation.Resource;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;

/**
 * An abstract principal Controller.
 *
 * @author Baptiste Wicht
 */
public abstract class PrincipalController<T extends Data> extends AbstractController implements IPrincipalController<T> {
    private final ControllerState viewState;
    private final ControllerState modifyState;
    private final ControllerState newObjectState;
    private final ControllerState autoAddState;

    private ControllerState state;

    @Resource
    private IViews views;

    /**
     * Construct a new PrincipalController.
     *
     * @param viewState      The view state.
     * @param modifyState    The modify state.
     * @param newObjectState The new state.
     * @param autoAddState   The auto add state.
     */
    protected PrincipalController(ControllerState viewState, ControllerState modifyState,
                                  ControllerState newObjectState, ControllerState autoAddState) {
        super();

        this.viewState = viewState;
        this.modifyState = modifyState;
        this.newObjectState = newObjectState;
        this.autoAddState = autoAddState;

        state = viewState;
    }

    @Override
    public final void valueChanged(TreeSelectionEvent event) {
        TreePath current = ((JTree) event.getSource()).getSelectionPath();

        if (current != null && current.getLastPathComponent() instanceof Data) {
            @SuppressWarnings("unchecked")
            T data = (T) current.getLastPathComponent();

            if (data != null) {
                view(data);
            }
        }
    }

    /**
     * Save the current data with the informations of the specified form bean.
     *
     * @param formBean The form bean.
     */
    protected final void save(FormBean formBean) {
        ControllerState newState = state.save(formBean);

        if (newState != null) {
            setAndApplyState(newState);
        }
    }

    /**
     * View the specified data.
     *
     * @param data The data to view.
     */
    protected final void view(T data) {
        ControllerState newState = state.view(data);

        if (newState != null) {
            setAndApplyState(newState);
        }
    }

    /**
     * Edit manually the current data.
     */
    protected final void manualEdit() {
        ControllerState newState = state.manualEdit();

        if (newState != null) {
            setAndApplyState(newState);
        }
    }

    /**
     * Create a new data.
     */
    protected final void create() {
        ControllerState newState = state.create();

        if (newState != null) {
            setAndApplyState(newState);
        }
    }

    /**
     * Delete the current data.
     */
    protected final void deleteCurrent() {
        ControllerState newState = state.delete();

        if (newState != null) {
            setAndApplyState(newState);
        }
    }

    /**
     * Cancel the current operation.
     */
    protected void cancel() {
        ControllerState newState = state.cancel();

        if (newState != null) {
            setAndApplyState(newState);
        }
    }

    @Override
    public final ControllerState getViewState() {
        return viewState;
    }

    @Override
    public final ControllerState getAutoAddState() {
        return autoAddState;
    }

    @Override
    public final ControllerState getModifyState() {
        return modifyState;
    }

    @Override
    public final ControllerState getNewObjectState() {
        return newObjectState;
    }

    /**
     * Return the state of the controller.
     *
     * @return The state of the controller.
     */
    protected final ControllerState getState() {
        return state;
    }

    /**
     * Set the state of the controller.
     *
     * @param state The new state to apply.
     */
    protected final void setAndApplyState(ControllerState state) {
        this.state = state;

        state.apply();
    }
}