package org.jtheque.primary.able.views.model;

import org.jtheque.primary.able.od.Person;
import org.jtheque.ui.able.Model;

/**
 * @author Baptiste Wicht
 */
public interface IBorrowerModel extends Model {
    /**
     * Set the borrower.
     *
     * @param borrower The borrower to set.
     */
    void setBorrower(Person borrower);

    /**
     * Return the current borrower.
     *
     * @return The current borrower.
     */
    Person getBorrower();
}
