package org.jtheque.primary.view.impl.actions.borrower;

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

import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.primary.controller.able.IChoiceController;
import org.jtheque.primary.services.able.IBorrowersService;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to delete a borrower.
 *
 * @author Baptiste Wicht
 */
public final class AcDeleteBorrower extends JThequeAction {
    private static final long serialVersionUID = -7362063035798793006L;

    @Resource
    private IChoiceController choiceController;

    /**
     * Construct a new AcDeleteBorrower.
     *
     * @param key The internationalization key.
     */
    public AcDeleteBorrower(String key) {
        super(key);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        choiceController.setAction("delete");
        choiceController.setContent(IBorrowersService.DATA_TYPE);
        choiceController.displayView();
    }
}