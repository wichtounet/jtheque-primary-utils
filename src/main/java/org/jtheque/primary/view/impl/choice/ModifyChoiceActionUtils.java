package org.jtheque.primary.view.impl.choice;

import org.jtheque.core.utils.CoreUtils;
import org.jtheque.primary.PrimaryConstants;
import org.jtheque.primary.controller.able.IBorrowerController;
import org.jtheque.primary.controller.able.ISimpleController;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.able.SimpleData;

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

/**
 * An utility class for modify choice actions.
 *
 * @author Baptiste Wicht
 */
public final class ModifyChoiceActionUtils {
	/**
	 * Utility class, not instanciable.
	 */
	private ModifyChoiceActionUtils(){
		super();
	}

	/**
	 * Execute the action for the datas of the primary utils.
	 *
	 * @param item The item to execute the action on.
	 * @param content The current content.
	 *
	 * @return <code>true</code> if the primary choice action can execute this action on this
	 * content else <code>false</code>. 
	 */
	public static boolean execute(Object item, String content) {
        if (SimpleData.DataType.KIND.getDataType().equals(content)){
            CoreUtils.<ISimpleController>getBean("kindController").edit((SimpleData) item);

            return true;
        } else if (SimpleData.DataType.TYPE.getDataType().equals(content)) {
            CoreUtils.<ISimpleController>getBean("typeController").edit((SimpleData) item);

            return true;
        } else if (SimpleData.DataType.LANGUAGE.getDataType().equals(content)) {
            CoreUtils.<ISimpleController>getBean("languageController").edit((SimpleData) item);

            return true;
        } else if (SimpleData.DataType.COUNTRY.getDataType().equals(content)) {
            CoreUtils.<ISimpleController>getBean("countryController").edit((SimpleData) item);

            return true;
        } else if (PrimaryConstants.BORROWERS.equals(content)) {
            CoreUtils.<IBorrowerController>getBean("borrowerController").editBorrower((Person) item);

            return true;
        } else if (SimpleData.DataType.SAGA.getDataType().equals(content)) {
            CoreUtils.<ISimpleController>getBean("sagaController").edit((SimpleData) item);

            return true;
        }

        return false;
    }
}
