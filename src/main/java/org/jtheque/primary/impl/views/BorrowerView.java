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
import org.jtheque.primary.able.controller.IBorrowerController;
import org.jtheque.primary.able.od.Person;
import org.jtheque.primary.able.views.IBorrowerView;
import org.jtheque.primary.able.views.model.IBorrowerModel;
import org.jtheque.primary.impl.views.actions.borrower.AcValidateBorrowerView;
import org.jtheque.primary.impl.views.model.BorrowerModel;
import org.jtheque.ui.able.constraints.Constraints;
import org.jtheque.ui.utils.builders.I18nPanelBuilder;
import org.jtheque.ui.utils.windows.dialogs.SwingFilthyBuildedDialogView;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.swing.Action;
import javax.swing.JTextField;

/**
 * A view to edit borrower.
 *
 * @author Baptiste Wicht
 */
public final class BorrowerView extends SwingFilthyBuildedDialogView<IBorrowerModel> implements IBorrowerView {
    private static final int DEFAULT_COLUMNS = 15;
    private static final int DEFAULT_FIELD_LENGTH = 100;

    private JTextField fieldNom;
    private JTextField fieldFirstName;
    private JTextField fieldEmail;

    @Override
    protected void initView() {
        setModel(new BorrowerModel());
    }

    @Override
    protected void buildView(I18nPanelBuilder builder) {
        builder.addI18nLabel("borrower.view.name", builder.gbcSet(0, 0));
        fieldNom = builder.add(new JTextField(DEFAULT_COLUMNS), builder.gbcSet(1, 0));

        builder.addI18nLabel("borrower.view.firstname", builder.gbcSet(0, 1));
        fieldFirstName = builder.add(new JTextField(DEFAULT_COLUMNS), builder.gbcSet(1, 1));

        builder.addI18nLabel("borrower.view.mail", builder.gbcSet(0, 2));
        fieldEmail = builder.add(new JTextField(DEFAULT_COLUMNS), builder.gbcSet(1, 2));

        Action validateAction = new AcValidateBorrowerView(getService(IBorrowerController.class));

        builder.addButtonBar(builder.gbcSet(0, 3, GridBagUtils.NONE, GridBagUtils.BASELINE_LEADING, 2, 1),
                validateAction, getCloseAction("borrower.actions.cancel"));

        addConstraint(fieldNom, Constraints.max(DEFAULT_FIELD_LENGTH, "borrower.view.name", false, false));
        addConstraint(fieldFirstName, Constraints.max(DEFAULT_FIELD_LENGTH, "borrower.view.firstname", false, false));
        addConstraint(fieldEmail, Constraints.max(DEFAULT_FIELD_LENGTH, "borrower.view.mail", false, false));

        SwingUtils.addFieldsValidateAction(validateAction, fieldNom, fieldFirstName, fieldEmail);
    }

    @Override
    public void reload() {
        Person borrower = getModel().getBorrower();

        if (borrower.isSaved()) {
            setTitle(getMessage("borrower.view.title.edit") + borrower.getName());
        } else {
            setTitleKey("borrower.view.title");
        }

        fieldNom.setText(borrower.getName());
        fieldFirstName.setText(borrower.getFirstName());
        fieldEmail.setText(borrower.getEmail());
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
    public void refreshText(ILanguageService languageService) {
        super.refreshText(languageService);

        if (getModel().getBorrower() != null) {
            setTitle(getMessage("borrower.view.title.edit") + getModel().getBorrower().getName());
        }
    }
}