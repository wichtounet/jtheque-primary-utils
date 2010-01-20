package org.jtheque.primary.view.impl.models;

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

import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.view.impl.models.able.ISimpleDataModel;

/**
 * A simple data model implementation.
 *
 * @author Baptiste Wicht
 */
public final class SimpleDataModel implements ISimpleDataModel {
	private SimpleData data;
    private String id;

	@Override
	public SimpleData getSimpleData(){
		return data;
	}

	@Override
	public void setSimpleData(SimpleData data){
		this.data = data;
	}

    @Override
    public void setCurrentController(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}