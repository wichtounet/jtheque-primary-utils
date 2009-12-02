package org.jtheque.primary.od.able;

/**
 * A country specification.
 *
 * @author Baptiste Wicht
 */
public interface Country extends Data {
    /**
     * Return the name of the country.
     *
     * @return The name of the country.
     */
    String getName();

    /**
     * Set the name of the country.
     *
     * @param name The name of the country.
     */
    void setName(String name);
}
