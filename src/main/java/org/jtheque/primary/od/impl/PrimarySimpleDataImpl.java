package org.jtheque.primary.od.impl;

import org.jtheque.primary.od.able.PrimarySimpleData;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.utils.TempUtils;

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
 * A primary simple data implementation.
 *
 * @author Baptiste Wicht
 */
public final class PrimarySimpleDataImpl extends SimpleDataImpl implements PrimarySimpleData {
	private String primaryImpl;

	/**
	 * Construct a new PrimarySimpleDataImpl.
	 *
	 * @param type The simple data type.
	 * @param primaryImpl The primary implementation.
	 */
	public PrimarySimpleDataImpl(SimpleData.DataType type, String primaryImpl){
		super(type);

		this.primaryImpl = primaryImpl;
	}

	@Override
	public String getPrimaryImpl(){
		return primaryImpl;
	}

	@Override
	public void setPrimaryImpl(String impl){
		primaryImpl = impl;
	}

	@Override
	public int hashCode(){
		return TempUtils.hashCodeDirect(this, getName(), primaryImpl);
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}

		PrimarySimpleData other = (PrimarySimpleData) obj;

		return TempUtils.areEqualsDirect(this, obj,
				getName(), primaryImpl,
				other.getName(), other.getPrimaryImpl());
	}
}
