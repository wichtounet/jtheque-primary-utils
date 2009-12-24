package org.jtheque.primary.services.impl;

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

import org.jtheque.core.managers.persistence.able.DataListener;
import org.jtheque.primary.dao.able.IDaoCountries;
import org.jtheque.primary.od.able.Country;
import org.jtheque.primary.services.able.ICountriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * A countries service implementation.
 *
 * @author Baptiste Wicht
 */
@Service
public final class CountriesService implements ICountriesService {
    private Country defaultCountry;

    @Resource
    private IDaoCountries daoCountries;

    @Override
    public Country getDefaultCountry() {
        if (defaultCountry == null) {
            defaultCountry = daoCountries.getCountry("USA");

            if (defaultCountry == null) {
                createDefaultCountry();
            }
        }

        return defaultCountry;
    }

    /**
     * Create the default country.
     */
    @Transactional
    private void createDefaultCountry() {
        defaultCountry = daoCountries.createCountry();
        defaultCountry.setName("USA");
        daoCountries.create(defaultCountry);
    }

    @Override
    @Transactional
    public void save(Country country) {
        daoCountries.save(country);
    }

    @Override
    @Transactional
    public void create(Country country) {
        daoCountries.create(country);
    }

    @Override
    @Transactional
    public boolean delete(Country country) {
        return daoCountries.delete(country);
    }

    @Override
    public Collection<Country> getCountries() {
        return daoCountries.getCountries();
    }

    @Override
    public Country getCountry(String name) {
        return daoCountries.getCountry(name);
    }

    @Override
    @Transactional
    public void createAll(Iterable<Country> countries) {
        for (Country country : countries) {
            daoCountries.create(country);
        }
    }

    @Override
    public boolean exist(Country country) {
        return daoCountries.exist(country);
    }

    @Override
    public Country getEmptyCountry() {
        return daoCountries.createCountry();
    }

    @Override
    public boolean exist(String name) {
        return getCountry(name) != null;
    }

    @Override
    public Collection<Country> getDatas() {
        return getCountries();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoCountries.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoCountries.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}