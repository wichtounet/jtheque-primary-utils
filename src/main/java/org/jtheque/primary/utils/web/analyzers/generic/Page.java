package org.jtheque.primary.utils.web.analyzers.generic;

import org.jtheque.primary.utils.web.analyzers.generic.transform.Transformer;

import java.util.ArrayList;
import java.util.Collection;

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
 * A Page for a generic web getter.
 *
 * @author Baptiste Wicht
 */
public final class Page {
    private String url;
    private Collection<Transformer> transformers;

    /**
     * Return the URL of the page.
     *
     * @return The page's url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL of the page.
     *
     * @param url The URL of the page.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Return all the transformers of the page's url.
     *
     * @return The transformers of the page url.
     */
    public Collection<Transformer> getTransformers() {
        return transformers;
    }

    /**
     * Set the transformers of the page url.
     *
     * @param transformers The transformers.
     */
    public void setTransformers(Collection<Transformer> transformers) {
        this.transformers = new ArrayList<Transformer>(transformers);
    }
}