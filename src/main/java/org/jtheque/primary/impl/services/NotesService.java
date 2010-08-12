package org.jtheque.primary.impl.services;

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

import org.jtheque.persistence.DaoNotes;
import org.jtheque.persistence.Note;
import org.jtheque.primary.able.services.INotesService;

import javax.annotation.Resource;

/**
 * A notes service implementation.
 *
 * @author Baptiste Wicht
 */
public final class NotesService implements INotesService {
    @Resource
    private DaoNotes daoNotes;

    @Override
    public Note getDefaultNote() {
        return daoNotes.getDefaultNote();
    }
}