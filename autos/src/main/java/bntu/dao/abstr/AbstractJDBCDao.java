package main.java.bntu.dao.abstr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.java.bntu.connection.DbConnection;
import main.java.bntu.connection.PoolConnection;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Defines common operations with data source
 * 
 * @author Veronika
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractJDBCDao<T extends Identified<Integer>> implements
		GenericDao<T> {
	public Logger Log = LogManager.getLogger(AbstractJDBCDao.class.getName());
	public final ResourceBundle request = ResourceBundle
			.getBundle("resources/sqlrequest");

	public abstract String getSelectQuery() throws FileNotFoundException,
			IOException;

	public abstract String getInsertQuery();

	public abstract String getUpdateQuery();

	public abstract String getDeleteQuery();

	public abstract String getAllQuery();

	protected abstract List<T> parseResultSet(ResultSet rs)
			throws PersistException;

	public abstract int parseResultSetKey(ResultSet resultSet)
			throws PersistException;

	protected abstract void prepareStatementForInsert(
			PreparedStatement statement, T object) throws PersistException;

	protected abstract void prepareStatementForUpdate(
			PreparedStatement statement, T object) throws PersistException;

	@Override
	public int create(T object) throws PersistException,
			ConnectionPoolException {
		Log.debug("Creating new " + object);
		int key = 0;
		// Get insert query
		String query = getInsertQuery();
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			// Open connection
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			// Create new object
			pStatement = connection.prepareStatement(query);
			prepareStatementForInsert(pStatement, object);
			Log.debug("Getting result set");
			pStatement.executeUpdate();
		}
		// Get exception
		catch (SQLException e) {
			Log.error("Cannot create " + object, e);
			throw new PersistException("Cannot create " + object, e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		Log.debug(object + " was created");
		// Return object
		return key;
	}

	/**
	 * Close all resources
	 * 
	 * @param connection2
	 * @param statement
	 * @param resultSet
	 */
	public void closeResources(DbConnection connection2,
			PreparedStatement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
				Log.debug("ResultSet closed");
			} catch (SQLException e) {
				Log.warn("Cannot close ResultSet", e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
				Log.debug("Statement closed");
			} catch (SQLException e) {
				Log.warn("Cannot close Statement", e);
			}
		}
		if (connection2 != null) {
			connection2.close();
			Log.debug("Connection closed");
		}
	}

	/**
	 * Close 2 resources
	 * 
	 * @param connection
	 * @param statement
	 */
	public void closeResources(DbConnection connection,
			PreparedStatement statement) {
		closeResources(connection, statement, null);
	}

	/**
	 * Close resultSet resources
	 * 
	 * @param resultSet
	 */
	public void closeResources(ResultSet resultSet) {
		closeResources(null, null, resultSet);
	}

	@Override
	public T getByPK(int key) throws PersistException, FileNotFoundException,
			IOException, ConnectionPoolException {
		Log.debug("Get object by key:" + key);
		List<T> listEntities = new ArrayList<T>();
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		// Get Select Query
		String sql = getSelectQuery();
		try {
			Log.trace("Open connection");
			// Get connection
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			// Get object by PK
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, key);
			resultSet = pStatement.executeQuery();
			Log.debug("Getting result set");
			listEntities = parseResultSet(resultSet);
		}
		// Get exceptions
		catch (Exception e) {
			Log.error("Cannot get object by key : " + key);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		if (listEntities.isEmpty()) {
			throw new PersistException("Record with PK = " + key
					+ " not found.");
		}
		if (listEntities.size() > 1) {
			throw new PersistException("Received more than one record.");
		}
		// Return object
		return listEntities.iterator().next();
	}

	@Override
	public void update(T object) throws PersistException,
			ConnectionPoolException {
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		// Get Update query
		String sql = getUpdateQuery();
		try {
			Log.trace("Open connection");
			// Get connection
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			// Update object in BD
			pStatement = connection.prepareStatement(sql);
			prepareStatementForUpdate(pStatement, object);
			int count = pStatement.executeUpdate();
			// Get exceptions
			if (count != 1) {
				throw new Exception("On update modify more then 1 record: "
						+ count);
			}
		} catch (Exception e) {
			Log.error("Cannot update");
			throw new PersistException(e);
		} finally {
			closeResources(connection, pStatement);
		}
	}

	@Override
	public void delete(int key) throws PersistException,
			ConnectionPoolException {
		// Get delete query
		String sql = getDeleteQuery();
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		try {
			Log.trace("Open connection");
			// Open connection
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			// Create prepare statement
			pStatement = connection.prepareStatement(sql);
			pStatement.setObject(1, key);
			// delete object by key
			pStatement.executeUpdate();
		}
		// Get exceptions
		catch (Exception e) {
			Log.error("Cannot update");
			throw new PersistException(e);
		} finally {
			closeResources(connection, pStatement);
		}

	}

	@Override
	public List<T> getAll() throws PersistException, ConnectionPoolException {
		List<T> list = new ArrayList<T>();
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		// Get query to get all objects
		String sql = getAllQuery();
		try {
			Log.trace("Open connection");
			// Get connections
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			// Create prepared statement
			pStatement = connection.prepareStatement(sql);
			// Get all objects
			resultSet = pStatement.executeQuery();
			list = parseResultSet(resultSet);
		}
		// Get exceptions
		catch (Exception e) {
			Log.error("Cannot get all");
			throw new PersistException(e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		return list;
	}

}
