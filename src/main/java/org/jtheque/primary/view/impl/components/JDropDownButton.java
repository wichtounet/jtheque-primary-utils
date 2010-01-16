package org.jtheque.primary.view.impl.components;

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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;
import java.util.Arrays;

/**
 * A drop down button.
 *
 * @author Baptiste Wicht
 */
public final class JDropDownButton extends JPanel implements ActionListener {
	private final JPopupMenu popup;

	/**
	 * Construct a new Drop Down Button with a list of action to provide.
	 *
	 * @param actions All the actions of the Drop Down Button.
	 */
	public JDropDownButton(Iterable<Action> actions){
		super();

		setBorder(null);
		setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
		setBackground(Color.white);

		JButton mainButton = buildMainButton(actions.iterator().next());
		add(mainButton);

		JButton arrowButton = buildArrowButton();
		add(arrowButton);

		calculateSizes(mainButton.getPreferredSize(), arrowButton.getPreferredSize());

		popup = new JPopupMenu();

		for (Action a : actions){
			popup.add(a);
		}
	}

	/**
	 * Construct a new JDropDownButton with the specified actions.
	 *
	 * @param actions All the actions to be displayed in the view.
	 */
	public JDropDownButton(Action... actions){
		this(Arrays.asList(actions));
	}

	/**
	 * Build the main button.
	 *
	 * @param action The action executing by this button.
	 *
	 * @return The builded button.
	 */
	private static JButton buildMainButton(Action action){
		JButton button = new JButton(action);
		button.setRequestFocusEnabled(false);
		button.setRolloverEnabled(true);

		return button;
	}

	/**
	 * Build the arrow button.
	 *
	 * @return The builded button.
	 */
	private JButton buildArrowButton(){
		JButton button = new JButton();

		button.setIcon(new ArrowIcon());
		button.addActionListener(this);
		button.setRequestFocusEnabled(false);
		button.setRolloverEnabled(true);

		return button;
	}

	/**
	 * Define the size of the button basing on the size of the two buttons.
	 *
	 * @param buttonSize1 The preferred size of the main button.
	 * @param buttonSize2 The preferred size of the arrow button.
	 */
	private void calculateSizes(Dimension2D buttonSize1, Dimension2D buttonSize2){
		int width = (int) (buttonSize1.getWidth() + buttonSize2.getWidth());
		int height = (int) Math.max(buttonSize1.getHeight(), buttonSize2.getHeight());

		Dimension dimension = new Dimension(width + 7, height);

		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
	}

	@Override
	public void actionPerformed(ActionEvent event){
		popup.show(this, 0, getHeight());
	}

	/**
	 * An icon representing an arrow.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class ArrowIcon implements Icon {
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y){
			g.setColor(Color.black);
			g.drawLine(x, y, x + 4, y);
			g.drawLine(x + 1, y + 1, x + 3, y + 1);
			g.drawLine(x + 2, y + 2, x + 2, y + 2);
		}

		@Override
		public int getIconWidth(){
			return 6;
		}

		@Override
		public int getIconHeight(){
			return 4;
		}
	}
}