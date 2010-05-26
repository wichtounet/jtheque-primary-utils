package org.jtheque.primary.utils.choice;

import org.osgi.framework.ServiceReference;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collection;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * A bean post processor to register all the resources of a module in the application context. This
 * post processor add automatically state bar components to state bar.
 *
 * @author Baptiste Wicht
 */
public class ChoiceActionsPostProcessor implements BeanPostProcessor {
	private final Collection<ChoiceAction> choiceActions = new ArrayList<ChoiceAction>(10);

	@Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof ChoiceAction){
	        ChoiceAction choiceAction = (ChoiceAction) bean;

	        choiceActions.add(choiceAction);

	        ChoiceActionFactory.addChoiceAction(choiceAction);
        }

        return bean;
    }

    /**
     * Remove the choice actions.
     */
	@PreDestroy
	public void removeChoiceActions(){
		for(ChoiceAction choiceAction : choiceActions){
			ChoiceActionFactory.removeChoiceAction(choiceAction);
		}
	}
}