package org.jtheque.primary.able;

import org.jtheque.features.Feature;

import java.util.List;

public interface IPrimaryUtils {
    /**
     * Return the current primary implementation.
     *
     * @return The current primary implementation.
     */
    String getPrimaryImpl();

    /**
     * Set the current primary implementation.
     *
     * @param primaryImpl The current primary implementation.
     */
    void setPrimaryImpl(String primaryImpl);

    /**
     * Enable the menu of the primary utils module.
     *
     * @param addFeatures    The sub features of the add menu.
     * @param removeFeatures The sub features of the remove menu.
     * @param editFeatures   The sub features of the edit menu.
     */
    void enableMenu(List<Feature> addFeatures, List<Feature> removeFeatures, List<Feature> editFeatures);
}
