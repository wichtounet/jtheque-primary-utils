package org.jtheque.primary.controller.impl;

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

import org.jtheque.primary.controller.able.ControllerState;
import org.jtheque.primary.controller.able.FormBean;
import org.jtheque.primary.controller.able.IPrincipalController;
import org.jtheque.primary.od.able.Data;
import org.jtheque.views.able.IViews;
import org.jtheque.views.able.components.MainComponent;
import org.jtheque.views.impl.AbstractController;

import javax.annotation.Resource;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;
import java.util.Collection;

/**
 * An abstract principal Controller.
 *
 * @author Baptiste Wicht
 */
public abstract class PrincipalController<T extends Data> extends AbstractController implements IPrincipalController<T> {
	private ControllerState state;
	private final ControllerState viewState;
	private final ControllerState modifyState;
	private final ControllerState newObjectState;
	private final ControllerState autoAddState;

    @Resource
    private IViews views;

	/**
	 * Construct a new PrincipalController.
	 *
	 * @param viewState The view state.
	 * @param modifyState The modify state.
	 * @param newObjectState The new state.
	 * @param autoAddState The auto add state.
	 */
	protected PrincipalController(ControllerState viewState, ControllerState modifyState,
								  ControllerState newObjectState, ControllerState autoAddState){
		super();

		this.viewState = viewState;
		this.modifyState = modifyState;
		this.newObjectState = newObjectState;
		this.autoAddState = autoAddState;

		state = viewState;
	}

	@Override
	public final void displayView(){
		views.setSelectedView((MainComponent) getView());

		super.displayView();
	}

	@Override
	public final void valueChanged(TreeSelectionEvent event){
		TreePath current = ((JTree) event.getSource()).getSelectionPath();

		if (current != null && current.getLastPathComponent() instanceof Data){
			T data = (T) current.getLastPathComponent();

			if (data != null){
				view(data);
			}
		}
	}

	@Override
	public final void save(FormBean formBean){
		ControllerState newState = state.save(formBean);

		if (newState != null){
			setAndApplyState(newState);
		}
	}

	@Override
	public final void view(T data){
		ControllerState newState = state.view(data);

		if (newState != null){
			setAndApplyState(newState);
		}
	}

	@Override
	public final void manualEdit(){
		ControllerState newState = state.manualEdit();

		if (newState != null){
			setAndApplyState(newState);
		}
	}

	@Override
	public final void create(){
		ControllerState newState = state.create();

		if (newState != null){
			setAndApplyState(newState);
		}
	}

	@Override
	public final void deleteCurrent(){
		ControllerState newState = state.delete();

		if (newState != null){
			setAndApplyState(newState);
		}
	}

	@Override
	public final void cancel(){
		ControllerState newState = state.cancel();

		if (newState != null){
			setAndApplyState(newState);
		}
	}

	@Override
	public final ControllerState getViewState(){
		return viewState;
	}

	@Override
	public final ControllerState getAutoAddState(){
		return autoAddState;
	}

	@Override
	public final ControllerState getModifyState(){
		return modifyState;
	}

	@Override
	public final ControllerState getNewObjectState(){
		return newObjectState;
	}

	@Override
	public Collection<T> getDisplayList(){
		return getViewModel().getDisplayList();
	}

	/**
	 * Return the state of the controller.
	 *
	 * @return The state of the controller.
	 */
	protected final ControllerState getState(){
		return state;
	}

	/**
	 * Set the state of the controller.
	 *
	 * @param state The new state to apply.
	 */
	protected final void setAndApplyState(ControllerState state){
		this.state = state;

		state.apply();
	}
}