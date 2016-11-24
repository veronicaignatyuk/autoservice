package main.java.bntu.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.java.bntu.connection.DbConnection;
import main.java.bntu.connection.PoolConnection;
import main.java.bntu.dao.abstr.AbstractJDBCDao;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.entity.TechService;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlTechServiceDao extends AbstractJDBCDao<TechService> {
	public Logger Log = LogManager.getLogger(MySqlTechServiceDao.class
			.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");

	@Override
	public String getSelectQuery() {
		return properties.getString("selectService");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertService");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateService");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteService");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("getAllServices");
	}

	@Override
	protected List<TechService> parseResultSet(ResultSet rs)
			throws PersistException {
		List<TechService> result = new ArrayList<TechService>();
		try {
			while (rs.next()) {
				TechService operations = new TechService();
				operations.setId(rs.getInt("id_TechServ"));
				operations.setTitle(rs.getString("title"));
				operations.setCost(rs.getInt("cost"));
				result.add(operations);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			TechService operations) throws PersistException {
		try {
			statement.setInt(1, operations.getId());
			statement.setString(2, operations.getTitle());
			statement.setInt(3, operations.getCost());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			TechService operations) throws PersistException {
		try {
			statement.setString(1, operations.getTitle());
			statement.setInt(2, operations.getCost());

		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public int parseResultSetKey(ResultSet resultSet)
			throws PersistException {
		Integer key = null;
		try {
			while (resultSet.next()) {
				key = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistException(e);
		}
		return key;
	}

	/**
	 * Get all services
	 * 
	 * @return list of services
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<TechService> getAllService() throws PersistException,
			ConnectionPoolException {
		Log.info("Getting all services");
		TechService techService = null;
		List<TechService> techServices = new ArrayList<TechService>();
		String query = properties.getString("selectAllServices");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				techService = new TechService(resultSet.getInt("id_TechServ"),
						resultSet.getString("title"), resultSet.getInt("cost"));
				techServices.add(techService);
			}
		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot get all services", e);
			throw new PersistException("Cannot get all services ", e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		return techServices;
	}

}
