package org.jtheque.primary.view.impl.models.able;

import org.jtheque.core.managers.view.able.components.IModel;
import org.jtheque.primary.od.able.Type;

/**
 * @author Baptiste Wicht
 */
public interface ITypeModel extends IModel {
    /**
     * Set the current type.
     *
     * @param type The current type.
     */
    void setType(Type type);

    /**
     * Return the current type.
     *
     * @return The current type.
     */
    Type getType();
}
