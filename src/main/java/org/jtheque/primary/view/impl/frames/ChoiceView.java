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
import org.jtheque.core.managers.persistence.able.DataContainerProvider;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingDialogView;
import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.utils.DataTypeManager;
import org.jtheque.primary.view.able.IChoiceView;
import org.jtheque.primary.view.impl.actions.choice.AcValidateChoiceView;
import org.jtheque.primary.view.impl.models.DataContainerCachedComboBoxModel;
import org.jtheque.utils.ui.SwingUtils;

import javax.swing.Action;
import javax.swing.JComponent;
import java.awt.Container;
import java.util.Collection;

/**
 * A view to choose an object from a list.
 *
 * @author Baptiste Wicht
 */
public final class ChoiceView extends SwingDialogView implements IChoiceView {
	private DataContainerCachedComboBoxModel<?> model;

	private String content;

	/**
	 * Reload the content of the view.
	 *
	 * @param content The content.
	 */
	private void reload(String content){
		this.content = content;

		setTitle(DataTypeManager.getTextForDataType(content));
		setContentPane(buildContentPane());
		pack();
	}

	/**
	 * Build and return the content pane.
	 *
	 * @return Le content pane
	 */
	private Container buildContentPane(){
		PanelBuilder builder = new PanelBuilder();

		model = new DataContainerCachedComboBoxModel(DataContainerProvider.getInstance().getContainerForDataType(content));

		Action validateAction = new AcValidateChoiceView();

		JComponent comboElements = builder.addComboBox(model, builder.gbcSet(0, 0));
		SwingUtils.addFieldValidateAction(comboElements, validateAction);

		builder.addButtonBar(builder.gbcSet(1, 0), validateAction, getCloseAction("choice.actions.cancel"));

		return builder.getPanel();
	}

	@Override
	public Data getSelectedItem(){
		return model.getSelectedData();
	}

	@Override
	public void display(String content){
		reload(content);

		display();
	}

	@Override
	public void refreshText(){
		if (content != null){
			setTitle(DataTypeManager.getTextForDataType(content));
		}
	}

	@Override
	protected void validate(Collection<JThequeError> errors){
		ValidationUtils.rejectIfNothingSelected(model, "choice.view.title", errors);
	}
}