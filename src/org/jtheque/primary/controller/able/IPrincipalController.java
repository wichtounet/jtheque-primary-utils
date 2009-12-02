package org.jtheque.primary.controller.able;

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

import org.jtheque.core.managers.view.able.controller.Controller;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.view.impl.models.able.IPrincipalDataModel;

import java.util.Collection;

/**
 * @author Baptiste Wicht
 */
public interface IPrincipalController<T extends Data> extends Controller {
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

    /**
     * Return the model of the view managed by the controller.
     *
     * @return The model of the view.
     */
    IPrincipalDataModel<T> getViewModel();

    /**
     * Return the associated data type.
     *
     * @return The associated data type.
     */
    String getDataType();

    /**
     * Return the display list.
     *
     * @return The display list.
     */
    Collection<T> getDisplayList();
}
