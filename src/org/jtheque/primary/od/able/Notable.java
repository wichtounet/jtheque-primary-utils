package org.jtheque.primary.od.able;

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

import org.jtheque.core.utils.db.Note;

/**
 * Represents a data who's notable. It seems a data who has a note.
 *
 * @author Baptiste Wicht
 */
public interface Notable {
    /**
     * Set the note of the object.
     *
     * @param note The new note to set
     */
    void setNote(Note note);

    /**
     * Return the note of the data.
     *
     * @return The note
     */
    Note getNote();
}