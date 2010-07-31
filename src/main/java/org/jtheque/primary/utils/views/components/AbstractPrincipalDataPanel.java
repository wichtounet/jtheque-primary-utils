package org.jtheque.primary.utils.views.components;

import org.jtheque.images.able.ImageService;
import org.jtheque.primary.able.controller.FormBean;
import org.jtheque.primary.able.views.ToolbarView;
import org.jtheque.primary.impl.views.renderers.JThequeTreeCellRenderer;
import org.jtheque.primary.utils.sort.SortManager;
import org.jtheque.ui.able.IModel;

import org.jdesktop.swingx.JXTree;

import javax.annotation.Resource;

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
    private static final SortManager SORTER = new SortManager();

    private final String dataType;

    private ToolbarView toolBar;
    private JXTree tree;

    @Resource
    private ImageService imageService;

    /**
     * Construct a new AbstractPrincipalDataPanel for a specific data type.
     *
     * @param dataType The data type.
     */
    public AbstractPrincipalDataPanel(String dataType) {
        super();

        this.dataType = dataType;
    }

    @Override
    public final ToolbarView getToolbarView() {
        return toolBar;
    }

    @Override
    protected final JXTree getTree() {
        return tree;
    }

    @Override
    public final String getDataType() {
        return dataType;
    }

    /**
     * Set the tool bar of the view.
     *
     * @param toolBar The tool bar of the view.
     */
    public final void setToolBar(ToolbarView toolBar) {
        this.toolBar = toolBar;
    }

    /**
     * Init the tree.
     */
    public final void initTree() {
        tree = new JXTree(getTreeModel());
        tree.setCellRenderer(new JThequeTreeCellRenderer(imageService));
        tree.putClientProperty("JTree.lineStyle", "None");
    }

    /**
     * Return the sorter used to sort the datas of the view.
     *
     * @return A SortManager instance to sort the datas.
     */
    public static SortManager getSorter() {
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
     * @param object The object to set as the current object.
     */
    public abstract void setCurrent(Object object);
}