package org.jtheque.primary.od.impl.abstraction;

import org.jtheque.primary.od.able.Type;

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
 * A type of film. It seems the media on which the film is.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractType extends AbstractPrimaryData implements Type {
    private String name;

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final void setName(String nom) {
        name = nom;
    }
}