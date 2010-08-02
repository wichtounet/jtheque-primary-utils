package org.jtheque.primary.able.views;

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

import org.jtheque.primary.able.views.model.IBorrowerModel;

import javax.swing.JTextField;

/**
 * A borrower view specification.
 *
 * @author Baptiste Wicht
 */
public interface IBorrowerView extends DataView, org.jtheque.ui.able.View {
    /**
     * Return the text field containing the name of the borrower.
     *
     * @return The text field who contains the name.
     */
    JTextField getFieldNom();

    /**
     * Return the text field containing the first name of the borrower.
     *
     * @return The text field who contains the first name.
     */
    JTextField getFieldFirstName();

    /**
     * Return the text field containing the email of the borrower.
     *
     * @return The text field who contains the email.
     */
    JTextField getFieldEmail();

    @Override
    IBorrowerModel getModel();
}
