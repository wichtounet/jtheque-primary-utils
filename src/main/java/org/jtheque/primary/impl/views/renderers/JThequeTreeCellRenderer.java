package org.jtheque.primary.impl.views.renderers;

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

import org.jtheque.images.ImageService;
import org.jtheque.primary.utils.views.tree.TreeElement;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Component;

/**
 * A cell renderer to display an icon in the JTree.
 *
 * @author Baptiste Wicht
 */
public final class JThequeTreeCellRenderer extends DefaultTreeCellRenderer {
    private final ImageService imageService;

    public JThequeTreeCellRenderer(ImageService imageService) {
        super();

        this.imageService = imageService;
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        JLabel component = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded,
                leaf, row, hasFocus);

        TreeElement element = (TreeElement) value;

        component.setText(element.getElementName());

        if (element.isLeaf()) {
            component.setIcon(element.getIcon(imageService));
        }

        return component;
    }
}