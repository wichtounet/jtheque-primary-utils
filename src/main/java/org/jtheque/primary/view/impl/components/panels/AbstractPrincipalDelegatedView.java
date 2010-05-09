package org.jtheque.primary.view.impl.components.panels;

import org.jtheque.primary.od.able.Data;
import org.jtheque.primary.view.able.PrincipalDataView;
import org.jtheque.primary.view.able.ToolbarView;
import org.jtheque.primary.view.impl.listeners.CurrentObjectListener;
import org.jtheque.primary.view.impl.listeners.ObjectChangedEvent;
import org.jtheque.ui.able.IModel;
import org.jtheque.ui.able.IView;
import org.jtheque.views.able.components.MainComponent;
import org.jtheque.views.impl.components.panel.AbstractDelegatedView;

import javax.swing.JComponent;

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

/**
 * An abstract principal delegated view. It's a delegated view for principal data.
 *
 * @author Baptiste Wicht.
 * @param <T> The type of view.
 */
public abstract class AbstractPrincipalDelegatedView<T extends IView> extends AbstractDelegatedView<T>
		implements PrincipalDataView, CurrentObjectListener, MainComponent {
	private AbstractPrincipalDataPanel<? extends IModel> view;

	private final int position;
	private final String titleKey;

	/**
	 * Construct a new AbstractPrincipalDelegatedView.
	 *
	 * @param position The position of of the view in the tabbed pane.
	 * @param titleKey The title key of the tabbed pane.
	 */
	protected AbstractPrincipalDelegatedView(int position, String titleKey){
		super();

		this.position = position;
		this.titleKey = titleKey;
	}

	@Override
	public final Integer getPosition(){
		return position;
	}

	@Override
	public final String getTitleKey(){
		return titleKey;
	}

	@Override
	public final JComponent getComponent(){
		return view;
	}

	@Override
	public final void select(Data data){
		view.select(data);
	}

	@Override
	public final void sort(String sort){
		view.sort(sort);
	}

	@Override
	public final void resort(){
		view.resort();
	}

	@Override
	public final void selectFirst(){
		view.selectFirst();
	}

	@Override
	public final ToolbarView getToolbarView(){
		return view.getToolbarView();
	}

	@Override
	public IModel getModel(){
		return view.getModel();
	}

	@Override
	public final void clear(){
		view.clear();
	}

	@Override
	public void objectChanged(ObjectChangedEvent event){
		view.setCurrent(event.getObject());
	}

	/**
	 * Set the delegated view of the view.
	 *
	 * @param view The delegated view.
	 */
	public final void setView(AbstractPrincipalDataPanel<? extends IModel> view){
		this.view = view;
	}
}