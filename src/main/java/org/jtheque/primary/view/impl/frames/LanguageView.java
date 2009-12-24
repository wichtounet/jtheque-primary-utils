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

import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingDialogView;
import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.od.able.Language;
import org.jtheque.primary.view.able.ILanguageView;
import org.jtheque.primary.view.impl.models.able.ILanguageModel;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Frame;
import java.util.Collection;

/**
 * A view to edit or add a language.
 *
 * @author Baptiste Wicht
 */
public final class LanguageView extends SwingDialogView implements ILanguageView {
    private static final long serialVersionUID = -3525319522701158262L;

    private JTextField fieldName;

    private Action validateAction;
    private Action closeAction;

    private static final int NAME_MAX_LENGTH = 150;
    private static final int FIELD_COLUMNS = 15;

    /**
     * Construct a new JFrameLanguage.
     *
     * @param frame The parent frame.
     */
    public LanguageView(Frame frame) {
        super(frame);
    }

    /**
     * Reload all the fields to do an add.
     */
    @Override
    public void reload() {
        setTitleKey("language.view.title");

        fieldName.setText("");

        getModel().setLanguage(null);
    }

    @Override
    public void reload(Data data) {
        Language languageObject = (Language) data;

        setTitle(getMessage("language.view.title.modify") + languageObject.getName());

        fieldName.setText(languageObject.getName());

        getModel().setLanguage(languageObject);
    }

    /**
     * Build the view.
     */
    @PostConstruct
    private void build() {
        setContentPane(buildContentPane());

        pack();
        reload();

        setLocationRelativeTo(getOwner());
    }

    /**
     * Build and return the content pane.
     *
     * @return The builded content pane.
     */
    private Container buildContentPane() {
        PanelBuilder builder = new PanelBuilder();

        builder.addI18nLabel("language.view.name", builder.gbcSet(0, 0));

        fieldName = builder.add(new JTextField(FIELD_COLUMNS), builder.gbcSet(1, 0));
        SwingUtils.addFieldValidateAction(fieldName, validateAction);
        SwingUtils.addFieldLengthLimit(fieldName, NAME_MAX_LENGTH);

        builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.NONE, 2, 1), validateAction, closeAction);

        return builder.getPanel();
    }

    @Override
    public JTextField getFieldNom() {
        return fieldName;
    }

    @Override
    public void refreshText() {
        super.refreshText();

        if (getModel().getLanguage() != null) {
            setTitle(getMessage("language.view.title.modify") + getModel().getLanguage().getName());
        }
    }

    @Override
    public ILanguageModel getModel() {
        return (ILanguageModel) super.getModel();
    }

    @Override
    protected void validate(Collection<JThequeError> errors) {
        ValidationUtils.rejectIfEmpty(fieldName.getText(), "language.view.name", errors);
        ValidationUtils.rejectIfLongerThan(fieldName.getText(), "language.view.name", NAME_MAX_LENGTH, errors);
    }

    /**
     * Set the action to launch the validate the view. This is not for use, this only for Spring Injection.
     *
     * @param validateAction The action.
     */
    public void setValidateAction(Action validateAction) {
        this.validateAction = validateAction;
    }

    /**
     * Set the action to launch the close the view. This is not for use, this only for Spring Injection.
     *
     * @param closeAction The action.
     */
    public void setCloseAction(Action closeAction) {
        this.closeAction = closeAction;
    }
}