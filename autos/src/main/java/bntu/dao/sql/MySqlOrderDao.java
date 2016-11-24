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
import main.java.bntu.entity.Order;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlOrderDao extends AbstractJDBCDao<Order> {

	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");
	public Logger Log = LogManager.getLogger(MySqlOrderDao.class.getName());

	@Override
	public String getSelectQuery() {
		return properties.getString("selectOrder");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertOrder");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateOrder");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteOrder");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("getAllOrder");
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
		List<Order> result = new ArrayList<Order>();
		try {
			while (rs.next()) {
				Order order = new Order();
				MySqlUsersDao user = new MySqlUsersDao();
				MySqlCarDao car = new MySqlCarDao();
				order.setUser(user.getUserById(rs.getInt("id_User")));
				order.setCar(car.getByPK(rs.getInt("id_Car")));
				order.setId(rs.getInt("id_Order"));
				order.setDateOrd(rs.getDate("Date"));
				result.add(order);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Order object) throws PersistException {
		try {
			statement.setInt(1, object.getCar().getId());
			statement.setInt(2, object.getUser().getId());
			statement.setString(4, object.getStatus());
			statement.setDate(3, object.getDateOrd());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Order object) throws PersistException {
		try {
			statement.setInt(1, object.getCar().getId());
			statement.setInt(2, object.getUser().getId());
			statement.setString(4, object.getStatus());
			statement.setDate(3, object.getDateOrd());

		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	protected java.sql.Date convert(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
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
	 * change order status
	 * 
	 * @param status
	 * @param id
	 */
	public void changeStatus(String status, String id) {
		Log.info("Change status in order with id : " + id);
		String query = properties.getString("changeStatus");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, status);
			pStatement.setString(2, id);
			Log.trace("Getting result set");
			pStatement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			Log.error("Cannot get order with id: " + id, e);

		} finally {
			closeResources(connection, pStatement, resultSet);
		}
	}

	/**
	 * Update master in order
	 * 
	 * @param id_Serv
	 * @param master
	 * @throws ConnectionPoolException
	 * @throws PersistException
	 */
	public void updateMasterInOrder(String id_Serv, String master)
			throws ConnectionPoolException, PersistException {
		Log.info("Update master in order with id : " + id_Serv);
		String query = properties.getString("updateMasterInOrder");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		MySqlUsersDao user = new MySqlUsersDao();
		Log.trace("Get master by id ");
		int id_master = user.getUserByEmail(master).getId();
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, id_master);
			pStatement.setString(2, id_Serv);
			Log.trace("Getting result set");
			pStatement.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			Log.error("Cannot get order with id: " + id_Serv, e);

		} finally {
			closeResources(connection, pStatement, resultSet);
		}

	}
}
