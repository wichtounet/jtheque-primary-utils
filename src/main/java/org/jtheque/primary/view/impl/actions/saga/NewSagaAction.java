package org.jtheque.primary.view.impl.actions.saga;

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
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.primary.controller.able.ISagaController;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to create a new saga.
 *
 * @author Baptiste Wicht
 */
public final class NewSagaAction extends JThequeAction {
    private static final long serialVersionUID = -4986384249077065534L;
    
    /**
     * Construct a NewKindAction.
     */
    public NewSagaAction() {
        super("saga.action.new");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ISagaController sagaController = Managers.getManager(IBeansManager.class).getBean("sagaController");
        
        sagaController.newSaga();
        sagaController.displayView();
    }
}