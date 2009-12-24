package org.jtheque.primary.view.impl.models.able;

import org.jtheque.core.managers.view.able.components.IModel;
import org.jtheque.primary.od.able.Language;

/**
 * @author Baptiste Wicht
 */
public interface ILanguageModel extends IModel {
    /**
     * Set the current language.
     *
     * @param language The language.
     */
    void setLanguage(Language language);

    /**
     * Return the current language.
     *
     * @return The current language.
     */
    Language getLanguage();
}
