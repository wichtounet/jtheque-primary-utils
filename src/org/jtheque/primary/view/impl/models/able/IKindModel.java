package org.jtheque.primary.view.impl.models.able;

import org.jtheque.core.managers.view.able.components.IModel;
import org.jtheque.primary.od.able.Kind;

/**
 * @author Baptiste Wicht
 */
public interface IKindModel extends IModel {

    /**
     * Return the current kind.
     *
     * @return The current kind.
     */
    Kind getKind();

    /**
     * Set the current kind.
     *
     * @param kind The current kind.
     */
    void setKind(Kind kind);
}
