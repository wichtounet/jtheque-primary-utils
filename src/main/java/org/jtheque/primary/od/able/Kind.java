package org.jtheque.primary.od.able;

/**
 * A kind specification.
 *
 * @author Baptiste Wicht
 */
public interface Kind extends PrimaryData {
    /**
     * Return the name of the kind.
     *
     * @return The name
     */
    String getName();

    /**
     * Set the name of the kind.
     *
     * @param name The new name of the kind
     */
    void setName(String name);
}
