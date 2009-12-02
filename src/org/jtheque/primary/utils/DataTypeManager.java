package org.jtheque.primary.utils;

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

import java.util.HashMap;
import java.util.Map;

/**
 * A manager for the data type. This manager enable to to bind data type to internationalization key.
 *
 * @author Baptiste Wicht
 */
public final class DataTypeManager {
    private static final Map<String, String> DATA_TYPES = new HashMap<String, String>(12);

    /**
     * Construct a new DataTypeManager.
     */
    private DataTypeManager() {
        super();
    }

    /**
     * Bind a data type to a key.
     *
     * @param dataType The data type.
     * @param key      The internationalization key.
     */
    public static void bindDataTypeToKey(String dataType, String key) {
        DATA_TYPES.put(dataType, key);
    }

    /**
     * Unbind a data type.
     *
     * @param dataType The data type to remove from binding.
     */
    public static void unbindDataType(String dataType) {
        DATA_TYPES.remove(dataType);
    }

    /**
     * Return the internationalization key for a data type.
     *
     * @param dataType The data type.
     * @return The internationalization for the data type.
     */
    private static String getKeyForDataType(String dataType) {
        return DATA_TYPES.get(dataType);
    }

    /**
     * Return the text for a data type.
     *
     * @param dataType The data type.
     * @return The internationalized text for the data type.
     */
    public static String getTextForDataType(String dataType) {
        return Managers.getManager(ILanguageManager.class).getMessage(getKeyForDataType(dataType));
    }
}