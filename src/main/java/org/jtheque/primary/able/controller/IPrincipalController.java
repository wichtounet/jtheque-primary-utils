package org.jtheque.primary.able.controller;

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

import org.jtheque.primary.able.od.Data;
import org.jtheque.ui.Controller;
import org.jtheque.ui.View;

import javax.swing.event.TreeSelectionListener;

/**
 * @author Baptiste Wicht
 */
public interface IPrincipalController<T extends Data, V extends View> extends Controller<V>, TreeSelectionListener {
    /**
     * Return the state state. This state is active when we show a data on the view.
     *
     * @return a new state to set or <code>null</code> if we doesn't must change state.
     */
    ControllerState getViewState();

    /**
     * Return the auto add state. This state is active when we add automatically a data.
     *
     * @return a new state to set or <code>null</code> if we doesn't must change state.
     */
    ControllerState getAutoAddState();

    /**
     * Return the modify state. This state is active when we modify a data.
     *
     * @return a new state to set or <code>null</code> if we doesn't must change state.
     */
    ControllerState getModifyState();

    /**
     * Return the new object state. This state is active when we add a new data.
     *
     * @return a new state to set or <code>null</code> if we doesn't must change state.
     */
    ControllerState getNewObjectState();

    void save();
}
