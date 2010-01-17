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
import org.jtheque.primary.dao.able.IDaoSimpleDatas;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.services.able.ISimpleDataService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * A simple data service implementation.
 *
 * @author Baptiste Wicht
 */
public final class SimpleDatasService implements ISimpleDataService {
	private SimpleData defaultSimpleData;

	private final IDaoSimpleDatas daoSimpleDatas;

	/**
	 * Construct a new SimpleDatasService.
	 *
	 * @param daoSimpleDatas The dao to use.
	 */
	public SimpleDatasService(IDaoSimpleDatas daoSimpleDatas){
		super();

		this.daoSimpleDatas = daoSimpleDatas;
	}

	@Override
	public SimpleData getDefaultSimpleData(){
		if (defaultSimpleData == null){
			defaultSimpleData = daoSimpleDatas.getSimpleData("Unknown");

			if (defaultSimpleData == null){
				createDefaultSimpleData();
			}
		}

		return defaultSimpleData;
	}

	/**
	 * Create the default country.
	 */
	@Transactional
	private void createDefaultSimpleData(){
		defaultSimpleData = daoSimpleDatas.createSimpleData();
		defaultSimpleData.setName("Unknown");
		daoSimpleDatas.create(defaultSimpleData);
	}

	@Override
	@Transactional
	public void save(SimpleData simpleData){
		daoSimpleDatas.save(simpleData);
	}

	@Override
	@Transactional
	public void create(SimpleData simpleData){
		daoSimpleDatas.create(simpleData);
	}

	@Override
	@Transactional
	public boolean delete(SimpleData simpleData){
		return daoSimpleDatas.delete(simpleData);
	}

	@Override
	public SimpleData getSimpleData(String name){
		return daoSimpleDatas.getSimpleData(name);
	}

	@Override
	@Transactional
	public void createAll(Iterable<SimpleData> simpleDatas){
		for (SimpleData simpleData : simpleDatas){
			daoSimpleDatas.create(simpleData);
		}
	}

	@Override
	public boolean exist(SimpleData simpleData){
		return daoSimpleDatas.exist(simpleData);
	}

	@Override
	public SimpleData getEmptySimpleData(){
		return daoSimpleDatas.createSimpleData();
	}

	@Override
	public boolean exist(String name){
		return getSimpleData(name) != null;
	}

	@Override
	public boolean hasNoDatas(){
		return daoSimpleDatas.getSimpleDatas().isEmpty();
	}

	@Override
	public Collection<SimpleData> getDatas(){
		return daoSimpleDatas.getSimpleDatas();
	}

	@Override
	public void addDataListener(DataListener listener){
		daoSimpleDatas.addDataListener(listener);
	}

	@Override
	@Transactional
	public void clearAll(){
		daoSimpleDatas.clearAll();
	}

	@Override
	public String getDataType(){
		return DATA_TYPE;
	}
}