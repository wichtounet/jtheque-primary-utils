package org.jtheque.primary.dao.able;

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

import org.jtheque.core.managers.persistence.able.JThequeDao;
import org.jtheque.primary.od.able.SimpleData;

import java.util.Collection;

public interface IDaoSimpleDatas extends JThequeDao {
    Collection<SimpleData> getSimpleDatas();

    SimpleData getSimpleData(int id);

    SimpleData getSimpleData(String title);

    boolean exist(SimpleData data);

    boolean delete(SimpleData data);

    void create(SimpleData data);

    void save(SimpleData data);

    SimpleData createSimpleData();
}