package org.jtheque.primary.view.impl.components.panels;

import org.jdesktop.swingx.JXTree;
import org.jtheque.primary.controller.able.FormBean;
import org.jtheque.primary.view.able.ToolbarView;
import org.jtheque.primary.view.impl.renderers.JThequeTreeCellRenderer;
import org.jtheque.primary.view.impl.sort.SortManager;
import org.jtheque.ui.able.IModel;

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
 * An abstract principal data panel.
 *
 * @author Baptiste Wicht
 * @param <M> The type of model.
 */
public abstract class AbstractPrincipalDataPanel<M extends IModel> extends PrincipalDataPanel<M> {
	private ToolbarView toolBar;
	private JXTree tree;

	private final String dataType;

	private static final SortManager SORTER = new SortManager();

	/**
	 * Construct a new AbstractPrincipalDataPanel for a specific data type.
	 *
	 * @param dataType The data type.
	 */
	public AbstractPrincipalDataPanel(String dataType){
		super();

		this.dataType = dataType;
	}

	@Override
	public final Object getImpl(){
		return this;
	}

	@Override
	public final ToolbarView getToolbarView(){
		return toolBar;
	}

	@Override
	protected final JXTree getTree(){
		return tree;
	}

	@Override
	public final String getDataType(){
		return dataType;
	}

	/**
	 * Set the tool bar of the view.
	 *
	 * @param toolBar The tool bar of the view.
	 */
	public final void setToolBar(ToolbarView toolBar){
		this.toolBar = toolBar;
	}

	/**
	 * Init the tree.
	 */
	public final void initTree(){
		tree = new JXTree(getTreeModel());
		tree.setCellRenderer(new JThequeTreeCellRenderer());
		tree.putClientProperty("JTree.lineStyle", "None");
	}

	/**
	 * Return the sorter used to sort the datas of the view.
	 *
	 * @return A SortManager instance to sort the datas.
	 */
	public static SortManager getSorter(){
		return SORTER;
	}

	/**
	 * Fill the form bean with the informations of the view.
	 *
	 * @param fb The form bean to fill.
	 */
	public abstract void fillFormBean(FormBean fb);

	/**
	 * Set the current object.
	 *
	 * @param object The objec to set as the current object.
	 */
	public abstract void setCurrent(Object object);
}