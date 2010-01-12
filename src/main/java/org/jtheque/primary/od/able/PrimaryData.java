package org.jtheque.primary.od.able;

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
    
    void setPrimaryImpl(String impl);
}
