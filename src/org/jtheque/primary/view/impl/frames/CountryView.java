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
import org.jtheque.primary.od.able.Country;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.view.able.ICountryView;
import org.jtheque.primary.view.impl.models.able.ICountryModel;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.annotation.PostConstruct;
import javax.swing.Action;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Frame;
import java.util.Collection;

/**
 * A view to edit country.
 *
 * @author Baptiste Wicht
 */
public final class CountryView extends SwingDialogView implements ICountryView {
    private static final long serialVersionUID = -3525319522701158262L;

    private JTextField fieldNom;

    private Action validateAction;
    private Action closeAction;
    private static final int NAME_LENGTH_LIMIT = 50;

    /**
     * Construct a new CountryView.
     *
     * @param frame The parent frame.
     */
    public CountryView(Frame frame) {
        super(frame);
    }

    @Override
    public void reload() {
        setTitleKey("country.view.title");

        fieldNom.setText("");

        getModel().setCountry(null);
    }

    @Override
    public void reload(Data data) {
        Country pays = (Country) data;

        setTitle(getMessage("country.view.title.modify") + pays.getName());

        fieldNom.setText(pays.getName());

        getModel().setCountry(pays);
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

        builder.addI18nLabel("country.view.name", builder.gbcSet(0, 0));

        fieldNom = builder.add(new JTextField(15), builder.gbcSet(1, 0));
        SwingUtils.addFieldValidateAction(fieldNom, validateAction);
        SwingUtils.addFieldLengthLimit(fieldNom, NAME_LENGTH_LIMIT);

        builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 2, 1), validateAction, closeAction);

        return builder.getPanel();
    }

    @Override
    public JTextField getFieldNom() {
        return fieldNom;
    }

    @Override
    public ICountryModel getModel() {
        return (ICountryModel) super.getModel();
    }

    @Override
    public void refreshText() {
        if (getModel().getCountry() != null) {
            setTitle(getMessage("borrower.view.title.edit") + getModel().getCountry().getName());
        }
    }

    @Override
    protected void validate(Collection<JThequeError> jThequeErrors) {
        //Nothing to do
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