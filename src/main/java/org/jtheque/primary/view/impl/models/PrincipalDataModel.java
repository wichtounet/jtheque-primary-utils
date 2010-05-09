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

import org.jtheque.core.utils.WeakEventListenerList;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.view.impl.listeners.CurrentObjectListener;
import org.jtheque.primary.view.impl.listeners.DisplayListListener;
import org.jtheque.primary.view.impl.listeners.ObjectChangedEvent;
import org.jtheque.primary.view.impl.models.able.IPrincipalDataModel;

import java.util.Collection;

/**
 * A model for the principal data.
 *
 * @author Baptiste Wicht
 */
public abstract class PrincipalDataModel<T extends Data> implements IPrincipalDataModel<T> {
	private final WeakEventListenerList listenerList;

	private Collection<T> displayList;

	/**
	 * Construct a new PrincipalDataModel.
	 */
	protected PrincipalDataModel(){
		super();

		listenerList = new WeakEventListenerList();
	}

	/**
	 * Return the datas managed by this model.
	 *
	 * @return The datas managed by this model.
	 */
	public abstract Collection<T> getDatas();

	/**
	 * Return the event listener list.
	 *
	 * @return The event listener list.
	 */
	protected final WeakEventListenerList getEventListenerList(){
		return listenerList;
	}

	@Override
	public final void addCurrentObjectListener(CurrentObjectListener listener){
		listenerList.add(CurrentObjectListener.class, listener);
	}

	@Override
	public final void addDisplayListListener(DisplayListListener listener){
		listenerList.add(DisplayListListener.class, listener);
	}

	/**
	 * Fire an event to say that the current object has changed.
	 *
	 * @param event The event to fire
	 */
	protected final void fireCurrentObjectChanged(ObjectChangedEvent event){
		CurrentObjectListener[] listeners = listenerList.getListeners(CurrentObjectListener.class);

		for (CurrentObjectListener listener : listeners){
			listener.objectChanged(event);
		}
	}

	/**
	 * Avert the listeners that the display list have changed.
	 */
	protected final void fireDisplayListChanged(){
		DisplayListListener[] listeners = listenerList.getListeners(DisplayListListener.class);

		for (DisplayListListener listener : listeners){
			listener.displayListChanged();
		}
	}

	@Override
	public final Collection<T> getDisplayList(){
		if (displayList == null){
			displayList = getDatas();
		}

		return displayList;
	}

	@Override
	public final void updateDisplayList(Collection<T> datas){
		getDisplayList().clear();

		if (datas != null){
			getDisplayList().addAll(datas);
		} else {
			getDisplayList().addAll(getDatas());
		}

		fireDisplayListChanged();
	}

	@Override
	public final void dataChanged(){
		updateDisplayList();
	}

	/**
	 * Update the display list.
	 */
	public final void updateDisplayList(){
		updateDisplayList(null);
	}
}