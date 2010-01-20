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
import org.jtheque.core.utils.CoreUtils;
import org.jtheque.primary.controller.able.ISimpleController;
import org.jtheque.primary.view.able.ISimpleDataView;

import java.awt.event.ActionEvent;

/**
 * An action to validate the kind view.
 *
 * @author Baptiste Wicht
 */
public final class ValidateSimpleDataViewAction extends JThequeAction {
	/**
	 * Construct a AcValidateKindView.
	 */
	public ValidateSimpleDataViewAction(){
		super("data.view.actions.ok");
	}

	@Override
	public void actionPerformed(ActionEvent e){
        ISimpleDataView simpleDataView = CoreUtils.getBean("simpleDataView");

		if (simpleDataView.validateContent()){
			CoreUtils.<ISimpleController>getBean(simpleDataView.getModel().getId()).save(simpleDataView.getDataName());

			simpleDataView.closeDown();
		}
	}
}