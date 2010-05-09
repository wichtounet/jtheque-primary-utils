package org.jtheque.primary.view.impl.models.able;

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

import org.jtheque.persistence.able.DataListener;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.view.impl.listeners.CurrentObjectListener;
import org.jtheque.primary.view.impl.listeners.DisplayListListener;
import org.jtheque.ui.able.IModel;

import java.util.Collection;

/**
 * A model to store principal datas.
 *
 * @author Baptiste Wicht
 */
public interface IPrincipalDataModel<T extends Data> extends IModel, DataListener {
	/**
	 * Add a current object listener.
	 *
	 * @param listener The listener to add.
	 */
	void addCurrentObjectListener(CurrentObjectListener listener);

	/**
	 * Add a display list listener.
	 *
	 * @param listener The listener.
	 */
	void addDisplayListListener(DisplayListListener listener);

	/**
	 * Update the display list and replace his datas with a new list.
	 *
	 * @param datas The new display list.
	 */
	void updateDisplayList(Collection<T> datas);

	/**
	 * Return the display list.
	 *
	 * @return The display list
	 */
	Collection<T> getDisplayList();
}
