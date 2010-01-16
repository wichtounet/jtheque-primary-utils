package org.jtheque.primary.utils.web.analyzers.generic.value;

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

import org.jtheque.primary.utils.web.analyzers.generic.condition.Condition;
import org.jtheque.primary.utils.web.analyzers.generic.operation.ScannerPossessor;
import org.jtheque.primary.utils.web.analyzers.generic.operation.iterator.IteratorOperation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A value getter who iterate on different lines.
 *
 * @author Baptiste Wicht
 */
public final class IteratorValue implements ValueGetter, BuilderPossessor {
	private final StringBuilder builder;
	private final Collection<IteratorOperation> operationsBefore;
	private final Collection<IteratorOperation> operationsAfter;
	private final Collection<IteratorOperation> operations;
	private Condition condition;

	private final ScannerPossessor analyzer;

	/**
	 * Construct a new IteratorValue.
	 *
	 * @param analyzer The film analyzer.
	 */
	IteratorValue(ScannerPossessor analyzer){
		super();

		this.analyzer = analyzer;

		builder = new StringBuilder(100);
		operationsBefore = new ArrayList<IteratorOperation>(5);
		operationsAfter = new ArrayList<IteratorOperation>(5);
		operations = new ArrayList<IteratorOperation>(5);
	}

	@Override
	public String getValue(String line){
		String current = line;

		for (IteratorOperation operation : operationsBefore){
			current = operation.perform(current, analyzer, this);
		}

		while (condition.match(current)){
			for (IteratorOperation operation : operations){
				current = operation.perform(current, analyzer, this);
			}
		}

		for (IteratorOperation operation : operationsAfter){
			current = operation.perform(current, analyzer, this);
		}

		return builder.toString();
	}

	/**
	 * Add an operation to do before the iteration.
	 *
	 * @param operation The operation to add.
	 */
	public void addOperationsBefore(IteratorOperation operation){
		operationsBefore.add(operation);
	}

	/**
	 * Add an operation to do after the iteration.
	 *
	 * @param operation The operation to add.
	 */
	public void addOperationsAfter(IteratorOperation operation){
		operationsAfter.add(operation);
	}

	/**
	 * Add an operation to do during the iteration.
	 *
	 * @param operation The operation to add.
	 */
	public void addOperations(IteratorOperation operation){
		operations.add(operation);
	}

	/**
	 * Set the condition of the iteration.
	 *
	 * @param condition The condition to set.
	 */
	public void setCondition(Condition condition){
		this.condition = condition;
	}

	@Override
	public StringBuilder getBuilder(){
		return builder;
	}
}
