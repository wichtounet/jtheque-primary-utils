package org.jtheque.primary.services.impl;

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

import org.jtheque.core.managers.persistence.able.DataListener;
import org.jtheque.primary.dao.able.IDaoPersons;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.services.able.IBorrowersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * A borrowers service implementation.
 *
 * @author Baptiste Wicht
 */
@Service
public final class BorrowersService implements IBorrowersService {
    @Resource
    private IDaoPersons daoPersons;

    @Override
    @Transactional
    public boolean delete(Person borrower) {
        return daoPersons.delete(borrower);
    }

    @Override
    @Transactional
    public void create(Person borrower) {
        assert borrower != null : "Borrower cannot be null";

        borrower.setType(PERSON_TYPE);

        daoPersons.create(borrower);
    }

    @Override
    @Transactional
    public void save(Person borrower) {
        borrower.setType(PERSON_TYPE);

        daoPersons.save(borrower);
    }

    @Override
    public Collection<Person> getBorrowers() {
        return daoPersons.getPersons(PERSON_TYPE);
    }

    @Override
    public boolean hasNoBorrowers() {
        return getBorrowers().isEmpty();
    }

    @Override
    public void createAll(Iterable<Person> borrowers) {
        for (Person borrower : borrowers) {
            create(borrower);
        }
    }

    @Override
    public Collection<Person> getDatas() {
        return getBorrowers();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoPersons.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoPersons.clearAll(PERSON_TYPE);
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }

    @Override
    public Person getEmptyBorrower() {
        Person borrower = daoPersons.createPerson();

        borrower.setType(PERSON_TYPE);

        return borrower;
    }
}