package org.jtheque.primary.view.impl.models.able;

import org.jtheque.core.managers.view.able.components.IModel;
import org.jtheque.primary.od.able.Saga;

/**
 * @author Baptiste Wicht
 */
public interface ISagaModel extends IModel {
    /**
     * Set the current saga.
     *
     * @param saga The current saga.
     */
    void setSaga(Saga saga);

    /**
     * Return the current saga.
     *
     * @return The current saga.
     */
    Saga getSaga();
}
