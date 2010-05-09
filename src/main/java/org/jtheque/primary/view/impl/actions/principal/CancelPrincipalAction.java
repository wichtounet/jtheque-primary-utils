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

import org.jtheque.primary.controller.able.IPrincipalController;
import org.jtheque.primary.od.able.Data;
import org.jtheque.ui.utils.actions.JThequeAction;

import java.awt.event.ActionEvent;

/**
 * Action to cancel the film view.
 *
 * @author Baptiste Wicht
 */
public final class CancelPrincipalAction extends JThequeAction {
	private final IPrincipalController<? extends Data> controller;

	/**
	 * Construct a new CancelPrincipalAction.
	 *
	 * @param key The i18n key of the action.
	 * @param controller The controller of the action.
	 */
	public CancelPrincipalAction(String key, IPrincipalController<? extends Data> controller){
		super(key);

		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		controller.cancel();
	}
}