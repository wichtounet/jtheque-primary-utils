package org.jtheque.primary.impl.views;

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

import org.jtheque.i18n.able.ILanguageService;
import org.jtheque.persistence.utils.DataContainerProvider;
import org.jtheque.primary.able.controller.IChoiceController;
import org.jtheque.primary.able.od.Data;
import org.jtheque.primary.able.views.IChoiceView;
import org.jtheque.primary.impl.views.actions.choice.AcValidateChoiceView;
import org.jtheque.primary.utils.DataTypeManager;
import org.jtheque.primary.utils.views.DataContainerCachedComboBoxModel;
import org.jtheque.ui.able.IModel;
import org.jtheque.ui.utils.builders.FilthyPanelBuilder;
import org.jtheque.ui.utils.builders.PanelBuilder;
import org.jtheque.ui.utils.constraints.AtLeastOneConstraint;
import org.jtheque.ui.utils.windows.dialogs.SwingDialogView;
import org.jtheque.utils.ui.SwingUtils;

import javax.swing.Action;
import javax.swing.JComponent;

import java.awt.Container;

/**
 * A view to choose an object from a list.
 *
 * @author Baptiste Wicht
 */
public final class ChoiceView extends SwingDialogView<IModel> implements IChoiceView {
    private DataContainerCachedComboBoxModel<?> model;

    private String content;

    @Override
    protected void init() {
        //Nothing to init here
    }

    /**
     * Reload the content of the view.
     *
     * @param content The content.
     */
    private void reload(String content) {
        this.content = content;

        setTitleKey(DataTypeManager.getKeyForDataType(content));
        setContentPane(buildContentPane());
        pack();
    }

    /**
     * Build and return the content pane.
     *
     * @return Le content pane
     */
    private Container buildContentPane() {
        PanelBuilder builder = new FilthyPanelBuilder();

        model = new DataContainerCachedComboBoxModel(DataContainerProvider.getInstance().getContainerForDataType(content));

        addConstraint(model, new AtLeastOneConstraint("choice.view.title"));

        Action validateAction = new AcValidateChoiceView(getService(IChoiceController.class));

        JComponent comboElements = builder.addComboBox(model, builder.gbcSet(0, 0));
        SwingUtils.addFieldValidateAction(comboElements, validateAction);

        builder.addButtonBar(builder.gbcSet(1, 0), validateAction, getCloseAction("choice.actions.cancel"));

        return builder.getPanel();
    }

    @Override
    public Data getSelectedItem() {
        return model.getSelectedData();
    }

    @Override
    public void display(String content) {
        reload(content);

        display();
    }

    @Override
    public void refreshText(ILanguageService languageService) {
        if (content != null) {
            setTitle(languageService.getMessage(DataTypeManager.getKeyForDataType(content)));
        }
    }
}