package org.jtheque.primary.dao.impl;

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

import org.jtheque.core.managers.persistence.GenericDao;
import org.jtheque.core.managers.persistence.Query;
import org.jtheque.core.managers.persistence.QueryMapper;
import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.core.managers.persistence.context.IDaoPersistenceContext;
import org.jtheque.primary.PrimaryUtils;
import org.jtheque.primary.dao.able.IDaoSimpleDatas;
import org.jtheque.primary.od.able.PrimaryData;
import org.jtheque.primary.od.able.PrimarySimpleData;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.od.able.SimpleData.DataType;
import org.jtheque.primary.od.impl.PrimarySimpleDataImpl;
import org.jtheque.primary.od.impl.SimpleDataImpl;
import org.jtheque.utils.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A Data Access Object implementation for countries.
 *
 * @author Baptiste Wicht
 */
public final class DaoSimpleDatas extends GenericDao<SimpleData> implements IDaoSimpleDatas {
	private final ParameterizedRowMapper<SimpleData> rowMapper = new SimpleDataRowMapper();
	private final QueryMapper queryMapper = new SimpleDataQueryMapper();

	@Resource
	private IDaoPersistenceContext persistenceContext;

	@Resource
	private SimpleJdbcTemplate jdbcTemplate;

	private final DataType dataType;

	/**
	 * Create a new DaoSimpleDatas.
	 *
	 * @param dataType The data type to manage.
	 */
	public DaoSimpleDatas(String dataType){
		super(DataType.valueOf(dataType).getTable());

		this.dataType = DataType.valueOf(dataType);
	}

	@Override
	public Collection<SimpleData> getSimpleDatas(){
		if (dataType.isPrimary()){
			return getAll(PrimaryUtils.getPrimaryImpl());
		}

		return getAll();
	}

	/**
	 * Return all the simple datas of the primary impl.
	 *
	 * @param impl The primary implementation.
	 *
	 * @return A Collection containing all the simple datas of the primary implementation.
	 */
	private Collection<SimpleData> getAll(CharSequence impl){
		if (StringUtils.isEmpty(impl) || dataType.isPrimary()){
			return getAll();
		}

		load();

		Collection<SimpleData> simpleDatas = new ArrayList<SimpleData>(getCache().size() / 2);

		for (SimpleData simpleData : getCache().values()){
			PrimarySimpleData primarySimpleData = (PrimarySimpleData) simpleData;

			if (impl.equals(primarySimpleData.getPrimaryImpl())){
				simpleDatas.add(primarySimpleData);
			}
		}

		return simpleDatas;
	}

	@Override
	public SimpleData getSimpleData(int id){
		return get(id);
	}

	@Override
	public SimpleData getSimpleData(String name){
		if (dataType.isPrimary()){
			return getSimplePrimaryData(name);
		}

		List<SimpleData> simpleDatas = jdbcTemplate.query("SELECT * FROM " + dataType.getTable() + " WHERE NAME = ?",
				rowMapper, name);

		if (simpleDatas.isEmpty()){
			return null;
		}

		SimpleData simpleData = simpleDatas.get(0);

		if (isNotInCache(simpleData.getId())){
			getCache().put(simpleData.getId(), simpleData);
		}

		return getCache().get(simpleData.getId());
	}

	/**
	 * Return the simple primary data of the specified name.
	 *
	 * @param name The name of the primary data to search.
	 *
	 * @return The simple primary data.
	 */
	private SimpleData getSimplePrimaryData(String name){
		List<SimpleData> types = jdbcTemplate.query("SELECT * FROM " + dataType.getTable() + " WHERE NAME = ? AND IMPL = ?",
				rowMapper, name, PrimaryUtils.getPrimaryImpl());

		if (types.isEmpty()){
			return null;
		}

		SimpleData type = types.get(0);

		if (isNotInCache(type.getId())){
			getCache().put(type.getId(), type);
		}

		return getCache().get(type.getId());
	}

	@Override
	public boolean exist(SimpleData simpleData){
		return getSimpleData(simpleData.getName()) != null;
	}

	@Override
	public SimpleData createSimpleData(){
		return dataType.isPrimary() ?
				new SimpleDataImpl(dataType) :
				new PrimarySimpleDataImpl(dataType, PrimaryUtils.getPrimaryImpl());
	}

	@Override
	protected void loadCache(){
		Collection<SimpleData> simpleDatas = persistenceContext.getSortedList(dataType.getTable(), rowMapper);

		for (SimpleData simpleData : simpleDatas){
			getCache().put(simpleData.getId(), simpleData);
		}

		setCacheEntirelyLoaded();
	}

	@Override
	protected void load(int i){
		SimpleData country = persistenceContext.getDataByID(dataType.getTable(), i, rowMapper);

		getCache().put(i, country);
	}

	@Override
	protected ParameterizedRowMapper<SimpleData> getRowMapper(){
		return rowMapper;
	}

	@Override
	protected QueryMapper getQueryMapper(){
		return queryMapper;
	}

	/**
	 * A row mapper to map resultset to SimpleData.
	 *
	 * @author Baptiste Wicht
	 */
	private final class SimpleDataRowMapper implements ParameterizedRowMapper<SimpleData> {
		@Override
		public SimpleData mapRow(ResultSet rs, int i) throws SQLException{

			SimpleData simpleData = createSimpleData();

			simpleData.setId(rs.getInt("ID"));
			simpleData.setName(rs.getString("NAME"));

			if (dataType.isPrimary()){
				((PrimaryData) simpleData).setPrimaryImpl(rs.getString("IMPL"));
			}

			return simpleData;
		}
	}

	/**
	 * A query mapper to map SimpleData to query.
	 *
	 * @author Baptiste Wicht
	 */
	private final class SimpleDataQueryMapper implements QueryMapper {
		@Override
		public Query constructInsertQuery(Entity entity){
			SimpleData simpleData = (SimpleData) entity;

			String query = "INSERT INTO " + dataType.getTable() + " (NAME) VALUES(?)";

			Object[] parameters = {simpleData.getName()};

			return new Query(query, parameters);
		}

		@Override
		public Query constructUpdateQuery(Entity entity){
			SimpleData simpleData = (SimpleData) entity;

			String query = "UPDATE " + dataType.getTable() + " SET NAME = ? WHERE ID = ?";

			Object[] parameters = {
					simpleData.getName(),
					simpleData.getId()};

			return new Query(query, parameters);
		}
	}
}