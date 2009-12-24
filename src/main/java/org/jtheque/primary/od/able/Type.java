package org.jtheque.primary.od.able;

/**
 * A type specification.
 *
 * @author Baptiste Wicht
 */
public interface Type extends PrimaryData {
    /**
     * Return the name of the type.
     *
     * @return The name of the type.
     */
    String getName();

    /**
     * Set the name of the type.
     *
     * @param name The name of the type.
     */
    void setName(String name);
}
