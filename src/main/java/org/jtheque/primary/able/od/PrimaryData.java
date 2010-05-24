package org.jtheque.primary.able.od;

/**
 * A primary data. It seems a data specific to a primary implementation.
 *
 * @author Baptiste Wicht
 */
public interface PrimaryData extends Data {
	/**
	 * Return the primary implementation of this data.
	 *
	 * @return The primary implementation of this data.
	 */
	String getPrimaryImpl();

	/**
	 * Set the primary implementation of the data.
	 *
	 * @param impl The primary impl of the data.
	 */
	void setPrimaryImpl(String impl);
}
