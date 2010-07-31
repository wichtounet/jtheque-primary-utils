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

import org.jtheque.i18n.able.LanguageService;
import org.jtheque.primary.able.od.SimpleData;
import org.jtheque.primary.able.views.ISimpleDataView;
import org.jtheque.primary.able.views.model.ISimpleDataModel;
import org.jtheque.primary.impl.views.model.SimpleDataModel;
import org.jtheque.ui.utils.builders.I18nPanelBuilder;
import org.jtheque.ui.able.constraints.Constraints;
import org.jtheque.ui.utils.windows.dialogs.SwingFilthyBuildedDialogView;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.swing.Action;
import javax.swing.JTextField;

/**
 * A view to edit simple data.
 *
 * @author Baptiste Wicht
 */
public final class SimpleDataView extends SwingFilthyBuildedDialogView<ISimpleDataModel> implements ISimpleDataView {
    private static final int NAME_LENGTH_LIMIT = 100;
    private static final int FIELD_COLUMNS = 15;

    private JTextField fieldName;

    @Override
    protected void initView() {
        setModel(new SimpleDataModel());
    }

    @Override
    protected void buildView(I18nPanelBuilder builder) {
        Action validateAction = getAction("data.view.actions.ok");

        builder.addI18nLabel("data.view.name", builder.gbcSet(0, 0));

        fieldName = builder.add(new JTextField(FIELD_COLUMNS), builder.gbcSet(1, 0));
        SwingUtils.addFieldValidateAction(fieldName, validateAction);

        addConstraint(fieldName, Constraints.max(NAME_LENGTH_LIMIT, "data.view.name", false, false));

        builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 2, 1),
                validateAction, getAction("data.view.actions.cancel"));
    }

    @Override
    public void reload() {
        SimpleData simpleData = getModel().getSimpleData();

        if (simpleData.isSaved()) {
            refreshText(getService(LanguageService.class));
        } else {
            setTitleKey("data.view.title");
        }

        fieldName.setText(simpleData.getName());
    }

    @Override
    public String getDataName() {
        return fieldName.getText();
    }

    @Override
    public void refreshText(LanguageService languageService) {
        if (getModel().getSimpleData() != null) {
            setTitle(getMessage("data.view.title.modify", getModel().getSimpleData().getName()));
        }
    }
}