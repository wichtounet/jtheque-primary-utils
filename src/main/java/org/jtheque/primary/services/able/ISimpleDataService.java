package org.jtheque.primary.services.able;

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.primary.od.able.SimpleData;

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
 * A countries service specification.
 *
 * @author Baptiste Wicht
 */
public interface ISimpleDataService extends DataContainer<SimpleData>, DataService<SimpleData> {
    String DATA_TYPE = "SimpleDatas";

    SimpleData getSimpleData(String name);

    void createAll(Iterable<SimpleData> datas);

    boolean exist(SimpleData country);

    SimpleData getEmptySimpleData();

    SimpleData getDefaultSimpleData();
    
    boolean exist(String name);

    boolean hasNoDatas();
}