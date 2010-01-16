package org.jtheque.primary.view.impl.models.tree;

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

import javax.swing.Icon;

/**
 * Represents an element of a tree model.
 *
 * @author Baptiste Wicht
 */
public interface TreeElement {
	/**
	 * Return the name of the element.
	 *
	 * @return The name
	 */
	String getElementName();

	/**
	 * Return the icon of the element.
	 *
	 * @return The icon
	 */
	Icon getIcon();

	/**
	 * Indicate if the element is root or not.
	 *
	 * @return <code>true</code> if the element is the root else false.
	 */
	boolean isRoot();

	/**
	 * Indicate if the element is a category or not.
	 *
	 * @return <code>true</code> if the element is a category else <code>false</code>.
	 */
	boolean isCategory();

	/**
	 * Indicate if the element is a leaf or not.
	 *
	 * @return <code>true</code> if the element is a leaf else <code>false</code>.
	 */
	boolean isLeaf();

	/**
	 * Return the child a the specified index.
	 *
	 * @param index The index.
	 *
	 * @return The element at the index else <code>null</code>.
	 */
	TreeElement getChild(int index);

	/**
	 * Return the number of childs.
	 *
	 * @return The number of childs.
	 */
	int getChildCount();

	/**
	 * Return the index of the specified element.
	 *
	 * @param treeElement The element to search.
	 *
	 * @return the index of the element if found else <code>-1</code>.
	 */
	int indexOf(TreeElement treeElement);

	/**
	 * Add a tree element to the element.
	 *
	 * @param element The element to add.
	 */
	void add(TreeElement element);

	/**
	 * Add all the elements to the element.
	 *
	 * @param elements The elements to add.
	 */
	void addAll(Iterable<? extends TreeElement> elements);

	/**
	 * Clear the element.
	 */
	void clear();
}