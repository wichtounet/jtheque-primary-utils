package org.jtheque.primary.controller.impl;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.undo.IUndoRedoManager;
import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.primary.controller.able.ICountryController;
import org.jtheque.primary.controller.impl.undo.CreatedCountryEdit;
import org.jtheque.primary.od.able.Country;
import org.jtheque.primary.services.able.ICountriesService;
import org.jtheque.primary.view.able.ICountryView;
import org.jtheque.primary.view.able.ViewMode;

import javax.annotation.Resource;

/**
 * A Country Controller.
 *
 * @author Baptiste Wicht
 */
public final class CountryController extends AbstractController implements ICountryController {
    private ViewMode state = ViewMode.NEW;
    private Country currentCountry;

    @Resource
    private ICountriesService countriesService;

    @Resource
    private ICountryView countryView;

    @Override
    public void newCountry() {
        state = ViewMode.NEW;

        countryView.reload();
        currentCountry = countriesService.getEmptyCountry();
    }

    @Override
    public void editCountry(Country country) {
        state = ViewMode.EDIT;

        countryView.reload(country);
        currentCountry = country;

        displayView();
    }

    @Override
    public void save(String name) {
        currentCountry.setName(name);

        if (state == ViewMode.NEW) {
            countriesService.create(currentCountry);

            Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedCountryEdit(currentCountry));
        } else {
            countriesService.save(currentCountry);
        }
    }

    @Override
    public ICountryView getView() {
        return countryView;
    }
}