package org.jtheque.primary.od.able;

/**
 * A language specification.
 *
 * @author Baptiste Wicht
 */
public interface Language extends Data {
    /**
     * Return the name of the language.
     *
     * @return The name of the language.
     */
    String getName();

    /**
     * Set the name of the language.
     *
     * @param name The name of the language.
     */
    void setName(String name);
}
