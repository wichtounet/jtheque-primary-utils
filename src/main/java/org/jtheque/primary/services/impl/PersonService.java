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
import org.jtheque.primary.services.able.INotesService;
import org.jtheque.primary.services.able.IPersonService;
import org.jtheque.primary.services.able.ISimpleDataService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * The implementation of the person service.
 *
 * @author Baptiste Wicht
 */
public class PersonService implements IPersonService {
	private Person emptyActor;

	private final String personType;
	private final String dataType;

	@Resource
	private INotesService notesService;

	@Resource
	private ISimpleDataService countriesService;

	@Resource
	private IDaoPersons daoPersons;

	/**
	 * Construct a new PersonService.
	 *
	 * @param personType The type of persons.
	 * @param dataType The type of datas.
	 */
	public PersonService(String personType, String dataType){
		super();

		this.personType = personType;
		this.dataType = dataType;
	}

	@Override
	@Transactional
	public final void create(Person person){
		person.setType(personType);

		daoPersons.create(person);
	}

	@Override
	@Transactional
	public void save(Person person){
		person.setType(personType);

		daoPersons.save(person);
	}

	@Override
	@Transactional
	public boolean delete(Person actor){
		return daoPersons.delete(actor);
	}

	@Override
	public final boolean hasNoPerson(){
		return getPersons().isEmpty();
	}

	@Override
	public final Collection<Person> getPersons(){
		return daoPersons.getPersons(personType);
	}

	@Override
	public final boolean exist(Person actor){
		return daoPersons.exist(actor);
	}

	@Override
	public final Person getPerson(String firstName, String name){
		return daoPersons.getPerson(firstName, name, personType);
	}

	@Override
	public final boolean exist(String firstName, String name){
		return daoPersons.exists(firstName, name, personType);
	}

	@Override
	public final Collection<Person> getDatas(){
		return getPersons();
	}

	@Override
	public final void addDataListener(DataListener listener){
		daoPersons.addDataListener(listener);
	}

	@Override
	public Person getDefaultPerson(){
		if (emptyActor == null){
			emptyActor = getEmptyPerson();

			emptyActor.setName("");
			emptyActor.setFirstName("");
			emptyActor.setNote(notesService.getDefaultNote());
			emptyActor.setTheCountry(countriesService.getDefaultSimpleData());
		}

		return emptyActor;
	}

	@Override
	public Person getEmptyPerson(){
		Person person = daoPersons.createPerson();

		person.setType(personType);

		return person;
	}

	@Override
	@Transactional
	public final void clearAll(){
		daoPersons.clearAll();
	}

	@Override
	public final String getDataType(){
		return dataType;
	}

	@Override
	public void createAll(Iterable<Person> persons){
		for (Person person : persons){
			create(person);
		}
	}

	@Override
	public String getPersonType(){
		return personType;
	}
}