package org.jtheque.primary.utils.choice;

import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.impl.PrimaryConstants;
import org.jtheque.primary.able.controller.IBorrowerController;
import org.jtheque.primary.able.controller.ISimpleController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
public abstract class AbstractModifyChoiceAction extends AbstractChoiceAction implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	
	/**
	 * Execute the action for the datas of the primary utils.
	 *
	 * @param item The item to execute the action on.
	 * @param content The current content.
	 *
	 * @return <code>true</code> if the primary choice action can execute this action on this
	 * content else <code>false</code>. 
	 */
	public boolean execute(Object item, String content) {
        if (SimpleData.DataType.KIND.getDataType().equals(content)){
            applicationContext.getBean("kindController", ISimpleController.class).edit((SimpleData) item);

            return true;
        } else if (SimpleData.DataType.TYPE.getDataType().equals(content)) {
            applicationContext.getBean("typeController", ISimpleController.class).edit((SimpleData) item);

            return true;
        } else if (SimpleData.DataType.LANGUAGE.getDataType().equals(content)) {
            applicationContext.getBean("languageController", ISimpleController.class).edit((SimpleData) item);

            return true;
        } else if (SimpleData.DataType.COUNTRY.getDataType().equals(content)) {
            applicationContext.getBean("countryController", ISimpleController.class).edit((SimpleData) item);

            return true;
        } else if (PrimaryConstants.BORROWERS.equals(content)) {
            applicationContext.getBean("borrowerController", IBorrowerController.class).editBorrower((Person) item);

            return true;
        } else if (SimpleData.DataType.SAGA.getDataType().equals(content)) {
            applicationContext.getBean("sagaController", ISimpleController.class).edit((SimpleData) item);

            return true;
        }

        return false;
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
}
