package org.jtheque.primary.view.impl.actions.principal;

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
import org.jtheque.core.utils.CoreUtils;
import org.jtheque.primary.controller.able.IPrincipalController;
import org.jtheque.primary.od.able.Data;

import java.awt.event.ActionEvent;

/**
 * Action to create a new film.
 *
 * @author Baptiste Wicht
 */
public final class CreateNewPrincipalAction extends JThequeAction {
	private final String controller;

	/**
	 * Construct a new CreateNewPrincipalAction.
	 *
	 * @param key The i18n key of the action.
	 * @param controller The controller to use.
	 */
	public CreateNewPrincipalAction(String key, String controller){
		super(key);

		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		CoreUtils.<IPrincipalController<? extends Data>>getBean(controller).create();
	}
}