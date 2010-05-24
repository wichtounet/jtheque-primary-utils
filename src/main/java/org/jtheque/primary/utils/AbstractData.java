package org.jtheque.primary.utils;

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

import org.jtheque.persistence.utils.AbstractEntity;
import org.jtheque.primary.able.od.Data;
import org.jtheque.primary.utils.views.tree.TreeElement;
import org.jtheque.resources.able.IResourceService;

import javax.swing.Icon;

/**
 * Represents a persisted object of JTheque.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractData extends AbstractEntity implements Data {
	@Override
	public final String getElementName(){
		return getDisplayableText();
	}

	@Override
	public Icon getIcon(IResourceService resourceService){
		return null;
	}

	@Override
	public boolean isRoot(){
		return false;
	}

	@Override
	public boolean isCategory(){
		return false;
	}

	@Override
	public boolean isLeaf(){
		return true;
	}

	@Override
	public TreeElement getChild(int index){
		return null;
	}

	@Override
	public int getChildCount(){
		return 0;
	}

	@Override
	public int indexOf(TreeElement treeElement){
		return -1;
	}

	@Override
	public void add(TreeElement element){
		throw new UnsupportedOperationException("Add is not supported on leaf");
	}

	@Override
	public void addAll(Iterable<? extends TreeElement> elements){
		throw new UnsupportedOperationException("Add is not supported on leaf");
	}

	@Override
	public void clear(){
		throw new UnsupportedOperationException("Clear is not supported on leaf");
	}
}