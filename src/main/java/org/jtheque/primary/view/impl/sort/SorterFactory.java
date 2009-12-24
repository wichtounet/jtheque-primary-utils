package org.jtheque.primary.view.impl.sort;

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

import java.util.ArrayList;
import java.util.Collection;

/**
 * A factory for Sorter objects.
 *
 * @author Baptiste Wicht
 */
public final class SorterFactory {
    private final Collection<Sorter> sorters;

    private static final SorterFactory INSTANCE = new SorterFactory();

    /**
     * Utility class, cannot be instanciated.
     */
    private SorterFactory() {
        super();

        sorters = new ArrayList<Sorter>(10);
    }

    /**
     * Return the unique instance of the class.
     *
     * @return The singleton of the class.
     */
    public static SorterFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Add a sorter to manage.
     *
     * @param sorter The sorter to add.
     */
    public void addSorter(Sorter sorter) {
        sorters.add(sorter);
    }

    /**
     * Remove a sorter to manage.
     *
     * @param sorter The sorter to remove.
     */
    public void removeSorter(Sorter sorter) {
        sorters.remove(sorter);
    }

    /**
     * Return the sorter for the specified content and sort types.
     *
     * @param content  The content type.
     * @param sortType The sort type.
     * @return The sorter of the specified or <code>null</code> if no sorter of this type exist.
     */
    public Sorter getSorter(String content, String sortType) {
        for (Sorter sorter : sorters) {
            if (sorter.canSort(content, sortType)) {
                return sorter;
            }
        }

        return null;
    }

    /**
     * Remove the specified sorters.
     *
     * @param sorters The sorters to remove.
     */
    public void removeSorters(Sorter[] sorters) {
        for (Sorter sorter : sorters) {
            removeSorter(sorter);
        }
    }
}
