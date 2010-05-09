package org.jtheque.primary;

import org.jtheque.features.Feature;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wichtounet
 * Date: May 6, 2010
 * Time: 5:52:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPrimaryUtils {
    /**
     * Preplug the elements of the utils.
     */
    void prePlug();

    /**
     * Plug the elements of the utils.
     */
    void plug();

    /**
     * Unplug the elements of the utils.
     */
    void unplug();

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
     * @param addFeatures The sub features of the add menu.
     * @param removeFeatures The sub features of the remove menu.
     * @param editFeatures The sub features of the edit menu.
     */
    void enableMenu(List<Feature> addFeatures, List<Feature> removeFeatures, List<Feature> editFeatures);
}
