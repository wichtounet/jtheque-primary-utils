package org.jtheque.primary.view.impl.frames;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingDialogView;
import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.od.able.Saga;
import org.jtheque.primary.view.able.ISagaView;
import org.jtheque.primary.view.impl.models.able.ISagaModel;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Frame;
import java.util.Collection;

/**
 * The view for saga.
 *
 * @author Baptiste Wicht
 */
public final class SagaView extends SwingDialogView implements ISagaView {
    private static final long serialVersionUID = -3525319522701158262L;

    private JTextField fieldName;

    private Action validateAction;
    private Action closeAction;

    /**
     * Construct a new JFrameSaga modal to his parent view.
     *
     * @param frame The parent frame.
     */
    public SagaView(Frame frame) {
        super(frame);
    }

    @Override
    public void reload() {
        setTitleKey("saga.view.title");

        fieldName.setText("");

        getModel().setSaga(null);
    }

    @Override
    public void reload(Data newEditor) {
        Saga saga = (Saga) newEditor;

        setTitle(Managers.getManager(ILanguageManager.class).getMessage("saga.view.title.modify") + saga.getName());

        fieldName.setText(saga.getName());

        getModel().setSaga(saga);
    }

    /**
     * Build the view.
     */
    @PostConstruct
    public void build() {
        setContentPane(buildContentPane());

        pack();
        reload();

        setLocationRelativeTo(getOwner());
    }

    /**
     * Build the content pane.
     *
     * @return The builded content pane.
     */
    private Container buildContentPane() {
        PanelBuilder builder = new PanelBuilder();

        builder.addI18nLabel("saga.view.name", builder.gbcSet(0, 0));

        fieldName = builder.add(new JTextField(15), builder.gbcSet(1, 0, GridBagUtils.HORIZONTAL));
        SwingUtils.addFieldLengthLimit(fieldName, 50);
        SwingUtils.addFieldValidateAction(fieldName, validateAction);

        builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.HORIZONTAL), validateAction, closeAction);

        return builder.getPanel();
    }

    @Override
    public JTextField getFieldName() {
        return fieldName;
    }

    @Override
    public void refreshText() {
        super.refreshText();

        if (getModel().getSaga() != null) {
            setTitle(Managers.getManager(ILanguageManager.class).getMessage("saga.view.title.modify") +
                    getModel().getSaga().getName());
        }
    }

    @Override
    public ISagaModel getModel() {
        return (ISagaModel) super.getModel();
    }

    @Override
    protected void validate(Collection<JThequeError> errors) {
        ValidationUtils.rejectIfEmpty(fieldName.getText(), "saga.view.name", errors);
        ValidationUtils.rejectIfLongerThan(fieldName.getText(), "saga.view.name", 150, errors);
    }

    /**
     * Set the action to use to validate the view.
     *
     * @param validateAction The action to validate the view.
     */
    public void setValidateAction(Action validateAction) {
        this.validateAction = validateAction;
    }

    /**
     * Set the action to close the view.
     *
     * @param closeAction The action to close the view.
     */
    public void setCloseAction(Action closeAction) {
        this.closeAction = closeAction;
    }
}