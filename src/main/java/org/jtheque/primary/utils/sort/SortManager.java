package org.jtheque.primary.utils.sort;

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

import org.jtheque.primary.utils.DataTypeManager;
import org.jtheque.primary.utils.views.tree.JThequeTreeModel;
import org.jtheque.primary.utils.views.tree.RootElement;
import org.jtheque.primary.utils.views.tree.TreeElement;

/**
 * Manage the sort of the tree model.
 *
 * @author Baptiste Wicht
 */
public final class SortManager {
    private final SorterFactory factory;

    /**
     * Construct a new <code>SortManager</code>.
     */
    public SortManager() {
        super();

        factory = SorterFactory.getInstance();
    }

    /**
     * Sort the model with specific content and type of sort.
     *
     * @param model    The model of the JTree
     * @param content  The content of the model
     * @param sortType The type of sort
     */
    public void sort(JThequeTreeModel model, String content, String sortType) {
        TreeElement root = model.getRoot();

        root.clear();

        Sorter sorter = factory.getSorter(content, sortType);

        if (sorter != null) {
            sorter.sort(model);
        }
    }

    /**
     * Create a model with initial content.
     *
     * @param type The type of content
     *
     * @return The model initiated
     */
    public JThequeTreeModel createInitialModel(String type) {
        TreeElement root = new RootElement(DataTypeManager.getKeyForDataType(type));

        JThequeTreeModel model = new JThequeTreeModel(root);

        sort(model, type, "None");

        return model;
    }
}