package org.jtheque.primary.view.impl.choice;

import org.jtheque.core.utils.CoreUtils;
import org.jtheque.primary.PrimaryConstants;
import org.jtheque.primary.controller.impl.undo.GenericDataDeletedEdit;
import org.jtheque.primary.od.able.Lending;
import org.jtheque.primary.od.able.Person;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.services.able.ILendingsService;
import org.jtheque.primary.services.able.IPersonService;
import org.jtheque.primary.services.able.ISimpleDataService;

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
 * An abstract delete action for the choice view.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractPrimaryDeleteChoiceAction extends AbstractDeleteChoiceAction {
	protected final void addPrimaryDeleters(){
		addDeleters(new CountryDeleter(), new BorrowerDeleter(), new SagaDeleter(),
				new KindDeleter(), new TypeDeleter(), new LanguageDeleter(), new LendingDeleter());
	}

	/**
	 * A Deleter for Kind object.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class KindDeleter extends Deleter<SimpleData> {
		/**
		 * Construct a new KindDeleter.
		 */
		private KindDeleter() {
			super(SimpleData.DataType.KIND.getDataType());
		}

		@Override
		public void delete(SimpleData o) {
			addEditIfDeleted(
					CoreUtils.<ISimpleDataService>getBean("kindsService").delete(o),
					new GenericDataDeletedEdit<SimpleData>("kindsService", o));
		}
	}

	/**
	 * A Deleter for Type object.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class TypeDeleter extends Deleter<SimpleData> {
		/**
		 * Construct a new TypeDeleter.
		 */
		private TypeDeleter() {
			super(SimpleData.DataType.TYPE.getDataType());
		}

		@Override
		public void delete(SimpleData o) {
			addEditIfDeleted(
					CoreUtils.<ISimpleDataService>getBean("typesService").delete(o),
					new GenericDataDeletedEdit<SimpleData>("typesService", o));
		}
	}

	/**
	 * A Deleter for Language object.
	 *
	 * @author Baptiste Wicht
	 */
	private static final class LanguageDeleter extends Deleter<SimpleData> {
		/**
		 * Construct a new LanguageDeleter.
		 */
		private LanguageDeleter() {
			super(SimpleData.DataType.LANGUAGE.getDataType());
		}

		@Override
		public void delete(SimpleData o) {
			addEditIfDeleted(
					CoreUtils.<ISimpleDataService>getBean("languagesService").delete(o),
					new GenericDataDeletedEdit<SimpleData>("languagesService", o));
		}
	}

    /**
     * A Deleter for Country object.
     *
     * @author Baptiste Wicht
     */
    private static final class CountryDeleter extends Deleter<SimpleData> {
        /**
         * Construct a new CountryDeleter.
         */
        private CountryDeleter() {
            super(SimpleData.DataType.COUNTRY.getDataType());
        }

        @Override
        public void delete(SimpleData o) {
            addEditIfDeleted(
					CoreUtils.<ISimpleDataService>getBean("countriesService").delete(o),
					new GenericDataDeletedEdit<SimpleData>("countriesService", o));
        }
    }

    /**
     * A Deleter for Borrower object.
     *
     * @author Baptiste Wicht
     */
    private static final class BorrowerDeleter extends Deleter<Person> {
        /**
         * Construct a new BorrowerDeleter.
         */
        private BorrowerDeleter() {
            super(PrimaryConstants.BORROWERS);
        }

        @Override
        public void delete(Person o) {
            addEditIfDeleted(
					CoreUtils.<IPersonService>getBean("borrowersService").delete(o),
					new GenericDataDeletedEdit<Person>("borrowersService", o));
        }
    }

    /**
     * A Deleter for Saga object.
     *
     * @author Baptiste Wicht
     */
    private static final class SagaDeleter extends Deleter<SimpleData> {
        /**
         * Construct a new SagaDeleter.
         */
        private SagaDeleter() {
            super(SimpleData.DataType.SAGA.getDataType());
        }

        @Override
        public void delete(SimpleData o) {
            addEditIfDeleted(
					CoreUtils.<ISimpleDataService>getBean("sagasService").delete(o),
					new GenericDataDeletedEdit<SimpleData>("sagasService", o));
        }
    }

    /**
     * A <code>Deleter</code> for Lending.
     *
     * @author Baptiste Wicht
     */
    private static final class LendingDeleter extends Deleter<Lending> {
        /**
         * Construct a new <code>LendingDeleter</code>.
         */
        private LendingDeleter() {
            super(ILendingsService.DATA_TYPE);
        }

        @Override
        public void delete(Lending o) {
            addEditIfDeleted(
					CoreUtils.<ILendingsService>getBean("lendingsService").delete(o),
					new GenericDataDeletedEdit<Lending>("lendingsService", o));
        }
    }
}