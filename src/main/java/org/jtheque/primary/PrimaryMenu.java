package org.jtheque.primary;

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

import org.jtheque.features.Feature;
import org.jtheque.primary.controller.able.IBorrowerController;
import org.jtheque.primary.controller.able.IChoiceController;
import org.jtheque.primary.controller.able.ISimpleController;
import org.jtheque.primary.view.impl.actions.borrower.AcNewBorrower;
import org.jtheque.primary.view.impl.actions.choice.ChoiceViewAction;
import org.jtheque.primary.view.impl.actions.simple.NewSimpleDataAction;
import org.jtheque.utils.collections.CollectionUtils;
import org.jtheque.views.impl.components.menu.AbstractMenu;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.List;

import static org.jtheque.primary.PrimaryConstants.ChoiceActions.*;
import static org.jtheque.primary.od.able.SimpleData.DataType.*;

/**
 * The menu of the primary utils module.
 *
 * @author Baptiste Wicht
 */
final class PrimaryMenu extends AbstractMenu {
	private final Collection<Feature> addFeatures;
	private final Collection<Feature> removeFeatures;
	private final Collection<Feature> editFeatures;

	private final ApplicationContext applicationContext;

	/**
	 * Construct a new PrimaryMenu.
	 *
	 * @param addFeatures The sub features of the add menu.
	 * @param removeFeatures The sub features of the remove menu.
	 * @param editFeatures The sub features of the edit menu.
	 */
	PrimaryMenu(Collection<Feature> addFeatures, Collection<Feature> removeFeatures, Collection<Feature> editFeatures,
	            ApplicationContext applicationContext){
		super();

		this.applicationContext = applicationContext;

		this.addFeatures = CollectionUtils.copyOf(addFeatures);
		this.removeFeatures = CollectionUtils.copyOf(removeFeatures);
		this.editFeatures = CollectionUtils.copyOf(editFeatures);
	}

	@Override
	protected List<Feature> getMenuMainFeatures(){
		return features(
				createMainFeature(500, "menu.others",
						createNewFeature(),
						createDeleteFeature(),
						createEditFeature())
		);
	}

	/**
	 * Create the new menu.
	 *
	 * @return The Feature for the new menu.
	 */
	private Feature createNewFeature(){
		Feature newFeature = createSubFeature(1, "menu.others.new",
				createSubFeature(1, new NewSimpleDataAction("menu.others.kind", applicationContext.getBean("kindController", ISimpleController.class))),
				createSubFeature(2, new NewSimpleDataAction("menu.others.type", applicationContext.getBean("typeController", ISimpleController.class))),
				createSubFeature(3, new NewSimpleDataAction("menu.others.language", applicationContext.getBean("languageController", ISimpleController.class))),
				createSubFeature(4, new NewSimpleDataAction("menu.others.country", applicationContext.getBean("countryController", ISimpleController.class))),
				createSubFeature(5, new AcNewBorrower(applicationContext.getBean("borrowerController", IBorrowerController.class))),
				createSubFeature(6, new NewSimpleDataAction("menu.others.saga", applicationContext.getBean("sagaController", ISimpleController.class))));

		fillFeature(newFeature, addFeatures);

		return newFeature;
	}

	/**
	 * Create the delete menu.
	 *
	 * @return The Feature for the delete menu.
	 */
	private Feature createDeleteFeature(){
		IChoiceController choiceController = applicationContext.getBean("choiceController", IChoiceController.class);

		Feature deleteFeature = createSubFeature(2, "menu.others.delete",
				createSubFeature(1, new ChoiceViewAction("menu.others.kind", DELETE, KIND.getDataType(), choiceController)),
				createSubFeature(2, new ChoiceViewAction("menu.others.type", DELETE, TYPE.getDataType(), choiceController)),
				createSubFeature(3, new ChoiceViewAction("menu.others.language", DELETE, LANGUAGE.getDataType(), choiceController)),
				createSubFeature(4, new ChoiceViewAction("menu.others.country", DELETE, COUNTRY.getDataType(), choiceController)),
				createSubFeature(5, new ChoiceViewAction("menu.others.borrower", DELETE, "Borrowers", choiceController)),
				createSubFeature(6, new ChoiceViewAction("menu.others.saga", DELETE, SAGA.getDataType(), choiceController)));

		fillFeature(deleteFeature, removeFeatures);

		return deleteFeature;
	}

	/**
	 * Create the edit menu.
	 *
	 * @return The Feature for the edit menu.
	 */
	private Feature createEditFeature(){
		IChoiceController choiceController = applicationContext.getBean("choiceController", IChoiceController.class);
		
		Feature editFeature = createSubFeature(3, "menu.others.edit",
				createSubFeature(1, new ChoiceViewAction("menu.others.kind", EDIT, KIND.getDataType(), choiceController)),
				createSubFeature(2, new ChoiceViewAction("menu.others.type", EDIT, TYPE.getDataType(), choiceController)),
				createSubFeature(3, new ChoiceViewAction("menu.others.language", EDIT, LANGUAGE.getDataType(), choiceController)),
				createSubFeature(4, new ChoiceViewAction("menu.others.country", EDIT, COUNTRY.getDataType(), choiceController)),
				createSubFeature(5, new ChoiceViewAction("menu.others.borrower", EDIT, "Borrowers", choiceController)),
				createSubFeature(6, new ChoiceViewAction("menu.others.saga", EDIT, SAGA.getDataType(), choiceController)));

		fillFeature(editFeature, editFeatures);

		return editFeature;
	}

	/**
	 * Fill the feature with all the features specified.
	 *
	 * @param feature The feature to fill.
	 * @param features The sub features to add.
	 */
	private static void fillFeature(Feature feature, Iterable<Feature> features){
		for (Feature f : features){
			feature.addSubFeature(f);
		}
	}
}
