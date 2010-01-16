package org.jtheque.primary.view.impl.choice;

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
 * A deleter. It's an object who can delete a certain type of object.
 *
 * @author Baptiste Wicht
 */
public abstract class Deleter {
	private final String content;

	/**
	 * Construct a new <code>Deleter</code>
	 *
	 * @param content The content to delete.
	 */
	public Deleter(String content){
		super();

		this.content = content;
	}

	/**
	 * Indicate if this deleter can delete the specified content or not.
	 *
	 * @param content The content to delete.
	 *
	 * @return <code>true</code> if the deleter can delete this content else <code>false</code>.
	 */
	public final boolean canDelete(String content){
		return this.content.equals(content);
	}

	/**
	 * Delete the object.
	 *
	 * @param item The object to delete.
	 */
	public abstract void delete(Object item);
}
