package org.jtheque.primary.od.able;

/**
 * @author Baptiste Wicht
 */
public interface Saga extends PrimaryData {
    /**
     * Return the name of the saga.
     *
     * @return The name of the saga.
     */
    String getName();

    /**
     * Set the name of the saga.
     *
     * @param name The name of the saga.
     */
    void setName(String name);
}
