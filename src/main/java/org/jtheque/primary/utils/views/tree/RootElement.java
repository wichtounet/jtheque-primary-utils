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

import org.jtheque.images.able.IImageService;

import javax.swing.Icon;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the root element of a tree model.
 *
 * @author Baptiste Wicht
 */
public final class RootElement implements TreeElement {
    private final List<TreeElement> categories = new ArrayList<TreeElement>(10);
    private final List<TreeElement> elements = new ArrayList<TreeElement>(25);
    private final String name;

    /**
     * Constructs a new <code>RootElement</code>.
     *
     * @param name The name of element
     */
    public RootElement(String name) {
        super();

        this.name = name;
    }

    @Override
    public void add(TreeElement element) {
        if (element.isLeaf()) {
            elements.add(element);
        } else if (element.isCategory()) {
            categories.add(element);
        }
    }

    @Override
    public void addAll(Iterable<? extends TreeElement> elements) {
        for (TreeElement element : elements) {
            add(element);
        }
    }

    @Override
    public String getElementName() {
        return name;
    }

    @Override
    public Icon getIcon(IImageService imageService) {
        return null;
    }

    @Override
    public boolean isRoot() {
        return true;
    }

    @Override
    public boolean isCategory() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public TreeElement getChild(int index) {
        if (hasCategories()) {
            return categories.get(index);
        } else if (hasElements()) {
            return elements.get(index);
        }

        return null;
    }

    /**
     * Test if there is elements.
     *
     * @return <code>true</code> if there is elements else <code>false</code>.
     */
    private boolean hasElements() {
        return !elements.isEmpty();
    }

    /**
     * Test if there is categories.
     *
     * @return <code>true</code> if there is categories else <code>false</code>.
     */
    private boolean hasCategories() {
        return !categories.isEmpty();
    }

    @Override
    public int getChildCount() {
        if (hasCategories()) {
            return categories.size();
        } else if (hasElements()) {
            return elements.size();
        }

        return 0;
    }

    @Override
    public int indexOf(TreeElement element) {
        if (categories.contains(element)) {
            return categories.indexOf(element);
        } else if (hasElements()) {
            return elements.indexOf(element);
        }

        return -1;
    }

    @Override
    public void clear() {
        elements.clear();
        categories.clear();
    }
}