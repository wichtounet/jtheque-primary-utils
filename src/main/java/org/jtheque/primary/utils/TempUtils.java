package org.jtheque.primary.utils;

import org.jtheque.utils.Constants;
import org.jtheque.utils.bean.EqualsUtils;

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
 * A temporary utility class. Its methods will be moved to JTheque Utils on the last
 * version of this last.
 *
 * @author Baptiste Wicht
 */
public final class TempUtils {
	private static final int NUMBER_BIT_LENGTH = 32;

	/**
	 * Utility class, not instanciable.
	 */
	private TempUtils(){
		super();
	}

	/**
	 * Return the hash code of an object, using the properties in the given list.
	 *
	 * @param properties The properties to use to generate the hash code.
	 *
	 * @return The hash code of the bean . If there is no properties, the hash code will be 17.
	 */
	public static int hashCodeDirect(Object... properties){
		int result = Constants.HASH_CODE_START;

		for (Object property : properties){
			result = computeValue(result, property);
		}

		return result;
	}

	/**
	 * Compute the value with the current result.
	 *
	 * @param result The current hash code result.
	 * @param value The value to compute to the result.
	 *
	 * @return The result computed with the value.
	 */
	private static int computeValue(int result, Object value){
		if (value instanceof Double){
			Long temp = Double.doubleToLongBits((Double) value);
			return Constants.HASH_CODE_PRIME * result + (int) (temp ^ temp >>> NUMBER_BIT_LENGTH);
		} else if (value instanceof Long){
			Long temp = (Long) value;
			return Constants.HASH_CODE_PRIME * result + (int) (temp ^ temp >>> NUMBER_BIT_LENGTH);
		} else if (value instanceof Boolean){
			return Constants.HASH_CODE_PRIME * result + ((Boolean) value ? 0 : 1);
		} else if (value instanceof Float){
			return Constants.HASH_CODE_PRIME * result + Float.floatToIntBits((Float) value);
		} else if (value instanceof Number){
			return Constants.HASH_CODE_PRIME * result + ((Number) value).intValue();
		} else {
			return Constants.HASH_CODE_PRIME * result + (value == null ? 0 : value.hashCode());
		}
	}

	/**
	 * Test if the two objects are equals.
	 *
	 * @param bean The bean to test.
	 * @param other The other bean to test for equality with the first one.
	 * @param properties The properties to compare one by one. The properties n is compared to the property
	 * n + (properties.length / 2). This arry must be pair.
	 *
	 * @return A boolean indicating if the two objects are equals or not.
	 */
	public static boolean areEqualsDirect(Object bean, Object other, Object... properties){
		if (bean == other){
			return true;
		}

		if (EqualsUtils.areObjectIncompatible(bean, other)){
			return false;
		}

		for (int i = 0; i < properties.length / 2; i++){
			Object propertyBean = properties[i];
			Object propertyOther = properties[i + properties.length];

			if (propertyBean == null){
				if (propertyOther != null){
					return false;
				}
			} else if (!propertyBean.equals(propertyOther)){
				return false;
			}
		}

		return true;
	}
}