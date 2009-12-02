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
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.services.able.IBorrowersService;
import org.jtheque.primary.view.able.IBorrowerView;
import org.jtheque.primary.view.impl.models.able.IBorrowerModel;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Frame;
import java.util.Collection;

/**
 * A view to edit borrower.
 *
 * @author Baptiste Wicht
 */
public final class BorrowerView extends SwingDialogView implements IBorrowerView {
    private static final long serialVersionUID = -962666643153770662L;

    private JTextField fieldNom;
    private JTextField fieldFirstName;
    private JTextField fieldEmail;

    private Action validateAction;
    private Action closeAction;
    private static final int DEFAULT_COLUMNS = 15;
    private static final int DEFAULT_FIELD_LENGTH = 50;

    /**
     * Construct a new BorrowerView.
     *
     * @param frame The parent frame
     */
    public BorrowerView(Frame frame) {
        super(frame);
    }

    @Override
    public void reload() {
        setTitleKey("borrower.view.title");

        fieldNom.setText("");
        fieldFirstName.setText("");
        fieldEmail.setText("");

        getModel().setBorrower(null);
    }

    @Override
    public void reload(Data data) {
        Person borrower = (Person) data;

        assert borrower.getType().equals(IBorrowersService.PERSON_TYPE) : "The person must be a borrower";

        setTitle(getMessage("borrower.view.title.edit") + borrower.getName());

        fieldNom.setText(borrower.getName());
        fieldFirstName.setText(borrower.getFirstName());
        fieldEmail.setText(borrower.getEmail());

        getModel().setBorrower(borrower);
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
     * Build and return the content pane.
     *
     * @return The content pane.
     */
    private Container buildContentPane() {
        PanelBuilder builder = new PanelBuilder();

        builder.addI18nLabel("borrower.view.name", builder.gbcSet(0, 0));
        builder.addI18nLabel("borrower.view.firstname", builder.gbcSet(0, 1));
        builder.addI18nLabel("borrower.view.mail", builder.gbcSet(0, 2));

        fieldNom = builder.add(new JTextField(DEFAULT_COLUMNS), builder.gbcSet(1, 0));
        SwingUtils.addFieldValidateAction(fieldNom, validateAction);
        SwingUtils.addFieldLengthLimit(fieldNom, DEFAULT_FIELD_LENGTH);

        fieldFirstName = builder.add(new JTextField(DEFAULT_COLUMNS), builder.gbcSet(1, 1));
        SwingUtils.addFieldValidateAction(fieldFirstName, validateAction);
        SwingUtils.addFieldLengthLimit(fieldFirstName, DEFAULT_FIELD_LENGTH);

        fieldEmail = builder.add(new JTextField(DEFAULT_COLUMNS), builder.gbcSet(1, 2));
        SwingUtils.addFieldValidateAction(fieldEmail, validateAction);
        SwingUtils.addFieldLengthLimit(fieldEmail, DEFAULT_FIELD_LENGTH);

        builder.addButtonBar(builder.gbcSet(0, 3, GridBagUtils.NONE, GridBagUtils.BASELINE_LEADING, 2, 1), validateAction, closeAction);

        return builder.getPanel();
    }

    @Override
    public JTextField getFieldNom() {
        return fieldNom;
    }

    @Override
    public JTextField getFieldFirstName() {
        return fieldFirstName;
    }

    @Override
    public JTextField getFieldEmail() {
        return fieldEmail;
    }

    @Override
    public void refreshText() {
        super.refreshText();

        if (getModel().getBorrower() != null) {
            setTitle(getMessage("borrower.view.title.edit") + getModel().getBorrower().getName());
        }
    }

    @Override
    public IBorrowerModel getModel() {
        return (IBorrowerModel) super.getModel();
    }

    @Override
    protected void validate(Collection<JThequeError> errors) {
        ValidationUtils.rejectIfEmpty(fieldNom.getText(), "borrower.view.name", errors);
        ValidationUtils.rejectIfEmpty(fieldFirstName.getText(), "borrower.view.firstname", errors);

        ValidationUtils.rejectIfLongerThan(fieldNom.getText(), "borrower.view.name", 100, errors);
        ValidationUtils.rejectIfLongerThan(fieldFirstName.getText(), "borrower.view.firstname", 100, errors);
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