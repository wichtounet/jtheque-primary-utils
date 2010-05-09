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

import org.jtheque.persistence.CachedJDBCDao;
import org.jtheque.persistence.Query;
import org.jtheque.persistence.able.Entity;
import org.jtheque.persistence.able.QueryMapper;
import org.jtheque.persistence.context.IDaoPersistenceContext;
import org.jtheque.primary.IPrimaryUtils;
import org.jtheque.primary.dao.able.IDaoSimpleDatas;
import org.jtheque.primary.od.able.PrimaryData;
import org.jtheque.primary.od.able.PrimarySimpleData;
import org.jtheque.primary.od.able.SimpleData;
import org.jtheque.primary.od.able.SimpleData.DataType;
import org.jtheque.primary.od.impl.PrimarySimpleDataImpl;
import org.jtheque.primary.od.impl.SimpleDataImpl;
import org.jtheque.utils.StringUtils;
import org.springframework.jdbc.core.RowMapper;
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
public final class DaoSimpleDatas extends CachedJDBCDao<SimpleData> implements IDaoSimpleDatas {
	private final RowMapper<SimpleData> rowMapper = new SimpleDataRowMapper();

	private final QueryMapper queryMapper;

	@Resource
	private IDaoPersistenceContext daoPersistenceContext;

	@Resource
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource
	private IPrimaryUtils primaryUtils;

	private final DataType dataType;

	/**
	 * Create a new DaoSimpleDatas.
	 *
	 * @param dataTypeStr The data type to manage.
	 */
	public DaoSimpleDatas(String dataTypeStr){
		super(DataType.valueOf(dataTypeStr).getTable());

		dataType = DataType.valueOf(dataTypeStr);

        queryMapper = dataType.isPrimary() ? new PrimarySimpleDataQueryMapper() : new SimpleDataQueryMapper();
	}

	@Override
	public Collection<SimpleData> getSimpleDatas(){
		if (dataType.isPrimary()){
			return getAll(primaryUtils.getPrimaryImpl());
		}

		return getAll();
	}

    @Override
    public SimpleData getSimpleDataByTemporaryId(int id) {
        for(SimpleData simpleData : getAll()){
            if(simpleData.getTemporaryContext().getId() == id){
                return simpleData;
            }
        }

        return null;
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
				rowMapper, name, primaryUtils.getPrimaryImpl());

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
	public boolean exists(SimpleData simpleData){
		return getSimpleData(simpleData.getName()) != null;
	}

	@Override
	public SimpleData create(){
		return dataType.isPrimary() ?
                new PrimarySimpleDataImpl(dataType, primaryUtils.getPrimaryImpl()) :
				new SimpleDataImpl(dataType);
	}

	@Override
	protected void loadCache(){
		Collection<SimpleData> simpleDatas = daoPersistenceContext.getSortedList(dataType.getTable(), rowMapper);

		for (SimpleData simpleData : simpleDatas){
			getCache().put(simpleData.getId(), simpleData);
		}

		setCacheEntirelyLoaded();
	}

	@Override
	protected void load(int i){
		SimpleData country = daoPersistenceContext.getDataByID(dataType.getTable(), i, rowMapper);

		getCache().put(i, country);
	}

	@Override
	protected RowMapper<SimpleData> getRowMapper(){
		return rowMapper;
	}

	@Override
	protected QueryMapper getQueryMapper(){
		return queryMapper;
	}

	@Override
	public boolean isPrimary() {
		return dataType.isPrimary();
	}

	/**
	 * A row mapper to map resultset to SimpleData.
	 *
	 * @author Baptiste Wicht
	 */
	private final class SimpleDataRowMapper implements ParameterizedRowMapper<SimpleData> {
		@Override
		public SimpleData mapRow(ResultSet rs, int i) throws SQLException{

			SimpleData simpleData = create();

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

	/**
	 * A query mapper to map SimpleData to query.
	 *
	 * @author Baptiste Wicht
	 */
	private final class PrimarySimpleDataQueryMapper implements QueryMapper {
		@Override
		public Query constructInsertQuery(Entity entity){
			PrimarySimpleData simpleData = (PrimarySimpleData) entity;

			String query = "INSERT INTO " + dataType.getTable() + " (NAME, IMPL) VALUES(?, ?)";

			Object[] parameters = {simpleData.getName(), simpleData.getPrimaryImpl()};

			return new Query(query, parameters);
		}

		@Override
		public Query constructUpdateQuery(Entity entity){
			PrimarySimpleData simpleData = (PrimarySimpleData) entity;

			String query = "UPDATE " + dataType.getTable() + " SET NAME = ?, IMPL = ? WHERE ID = ?";

			Object[] parameters = {
					simpleData.getName(),
                    simpleData.getPrimaryImpl(),
					simpleData.getId()};

			return new Query(query, parameters);
		}
	}
}