package org.jtheque.primary.view.impl.models;

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

import org.jtheque.primary.od.able.Language;
import org.jtheque.primary.view.impl.models.able.ILanguageModel;


/**
 * A model for the language view.
 *
 * @author Baptiste Wicht
 */
public final class LanguageModel implements ILanguageModel {
    private Language language;

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public Language getLanguage() {
        return language;
    }
}