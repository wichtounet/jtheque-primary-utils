package org.jtheque.primary.od.impl.abstraction;

import org.jtheque.primary.od.able.PrimaryData;

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
 * An abstract primary data.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractPrimaryData extends AbstractData implements PrimaryData {
	private String primaryImpl;

	@Override
	public final String getPrimaryImpl(){
		return primaryImpl;
	}

	@Override
	public final void setPrimaryImpl(String primaryImpl){
		this.primaryImpl = primaryImpl;
	}
}
