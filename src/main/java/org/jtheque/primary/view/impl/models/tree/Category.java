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
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a category of a tree model.
 *
 * @author Baptiste Wicht
 */
public final class Category implements TreeElement {
	private final List<TreeElement> elements;
	private final String name;

	/**
	 * Constructs a new category.
	 *
	 * @param name The name of element
	 */
	public Category(String name){
		super();

		this.name = name;
		elements = new ArrayList<TreeElement>(20);
	}

	@Override
	public String getElementName(){
		return name;
	}

	@Override
	public Icon getIcon(){
		return null;
	}

	@Override
	public boolean isRoot(){
		return false;
	}

	@Override
	public boolean isCategory(){
		return true;
	}

	@Override
	public boolean isLeaf(){
		return false;
	}

	@Override
	public TreeElement getChild(int index){
		return elements.get(index);
	}

	@Override
	public int getChildCount(){
		return elements.size();
	}

	@Override
	public int indexOf(TreeElement element){
		return elements.indexOf(element);
	}

	@Override
	public void add(TreeElement element){
		elements.add(element);
	}

	@Override
	public void addAll(Iterable<? extends TreeElement> elements){
		for (TreeElement element : elements){
			add(element);
		}
	}

	@Override
	public void clear(){
		elements.clear();
	}
}