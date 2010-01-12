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

import org.jtheque.primary.od.able.Data;

/**
 * Represents a state of a controller. This is for the controllers of principal datas. The state can change when
 * a user interaction occurs or a state can be changed by an other state.
 *
 * @author Baptiste Wicht
 */
public interface ControllerState {
    /**
     * Apply the state.
     */
    void apply();

    /**
     * Save the current data.
     *
     * @param infos The informations of the interface
     * @return The new <code>ControllerState</code> or <code>null</code>
     */
    ControllerState save(FormBean infos);

    /**
     * Cancel the current state.
     *
     * @return The new <code>ControllerState</code> or <code>null</code>
     */
    ControllerState cancel();

    /**
     * Create a new object.
     *
     * @return The new <code>ControllerState</code> or <code>null</code>
     */
    ControllerState create();

    /**
     * Delete the current data.
     *
     * @return The new <code>ControllerState</code> or <code>null</code>
     */
    ControllerState delete();

    /**
     * Edit manually the current data.
     *
     * @return The new <code>ControllerState</code> or <code>null</code>
     */
    ControllerState manualEdit();

    /**
     * Display a data.
     *
     * @param data the data to display.
     * @return The new <code>ControllerState</code> or <code>null</code>
     */
    ControllerState view(Data data);
}