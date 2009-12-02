package org.jtheque.primary.view.impl.actions.type;

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
import org.jtheque.primary.controller.able.ITypeController;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to create a new type.
 *
 * @author Baptiste Wicht
 */
public final class AcNewType extends JThequeAction {
    private static final long serialVersionUID = 1831680737552781216L;

    @Resource
    private ITypeController typeController;

    /**
     * Construct a AcNewType.
     */
    public AcNewType() {
        super("actions.type");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        typeController.newType();
        typeController.displayView();
    }
}