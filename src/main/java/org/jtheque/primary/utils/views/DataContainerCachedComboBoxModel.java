package org.jtheque.primary.utils.views;

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

import org.jtheque.persistence.able.DataContainer;
import org.jtheque.persistence.able.DataListener;
import org.jtheque.persistence.able.Entity;
import org.jtheque.primary.able.od.Data;

import javax.swing.DefaultComboBoxModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A combo box model with the data in a cache.
 *
 * @author Baptiste Wicht
 * @param <T> The entity class on the model.
 */
public final class DataContainerCachedComboBoxModel<T extends Data> extends DefaultComboBoxModel implements DataListener {
    private static final long serialVersionUID = 141208715181504849L;

    private final DataContainer<T> container;

    private List<T> datas;

    /**
     * Construct a new DataContainerCachedComboBoxModel with a specific container.
     *
     * @param container The data container.
     */
    public DataContainerCachedComboBoxModel(DataContainer<T> container) {
        super();

        this.container = container;
        datas = new ArrayList<T>(container.getDatas());

        container.addDataListener(this);
    }

    @Override
    public Object getElementAt(int index) {
        return datas.get(index);
    }

    @Override
    public int getSize() {
        return getDatas().size();
    }

    /**
     * Return the selected data in the model.
     *
     * @return The data who's selected.
     */
    @SuppressWarnings("unchecked") //Safe because we guarantee to have only T elements added into the model
    public T getSelectedData() {
        return (T) getSelectedItem();
    }

    @Override
    public void dataChanged() {
        datas = new ArrayList<T>(container.getDatas());

        fireContentsChanged(this, 0, getSize());
    }

    /**
     * Return the datas.
     *
     * @return The datas.
     */
    private Collection<? extends Entity> getDatas() {
        return datas;
    }

    /**
     * Select the first element. If there is no element, nothing will be done.
     */
    public void selectFirst() {
        if (!datas.isEmpty()) {
            setSelectedItem(datas.get(0));
        }
    }
}