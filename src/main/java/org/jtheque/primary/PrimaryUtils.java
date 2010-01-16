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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.feature.Feature;
import org.jtheque.core.managers.feature.IFeatureManager;
import org.jtheque.core.managers.feature.Menu;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.schema.ISchemaManager;
import org.jtheque.core.managers.schema.Schema;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.utils.DataTypeManager;

import java.util.List;

/**
 * The primary utils. This class give to modules some utilities methods to construct primary module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryUtils {
	private static final String BASE_NAME = "classpath:/org/jtheque/primary/i18n/utils";

	private static Schema schema;

	private static Menu menu;

	private static String primaryImpl;

	/**
	 * Construct a new PrimaryUtils.
	 */
	private PrimaryUtils(){
		super();
	}

	/**
	 * Preplug the elements of the utils.
	 */
	public static void prePlug(){
		schema = new PrimaryUtilsSchema();

		Managers.getManager(ISchemaManager.class).registerSchema(schema);

		Managers.getManager(ILanguageManager.class).addBaseName(BASE_NAME);
	}

	/**
	 * Plug the elements of the utils.
	 */
	public static void plug(){
		DataTypeManager.bindDataTypeToKey(PrimaryConstants.BORROWERS, "data.titles.borrower");
		DataTypeManager.bindDataTypeToKey(SimpleData.DataType.COUNTRY.getDataType(), "data.titles.country");
		DataTypeManager.bindDataTypeToKey(SimpleData.DataType.LANGUAGE.getDataType(), "data.titles.language");
		DataTypeManager.bindDataTypeToKey(SimpleData.DataType.TYPE.getDataType(), "type.data.title");
		DataTypeManager.bindDataTypeToKey(SimpleData.DataType.KIND.getDataType(), "kind.data.title");
		DataTypeManager.bindDataTypeToKey(SimpleData.DataType.SAGA.getDataType(), "saga.data.title");
	}

	/**
	 * Unplug the elements of the utils.
	 */
	public static void unplug(){
		DataTypeManager.unbindDataType(PrimaryConstants.BORROWERS);
		DataTypeManager.unbindDataType(SimpleData.DataType.COUNTRY.getDataType());
		DataTypeManager.unbindDataType(SimpleData.DataType.LANGUAGE.getDataType());
		DataTypeManager.unbindDataType(SimpleData.DataType.TYPE.getDataType());
		DataTypeManager.unbindDataType(SimpleData.DataType.KIND.getDataType());
		DataTypeManager.unbindDataType(SimpleData.DataType.SAGA.getDataType());

		Managers.getManager(ISchemaManager.class).unregisterSchema(schema);

		Managers.getManager(ILanguageManager.class).removeBaseName(BASE_NAME);

		if (menu != null){
			Managers.getManager(IFeatureManager.class).removeMenu(menu);
		}
	}

	/**
	 * Return the current primary implementation.
	 *
	 * @return The current primary implementation.
	 */
	public static String getPrimaryImpl(){
		return primaryImpl;
	}

	/**
	 * Set the current primary implementation.
	 *
	 * @param primaryImpl The current primary implementation.
	 */
	public static void setPrimaryImpl(String primaryImpl){
		PrimaryUtils.primaryImpl = primaryImpl;
	}

	/**
	 * Enable the menu of the primary utils module.
	 *
	 * @param addFeatures The sub features of the add menu.
	 * @param removeFeatures The sub features of the remove menu.
	 * @param editFeatures The sub features of the edit menu.
	 */
	public static void enableMenu(List<Feature> addFeatures, List<Feature> removeFeatures, List<Feature> editFeatures){
		menu = new PrimaryMenu(addFeatures, removeFeatures, editFeatures);
	}
}