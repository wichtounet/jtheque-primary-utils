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
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingBuildedDialogView;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingFilthyBuildedDialogView;
import org.jtheque.core.utils.ui.builders.I18nPanelBuilder;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.view.able.ISimpleDataView;
import org.jtheque.primary.view.impl.actions.simple.ValidateSimpleDataViewAction;
import org.jtheque.primary.view.impl.models.SimpleDataModel;
import org.jtheque.primary.view.impl.models.able.ISimpleDataModel;
import org.jtheque.utils.ui.GridBagUtils;
import org.jtheque.utils.ui.SwingUtils;

import javax.swing.Action;
import javax.swing.JTextField;
import java.util.Collection;

/**
 * A view to edit simple data.
 *
 * @author Baptiste Wicht
 */
public final class SimpleDataView extends SwingFilthyBuildedDialogView<ISimpleDataModel> implements ISimpleDataView {
	private JTextField fieldName;

	private static final int NAME_LENGTH_LIMIT = 100;
	private static final int FIELD_COLUMNS = 15;

	/**
	 * Construct a new SimpleDataView.
	 */
	public SimpleDataView(){
		super();

		build();
	}

	@Override
	protected void initView(){
		setModel(new SimpleDataModel());
	}

	@Override
	protected void buildView(I18nPanelBuilder builder){
		Action validateAction = new ValidateSimpleDataViewAction();

		builder.addI18nLabel("data.view.name", builder.gbcSet(0, 0));

		fieldName = builder.add(new JTextField(FIELD_COLUMNS), builder.gbcSet(1, 0));
		SwingUtils.addFieldValidateAction(fieldName, validateAction);
		SwingUtils.addFieldLengthLimit(fieldName, NAME_LENGTH_LIMIT);

		builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 2, 1),
				validateAction, getCloseAction("data.view.actions.cancel"));
	}

	@Override
	public void reload(){
        SimpleData simpleData = getModel().getSimpleData();

        if(simpleData.isSaved()){
            refreshText();
        } else {
            setTitleKey("data.view.title");
        }

		fieldName.setText(simpleData.getName());
	}

	@Override
	public String getDataName(){
		return fieldName.getText();
	}

	@Override
	public void refreshText(){
		if (getModel().getSimpleData() != null){
			setTitle(getMessage("data.view.title.modify", getModel().getSimpleData().getName()));
		}
	}

	@Override
	protected void validate(Collection<JThequeError> errors){
		ValidationUtils.rejectIfEmpty(fieldName.getText(), "data.view.name", errors);
		ValidationUtils.rejectIfLongerThan(fieldName.getText(), "data.view.name", NAME_LENGTH_LIMIT, errors);
	}
}