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
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.able.components.TabComponent;
import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.primary.controller.able.ControllerState;
import org.jtheque.primary.controller.able.IPrincipalController;
import org.jtheque.primary.od.able.Data;

/**
 * An abstract principal Controller.
 *
 * @author Baptiste Wicht
 */
public abstract class PrincipalController<T extends Data> extends AbstractController implements IPrincipalController<T> {
    private ControllerState state;
    private ControllerState viewState;
    private ControllerState modifyState;
    private ControllerState newObjectState;
    private ControllerState autoAddState;

    @Override
    public final void displayView() {
        Managers.getManager(IViewManager.class).getViews().setSelectedView((TabComponent) getView());

        super.displayView();
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

    /**
     * Set the current state.
     *
     * @param state The state to set.
     */
    protected final void setState(ControllerState state) {
        this.state = state;
    }

    /**
     * Set the view state of the controller.
     *
     * @param viewState The view state of the controller.
     */
    public void setViewState(ControllerState viewState) {
        this.viewState = viewState;
    }

    /**
     * Set the modify state of the controller.
     *
     * @param modifyState The modify state of the controller.
     */
    public void setModifyState(ControllerState modifyState) {
        this.modifyState = modifyState;
    }

    /**
     * Set the new object state of the controller.
     *
     * @param newObjectState The new object state of the controller.
     */
    public void setNewObjectState(ControllerState newObjectState) {
        this.newObjectState = newObjectState;
    }

    /**
     * Set the auto add state of the controller.
     *
     * @param autoAddState The auto add state of the controller.
     */
    public void setAutoAddState(ControllerState autoAddState) {
        this.autoAddState = autoAddState;
    }
}