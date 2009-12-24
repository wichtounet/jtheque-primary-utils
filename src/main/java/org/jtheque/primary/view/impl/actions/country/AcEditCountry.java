package org.jtheque.primary.view.impl.actions.country;

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

import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.primary.controller.able.IChoiceController;
import org.jtheque.primary.services.able.ICountriesService;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to edit a country.
 *
 * @author Baptiste Wicht
 */
public final class AcEditCountry extends JThequeAction {
    private static final long serialVersionUID = -8314991315232584987L;

    @Resource
    private IChoiceController choiceController;

    /**
     * Construct a new AcEditCountry.
     *
     * @param key The internationalization key.
     */
    public AcEditCountry(String key) {
        super(key);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        choiceController.setAction("edit");
        choiceController.setContent(ICountriesService.DATA_TYPE);
        choiceController.displayView();
    }
}