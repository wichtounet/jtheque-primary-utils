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

import org.jtheque.core.managers.feature.AbstractMenu;
import org.jtheque.core.managers.feature.Feature;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.view.impl.actions.borrower.AcNewBorrower;
import org.jtheque.primary.view.impl.actions.choice.ChoiceViewAction;
import org.jtheque.primary.view.impl.actions.simple.NewSimpleDataAction;
import org.jtheque.utils.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * The menu of the primary utils module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryMenu extends AbstractMenu {
	private final Collection<Feature> addFeatures;
	private final Collection<Feature> removeFeatures;
	private final Collection<Feature> editFeatures;

	/**
	 * Construct a new PrimaryMenu.
	 *
	 * @param addFeatures The sub features of the add menu.
	 * @param removeFeatures The sub features of the remove menu.
	 * @param editFeatures The sub features of the edit menu.
	 */
	public PrimaryMenu(Collection<Feature> addFeatures, Collection<Feature> removeFeatures, Collection<Feature> editFeatures){
		super();

		this.addFeatures = CollectionUtils.copyOf(addFeatures);
		this.removeFeatures = CollectionUtils.copyOf(removeFeatures);
		this.editFeatures = CollectionUtils.copyOf(editFeatures);
	}

	@Override
	protected List<Feature> getMenuMainFeatures(){
		return features(
				createMainFeature(500, "actions.others",
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
		Feature newFeature = createSubFeature(1, "actions.others.new",
				createSubFeature(1, new NewSimpleDataAction("menu.others.kind", "kindController")),
				createSubFeature(2, new NewSimpleDataAction("menu.others.type", "typeController")),
				createSubFeature(3, new NewSimpleDataAction("menu.others.language", "languageController")),
				createSubFeature(4, new NewSimpleDataAction("menu.others.country", "countryController")),
				createSubFeature(5, new AcNewBorrower()),
				createSubFeature(6, new NewSimpleDataAction("menu.others.saga", "sagaController")));

		fillFeature(newFeature, addFeatures);

		return newFeature;
	}

	/**
	 * Create the delete menu.
	 *
	 * @return The Feature for the delete menu.
	 */
	private Feature createDeleteFeature(){
		Feature deleteFeature = createSubFeature(2, "actions.others.delete",
				createSubFeature(1, new ChoiceViewAction("menu.others.kind", PrimaryConstants.ChoiceActions.DELETE, SimpleData.DataType.KIND.getDataType())),
				createSubFeature(2, new ChoiceViewAction("menu.others.type", PrimaryConstants.ChoiceActions.DELETE, SimpleData.DataType.TYPE.getDataType())),
				createSubFeature(3, new ChoiceViewAction("menu.others.language", PrimaryConstants.ChoiceActions.DELETE, SimpleData.DataType.LANGUAGE.getDataType())),
				createSubFeature(4, new ChoiceViewAction("menu.others.country", PrimaryConstants.ChoiceActions.DELETE, SimpleData.DataType.COUNTRY.getDataType())),
				createSubFeature(5, new ChoiceViewAction("menu.others.borrower", PrimaryConstants.ChoiceActions.DELETE, "Borrowers")),
				createSubFeature(6, new ChoiceViewAction("menu.others.saga", PrimaryConstants.ChoiceActions.DELETE, SimpleData.DataType.SAGA.getDataType())));


		fillFeature(deleteFeature, removeFeatures);

		return deleteFeature;
	}

	/**
	 * Create the edit menu.
	 *
	 * @return The Feature for the edit menu.
	 */
	private Feature createEditFeature(){
		Feature editFeature = createSubFeature(3, "actions.others.modify",
				createSubFeature(1, new ChoiceViewAction("menu.others.kind", PrimaryConstants.ChoiceActions.EDIT, SimpleData.DataType.KIND.getDataType())),
				createSubFeature(2, new ChoiceViewAction("menu.others.type", PrimaryConstants.ChoiceActions.EDIT, SimpleData.DataType.TYPE.getDataType())),
				createSubFeature(3, new ChoiceViewAction("menu.others.language", PrimaryConstants.ChoiceActions.EDIT, SimpleData.DataType.LANGUAGE.getDataType())),
				createSubFeature(4, new ChoiceViewAction("menu.others.country", PrimaryConstants.ChoiceActions.EDIT, SimpleData.DataType.COUNTRY.getDataType())),
				createSubFeature(5, new ChoiceViewAction("menu.others.borrower", PrimaryConstants.ChoiceActions.EDIT, "Borrowers")),
				createSubFeature(6, new ChoiceViewAction("menu.others.saga", PrimaryConstants.ChoiceActions.EDIT, SimpleData.DataType.SAGA.getDataType())));

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
