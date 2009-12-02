package org.jtheque.primary.view.impl.models.able;

import org.jtheque.core.managers.view.able.components.IModel;
import org.jtheque.primary.od.able.Country;

/**
 * @author Baptiste Wicht
 */
public interface ICountryModel extends IModel {
    /**
     * Return the current country.
     *
     * @return The current country.
     */
    Country getCountry();

    /**
     * Set the current country.
     *
     * @param country The current country.
     */
    void setCountry(Country country);
}
