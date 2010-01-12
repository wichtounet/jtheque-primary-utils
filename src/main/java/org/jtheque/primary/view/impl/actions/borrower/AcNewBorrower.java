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
import org.jtheque.primary.controller.able.IBorrowerController;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to edit a borrower.
 *
 * @author Baptiste Wicht
 */
public final class AcNewBorrower extends JThequeAction {
    @Resource
    private IBorrowerController borrowerController;

    /**
     * Construct a new AcNewBorrower.
     *
     */
    public AcNewBorrower() {
        super("menu.others.borrower");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        borrowerController.newBorrower();
        borrowerController.displayView();
    }
}