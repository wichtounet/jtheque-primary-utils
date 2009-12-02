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
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.schema.ISchemaManager;
import org.jtheque.core.managers.schema.Schema;
import org.jtheque.primary.services.able.IBorrowersService;
import org.jtheque.primary.services.able.ICountriesService;
import org.jtheque.primary.services.able.ILanguagesService;
import org.jtheque.primary.utils.DataTypeManager;

/**
 * The primary utils. This class give to modules some utilities methods to construct primary module.
 *
 * @author Baptiste Wicht
 */
public final class PrimaryUtils {
    private static final String BASE_NAME = "classpath:/org/jtheque/primary/ressources/i18n/utils";

    private static Schema schema;

    private static String primaryImpl;

    /**
     * Construct a new PrimaryUtils.
     */
    private PrimaryUtils() {
        super();
    }

    /**
     * Preplug the elements of the utils.
     */
    public static void prePlug() {
        schema = new PrimaryUtilsSchema();

        Managers.getManager(ISchemaManager.class).registerSchema(schema);

        Managers.getManager(ILanguageManager.class).addBaseName(BASE_NAME);
    }

    /**
     * Plug the elements of the utils.
     */
    public static void plug() {
        DataTypeManager.bindDataTypeToKey(IBorrowersService.DATA_TYPE, "data.titles.borrower");
        DataTypeManager.bindDataTypeToKey(ICountriesService.DATA_TYPE, "data.titles.country");
        DataTypeManager.bindDataTypeToKey(ILanguagesService.DATA_TYPE, "data.titles.language");
    }

    /**
     * Unplug the elements of the utils.
     */
    public static void unplug() {
        DataTypeManager.unbindDataType(IBorrowersService.DATA_TYPE);
        DataTypeManager.unbindDataType(ICountriesService.DATA_TYPE);
        DataTypeManager.unbindDataType(ILanguagesService.DATA_TYPE);

        Managers.getManager(ISchemaManager.class).unregisterSchema(schema);

        Managers.getManager(ILanguageManager.class).removeBaseName(BASE_NAME);
    }

    /**
     * Return the current primary implementation.
     *
     * @return The current primary implementation.
     */
    public static String getPrimaryImpl() {
        return primaryImpl;
    }

    /**
     * Set the current primary implementation.
     *
     * @param primaryImpl The current primary implementation.
     */
    public static void setPrimaryImpl(String primaryImpl) {
        PrimaryUtils.primaryImpl = primaryImpl;
    }
}