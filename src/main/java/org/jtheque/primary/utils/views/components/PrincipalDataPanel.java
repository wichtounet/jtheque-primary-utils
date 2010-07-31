package org.jtheque.primary.utils.views.components;

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

import org.jtheque.errors.able.IError;
import org.jtheque.i18n.able.LanguageService;
import org.jtheque.primary.able.od.Data;
import org.jtheque.primary.able.views.PrincipalDataView;
import org.jtheque.primary.able.views.ToolbarView;
import org.jtheque.primary.utils.sort.SortManager;
import org.jtheque.primary.utils.views.listeners.DisplayListListener;
import org.jtheque.primary.utils.views.tree.JThequeTreeModel;
import org.jtheque.ui.able.IModel;
import org.jtheque.utils.ui.SwingUtils;
import org.jtheque.views.able.IViews;

import org.jdesktop.swingx.JXTree;

import javax.annotation.Resource;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A principal data panel.
 *
 * @author Baptiste Wicht
 */
public abstract class PrincipalDataPanel<M extends IModel> extends JPanel implements PrincipalDataView, DisplayListListener {
    private static final SortManager SORTER = new SortManager();

    private JThequeTreeModel treeModel;
    private String sortMode = "None";
    private M model;

    @Resource
    private IViews views;

    @Resource
    private LanguageService languageService;

    /**
     * Return the data tree.
     *
     * @return The data tree.
     */
    protected abstract JXTree getTree();

    /**
     * Return the data type of the panel.
     *
     * @return the data type of the panel.
     */
    protected abstract String getDataType();

    /**
     * Return the tree model of the panel.
     *
     * @return The <code>JThequeTreeModel</code> associated to the tree.
     */
    public final JThequeTreeModel getTreeModel() {
        return treeModel;
    }

    /**
     * Set the tree model of the panel.
     *
     * @param model the new tree model of the panel.
     */
    public final void setTreeModel(JThequeTreeModel model) {
        treeModel = model;
    }

    @Override
    public final void displayListChanged() {
        SORTER.sort(treeModel, getDataType(), sortMode);
    }

    @Override
    public final M getModel() {
        return model;
    }

    /**
     * Set the model of the panel.
     *
     * @param model The model.
     */
    public final void setModel(M model) {
        this.model = model;
    }

    @Override
    public final void resort() {
        sort(sortMode);
    }

    @Override
    public final void select(Data data) {
        int index = 1;

        Object root = treeModel.getRoot();

        for (int i = 0; i < treeModel.getChildCount(root); i++) {
            for (int j = 0; j < treeModel.getChildCount(treeModel.getChild(root, i)); j++) {
                Object element = treeModel.getChild(treeModel.getChild(root, i), j);

                if (data.equals(element)) {
                    getTree().setSelectionRow(index + j);
                    return;
                }
            }

            index += treeModel.getChildCount(treeModel.getChild(root, i));
        }
    }

    @Override
    public void selectFirst() {
        if (sortMode == null || "None".equalsIgnoreCase(sortMode)) {
            getTree().setSelectionRow(1);
        } else {
            getTree().setSelectionRow(2);
        }
    }

    @Override
    public final void sort(String sort) {
        sortMode = sort;
        SORTER.sort(treeModel, getDataType(), sort);
    }

    @Override
    public final void display() {
        views.setSelectedMainComponent(this);
    }

    @Override
    public final void refresh() {
        SwingUtils.refresh(this);
    }

    @Override
    public final void toFirstPlan() {
        //Nothing to be done
    }

    @Override
    public final void closeDown() {
        //Nothing to be done
    }

    @Override
    public final void sendMessage(String message, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return the message for the internationalization key.
     *
     * @param key The internationalization key.
     *
     * @return The message.
     */
    protected String getMessage(String key) {
        return languageService.getMessage(key);
    }

    @Override
    public final boolean validateContent() {
        Collection<IError> errors = new ArrayList<IError>(6);

        validate(errors);

        return errors.isEmpty();
    }

    @Override
    public ToolbarView getToolbarView() {
        return null;  //Default implementation
    }

    @Override
    public void clear() {
        //Nothing by default
    }

    /**
     * Validate the view and save all the validation's errors in the list.
     *
     * @param errors The error's list.
     */
    protected abstract void validate(Collection<IError> errors);
}