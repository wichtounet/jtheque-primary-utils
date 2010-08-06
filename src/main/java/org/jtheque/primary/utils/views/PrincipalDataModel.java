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

import org.jtheque.primary.able.od.Data;
import org.jtheque.primary.able.views.model.IPrincipalDataModel;
import org.jtheque.primary.utils.views.listeners.CurrentObjectListener;
import org.jtheque.primary.utils.views.listeners.DisplayListListener;
import org.jtheque.primary.utils.views.listeners.ObjectChangedEvent;
import org.jtheque.utils.collections.WeakEventListenerList;

import java.util.Collection;

/**
 * A model for the principal data.
 *
 * @author Baptiste Wicht
 */
public abstract class PrincipalDataModel<T extends Data> implements IPrincipalDataModel<T> {
    private final WeakEventListenerList<CurrentObjectListener> currentObjectListeners = WeakEventListenerList.create();
    private final WeakEventListenerList<DisplayListListener> displayListListeners = WeakEventListenerList.create();

    private Collection<T> displayList;

    /**
     * Construct a new PrincipalDataModel.
     */
    protected PrincipalDataModel() {
        super();
    }

    /**
     * Return the datas managed by this model.
     *
     * @return The datas managed by this model.
     */
    public abstract Collection<T> getDatas();

    @Override
    public final void addCurrentObjectListener(CurrentObjectListener listener) {
        currentObjectListeners.add(listener);
    }

    @Override
    public final void addDisplayListListener(DisplayListListener listener) {
        displayListListeners.add(listener);
    }

    /**
     * Fire an event to say that the current object has changed.
     *
     * @param event The event to fire
     */
    protected final void fireCurrentObjectChanged(ObjectChangedEvent event) {
        for (CurrentObjectListener listener : currentObjectListeners) {
            listener.objectChanged(event);
        }
    }

    /**
     * Avert the listeners that the display list have changed.
     */
    protected final void fireDisplayListChanged() {
        for (DisplayListListener listener : displayListListeners) {
            listener.displayListChanged();
        }
    }

    @Override
    public final Collection<T> getDisplayList() {
        if (displayList == null) {
            displayList = getDatas();
        }

        return displayList;
    }

    @Override
    public final void updateDisplayList(Collection<T> datas) {
        getDisplayList().clear();

        if (datas != null) {
            getDisplayList().addAll(datas);
        } else {
            getDisplayList().addAll(getDatas());
        }

        fireDisplayListChanged();
    }

    @Override
    public final void dataChanged() {
        updateDisplayList();
    }

    /**
     * Update the display list.
     */
    public final void updateDisplayList() {
        updateDisplayList(null);
    }
}