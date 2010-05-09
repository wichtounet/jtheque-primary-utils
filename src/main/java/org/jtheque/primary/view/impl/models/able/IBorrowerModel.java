package org.jtheque.primary.view.impl.models.able;

import org.jtheque.primary.od.able.Person;
import org.jtheque.ui.able.IModel;

/**
 * @author Baptiste Wicht
 */
public interface IBorrowerModel extends IModel {
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
