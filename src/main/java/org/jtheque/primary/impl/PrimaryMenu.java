package org.jtheque.primary.impl;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jtheque.features.able.IFeature;
import org.jtheque.ui.able.IController;
import org.jtheque.utils.collections.CollectionUtils;
import org.jtheque.views.utils.AbstractMenu;

import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.List;

/**
 * The menu of the primary utils module.
 *
 * @author Baptiste Wicht
 */
final class PrimaryMenu extends AbstractMenu {
    private final Collection<IFeature> addFeatures;
    private final Collection<IFeature> removeFeatures;
    private final Collection<IFeature> editFeatures;

    private final ApplicationContext applicationContext;

    /**
     * Construct a new PrimaryMenu.
     *
     * @param addFeatures        The sub features of the add menu.
     * @param removeFeatures     The sub features of the remove menu.
     * @param editFeatures       The sub features of the edit menu.
     * @param applicationContext The application context.
     */
    PrimaryMenu(Collection<IFeature> addFeatures, Collection<IFeature> removeFeatures, Collection<IFeature> editFeatures,
                ApplicationContext applicationContext) {
        super();

        this.applicationContext = applicationContext;

        this.addFeatures = CollectionUtils.copyOf(addFeatures);
        this.removeFeatures = CollectionUtils.copyOf(removeFeatures);
        this.editFeatures = CollectionUtils.copyOf(editFeatures);
    }

    @Override
    protected List<IFeature> getMenuMainFeatures() {
        return features(
                createMainFeature(500, "menu.others",
                        createNewFeature(),
                        createDeleteFeature(),
                        createEditFeature())
        );
    }

    /**
     * Create the new menu.
     *
     * @return The Feature for the new menu.
     */
    private IFeature createNewFeature() {
        IController simpleController = applicationContext.getBean("simpleController", IController.class);
        IController borrowerController = applicationContext.getBean("borrowerController", IController.class);

        IFeature newFeature = createSubFeature(1, "menu.others.new",
                createSubFeature(1, createControllerAction("menu.new.kind", simpleController)),
                createSubFeature(2, createControllerAction("menu.new.type", simpleController)),
                createSubFeature(3, createControllerAction("menu.new.language", simpleController)),
                createSubFeature(4, createControllerAction("menu.new.country", simpleController)),
                createSubFeature(5, createControllerAction("menu.others.borrower", borrowerController)),
                createSubFeature(6, createControllerAction("menu.new.saga", simpleController)));

        fillFeature(newFeature, addFeatures);

        return newFeature;
    }

    /**
     * Create the delete menu.
     *
     * @return The Feature for the delete menu.
     */
    private IFeature createDeleteFeature() {
        IController choiceController = applicationContext.getBean("choiceController", IController.class);

        IFeature deleteFeature = createSubFeature(2, "menu.others.delete",
                createSubFeature(1, createControllerAction("menu.delete.kind", choiceController)),
                createSubFeature(2, createControllerAction("menu.delete.type", choiceController)),
                createSubFeature(3, createControllerAction("menu.delete.language", choiceController)),
                createSubFeature(4, createControllerAction("menu.delete.country", choiceController)),
                createSubFeature(5, createControllerAction("menu.delete.borrower", choiceController)),
                createSubFeature(6, createControllerAction("menu.delete.saga", choiceController)));

        fillFeature(deleteFeature, removeFeatures);

        return deleteFeature;
    }

    /**
     * Create the edit menu.
     *
     * @return The Feature for the edit menu.
     */
    private IFeature createEditFeature() {
        IController choiceController = applicationContext.getBean("choiceController", IController.class);

        IFeature editFeature = createSubFeature(3, "menu.others.edit",
                createSubFeature(1, createControllerAction("menu.edit.kind", choiceController)),
                createSubFeature(2, createControllerAction("menu.edit.type", choiceController)),
                createSubFeature(3, createControllerAction("menu.edit.language", choiceController)),
                createSubFeature(4, createControllerAction("menu.edit.country", choiceController)),
                createSubFeature(5, createControllerAction("menu.edit.borrower", choiceController)),
                createSubFeature(6, createControllerAction("menu.edit.saga", choiceController)));

        fillFeature(editFeature, editFeatures);

        return editFeature;
    }

    /**
     * Fill the feature with all the features specified.
     *
     * @param feature  The feature to fill.
     * @param features The sub features to add.
     */
    private static void fillFeature(IFeature feature, Iterable<IFeature> features) {
        for (IFeature f : features) {
            feature.addSubFeature(f);
        }
    }
}
