package org.jtheque.primary.utils.choice;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * A factory of choice actions.
 *
 * @author Baptiste Wicht
 */
public final class ChoiceActionFactory {
    private static final Collection<ChoiceAction> ACTIONS = new ArrayList<ChoiceAction>(6);

    /**
     * Construct a <code>ChoiceActionFactory</code>.
     */
    private ChoiceActionFactory() {
        super();
    }

    /**
     * Get the good choice action for a specific type of action.
     *
     * @param action The type of the action
     *
     * @return The good choice action or null if we don't found it.
     */
    public static ChoiceAction getChoiceAction(String action) {
        for (ChoiceAction choiceAction : ACTIONS) {
            if (choiceAction.canDoAction(action)) {
                return choiceAction;
            }
        }

        return null;
    }

    /**
     * Add a choice action.
     *
     * @param action The choice action.
     */
    public static void addChoiceAction(ChoiceAction action) {
        ACTIONS.add(action);
    }

    public static void addChoiceActions(ChoiceAction[] actions) {
        ACTIONS.addAll(Arrays.asList(actions));
    }

    /**
     * Remove a choice action.
     *
     * @param action The choice action.
     */
    public static void removeChoiceAction(ChoiceAction action) {
        ACTIONS.remove(action);
    }

    /**
     * Remove the specified choice actions.
     *
     * @param choiceActions The actions to remove.
     */
    public static void removeChoiceActions(ChoiceAction[] choiceActions) {
        for (ChoiceAction action : choiceActions) {
            removeChoiceAction(action);
        }
    }
}