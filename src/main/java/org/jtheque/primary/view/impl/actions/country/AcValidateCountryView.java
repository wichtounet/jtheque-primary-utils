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
import org.jtheque.primary.controller.able.ICountryController;
import org.jtheque.primary.view.able.ICountryView;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

/**
 * An action to validate the country view.
 *
 * @author Baptiste Wicht
 */
public final class AcValidateCountryView extends JThequeAction {
    private static final long serialVersionUID = -6791055361978541369L;

    @Resource
    private ICountryController countryController;

    @Resource
    private ICountryView countryView;

    /**
     * Construct a new AcValidateCountryView.
     */
    public AcValidateCountryView() {
        super("country.actions.ok");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (countryView.validateContent()) {
            countryController.save(countryView.getFieldNom().getText());

            countryView.closeDown();
        }
    }
}
