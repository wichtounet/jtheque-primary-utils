package org.jtheque.primary.utils.choice;

import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.able.views.IBorrowerView;
import org.jtheque.primary.able.views.ISimpleDataView;
import org.jtheque.primary.impl.PrimaryConstants;
import org.jtheque.ui.Controller;
import org.jtheque.utils.StringUtils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.jtheque.primary.able.od.SimpleData.DataType.*;

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
     * @param item    The item to execute the action on.
     * @param content The current content.
     *
     * @return <code>true</code> if the primary choice action can execute this action on this content else
     *         <code>false</code>.
     */
    public boolean execute(Object item, String content) {
        if (StringUtils.equalsOneOf(content, KIND.getDataType(), TYPE.getDataType(), LANGUAGE.getDataType(),
                COUNTRY.getDataType(), SAGA.getDataType())) {
            applicationContext.getBean("simpleDataView", ISimpleDataView.class).getModel().setSimpleData((SimpleData) item);
            applicationContext.getBean("simpleController", Controller.class).handleAction("edit");

            return true;
        } else if (PrimaryConstants.BORROWERS.equals(content)) {
            applicationContext.getBean("borrowerView", IBorrowerView.class).getModel().setBorrower((Person) item);
            applicationContext.getBean("borrowerController", Controller.class).handleAction("edit");

            return true;
        }

        return false;
    }

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
