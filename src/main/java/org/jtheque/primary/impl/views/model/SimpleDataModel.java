package org.jtheque.primary.impl.views.model;

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

import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.able.views.model.ISimpleDataModel;
import org.jtheque.primary.impl.controller.SimpleController;

/**
 * A simple data model implementation.
 *
 * @author Baptiste Wicht
 */
public final class SimpleDataModel implements ISimpleDataModel {
    private SimpleData data;
    private SimpleController id;

    @Override
    public SimpleData getSimpleData() {
        return data;
    }

    @Override
    public void setSimpleData(SimpleData data) {
        this.data = data;
    }

    @Override
    public void setCurrentController(SimpleController id) {
        this.id = id;
    }

    @Override
    public SimpleController getController() {
        return id;
    }
}