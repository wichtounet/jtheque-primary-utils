package org.jtheque.primary.view.impl.actions.simple;

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
import org.jtheque.primary.controller.able.ISimpleController;
import org.jtheque.primary.view.able.ISimpleDataView;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to validate the kind view.
 *
 * @author Baptiste Wicht
 */
public final class ValidateSimpleDataViewAction extends JThequeAction {
	@Resource
	private ISimpleController simpleController;

	/**
	 * Construct a AcValidateKindView.
	 */
	public ValidateSimpleDataViewAction(){
		super("kind.actions.ok");
	}

	@Override
	public void actionPerformed(ActionEvent e){
		ISimpleDataView view = simpleController.getView();

		if (view.validateContent()){
			simpleController.save(view.getDataName());

			view.closeDown();
		}
	}
}