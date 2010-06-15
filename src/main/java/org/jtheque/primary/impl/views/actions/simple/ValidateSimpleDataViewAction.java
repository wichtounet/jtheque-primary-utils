package org.jtheque.primary.impl.views.actions.simple;

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

import org.jtheque.primary.able.views.ISimpleDataView;
import org.jtheque.ui.utils.actions.JThequeAction;

import java.awt.event.ActionEvent;

/**
 * An action to validate the kind view.
 *
 * @author Baptiste Wicht
 */
public final class ValidateSimpleDataViewAction extends JThequeAction {
    private final ISimpleDataView simpleDataView;

    /**
     * Construct a AcValidateKindView.
     *
     * @param simpleDataView The simple data view.
     */
    public ValidateSimpleDataViewAction(ISimpleDataView simpleDataView) {
        super("data.view.actions.ok");

        this.simpleDataView = simpleDataView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (simpleDataView.validateContent()) {
            simpleDataView.getModel().getController().save(simpleDataView.getDataName());

            simpleDataView.closeDown();
        }
    }
}