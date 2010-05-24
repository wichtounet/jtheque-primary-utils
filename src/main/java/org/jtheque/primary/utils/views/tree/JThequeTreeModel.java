package org.jtheque.primary.utils.views.tree;

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

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * A JTheque Tree Model.
 *
 * @author Baptiste Wicht
 */
public final class JThequeTreeModel implements TreeModel {
	private TreeElement root;
	private final WeakEventListenerList listeners;

	/**
	 * Construct a new JTheque Tree Model with a root element.
	 *
	 * @param root The root element.
	 */
	public JThequeTreeModel(TreeElement root){
		super();

		this.root = root;
		listeners = new WeakEventListenerList();
	}

	/**
	 * Set the root element of the model.
	 *
	 * @param root The root element of the model.
	 */
	public void setRootElement(TreeElement root){
		this.root = root;

		fireTreeStructureChanged(root);
	}

	@Override
	public Object getChild(Object parent, int index){
		TreeElement element = (TreeElement) parent;

		if (element.isRoot() || element.isCategory()){
			return element.getChild(index);
		} else {
			assert element.isLeaf() : "Element must be one of (root, category, tree";

			return null;
		}
	}

	@Override
	public int getChildCount(Object parent){
		TreeElement element = (TreeElement) parent;

		return element.getChildCount();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child){
		TreeElement element = (TreeElement) parent;

		return element.indexOf((TreeElement) child);
	}

	@Override
	public TreeElement getRoot(){
		return root;
	}

	@Override
	public boolean isLeaf(Object node){
		TreeElement element = (TreeElement) node;

		return element.isLeaf();
	}

	/**
	 * Fire a event to say that the tree structure has changed.
	 *
	 * @param root The root element.
	 */
	void fireTreeStructureChanged(TreeElement root){
		TreeModelEvent event = new TreeModelEvent(this, new TreeElement[]{root});

		for (TreeModelListener tml : listeners.getListeners(TreeModelListener.class)){
			tml.treeStructureChanged(event);
		}
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener){
		listeners.add(TreeModelListener.class, listener);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener){
		listeners.remove(TreeModelListener.class, listener);
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1){
		//Nothing to be done
	}
}