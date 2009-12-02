package org.jtheque.primary.view.impl.components.panels;

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

import org.jdesktop.swingx.JXTitledPanel;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.language.Internationalizable;

/**
 * A JTheque Titled Panel.
 *
 * @author Baptiste Wicht
 */
public final class JThequeTitledPanel extends JXTitledPanel implements Internationalizable {
    private static final long serialVersionUID = -2284383158816107610L;

    private final String key;

    /**
     * Construct a new JThequeTitledPanel.
     *
     * @param key The internationalization key.
     */
    public JThequeTitledPanel(String key) {
        super();

        this.key = key;

        setTitle(Managers.getManager(ILanguageManager.class).getMessage(key));

        Managers.getManager(ILanguageManager.class).addInternationalizable(this);
    }

    @Override
    public void refreshText() {
        setTitle(Managers.getManager(ILanguageManager.class).getMessage(key));
    }
}