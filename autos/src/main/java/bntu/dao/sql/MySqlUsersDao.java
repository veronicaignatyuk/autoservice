package main.java.bntu.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.java.bntu.connection.DbConnection;
import main.java.bntu.connection.PoolConnection;
import main.java.bntu.dao.abstr.AbstractJDBCDao;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.entity.Car;
import main.java.bntu.entity.OrderTable;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.Users;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlUsersDao extends AbstractJDBCDao<Users> {

	public Logger Log = LogManager.getLogger(MySqlUsersDao.class
			.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");

	@Override
	public String getSelectQuery() {
		return properties.getString("selectUser");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertUser");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateUser");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteUser");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("selectUsers");
	}

	@Override
	protected List<Users> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<Users> result = new LinkedList<Users>();
		try {
			while (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("id_Users"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				result.add(user);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Users object) throws PersistException {
		try {
			statement.setInt(3, object.getId());
			statement.setString(1, object.getLogin());
			statement.setString(2, object.getPassword());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Users object) throws PersistException {
		try {
			statement.setString(1, object.getLogin());
			statement.setString(2, object.getPassword());

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
	 * Get user by name and password
	 * 
	 * @param email
	 * @param password
	 * @return User
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public Users getUserByEmailAndPassword(String email, String password)
			throws PersistException, ConnectionPoolException {
		Log.info("Getting User with email: " + email + " password: " + password);
		Users user = null;
		String query = request.getString("selectUserByEm&Pas");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, email);
			pStatement.setString(2, password);
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				user = new Users(resultSet.getInt("id_Users"),
						resultSet.getString("login"),
						resultSet.getString("password"));
			}
		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot get User with email: " + email
					+ " and password : " + password, e);
			throw new PersistException("Cannot get User with email: " + email
					+ " and password : " + password, e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		Log.trace("Returning User: " + user);
		Log.info("Returning User: " + user);
		return user;
	}

	/**
	 * get user's role
	 * 
	 * @param users
	 * @return list of roles
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public Role getUserRoles(Users users) throws PersistException,
			ConnectionPoolException {
		Log.info("Getting user's role with id: " + users.getId());
		String query = request.getString("getUserRole");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, users.getId());
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();
			Role role = null;
			while (resultSet.next()) {
				role = new Role(resultSet.getInt(1), resultSet.getString(2));
			}
			return role;
		} catch (SQLException e) {
			Log.error("Cannot get roles user with id: " + users.getId(), e);
			throw new PersistException("Cannot get roles user with id: "
					+ users.getId(), e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}

	}

	/**
	 * Get user's cars
	 * 
	 * @param users
	 * @return list of user's cars
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<Car> getCarL(Users users) throws PersistException,
			ConnectionPoolException {
		Log.info("Getting user's cars with user's id: " + users.getId());
		List<Car> cars = new ArrayList<Car>();
		String query = properties.getString("getUserCar");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, users.getId());
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				Car car = new Car(resultSet.getInt(1), users,
						resultSet.getString(3), resultSet.getString(4));
				cars.add(car);
			}
			return cars;
		} catch (SQLException e) {
			Log.error("Cannot get cars user with id: " + users.getId(), e);
			throw new PersistException("Cannot get cars user with id: "
					+ users.getId(), e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}

	}

	/**
	 * Get list of repairable cars
	 * 
	 * @return list of cars
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<Car> getRepairableCars() throws PersistException,
			ConnectionPoolException {
		Log.info("Getting repairable cars");
		List<Car> cars = new ArrayList<Car>();
		String query = properties.getString("getRepairableCars");
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
				Car car = new Car(resultSet.getInt(1),
						getUserById(resultSet.getInt(2)),
						resultSet.getString(3), resultSet.getString(4));
				cars.add(car);
			}
			return cars;
		} catch (SQLException e) {
			Log.error("Cannot get cars ", e);
			throw new PersistException("Cannot get cars", e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}

	}

	/**
	 * Get user's list of services
	 * 
	 * @param users
	 * @return list of services
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<OrderTable> getServL(Users users) throws PersistException,
			ConnectionPoolException {
		Log.info("Getting user's list of services with id" + users.getId());
		List<OrderTable> tServ = new ArrayList<OrderTable>();
		String query = properties.getString("getUserServ");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, users.getId());
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				OrderTable tService = new OrderTable(resultSet.getInt("id_Order"),
						resultSet.getString("login"),
						resultSet.getString("brand"),
						resultSet.getString("model"), users.getLogin(),
						resultSet.getDate("Date"),
						resultSet.getString("status"),
						resultSet.getString("title"), resultSet.getInt("cost"));
				tServ.add(tService);

			}
			return tServ;
		} catch (SQLException e) {
			Log.error(
					"Cannot get user's list of services with id: "
							+ users.getId(), e);
			throw new PersistException(
					"Cannot get user's list of services with id: "
							+ users.getId(), e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
	}

	/**
	 * Get user by email
	 * 
	 * @param email
	 * @return user
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public Users getUserByEmail(String email) throws PersistException,
			ConnectionPoolException {
		Log.info("Getting User with email: " + email);
		Users user = null;
		String query = properties.getString("selectUserByEm");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, email);
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				user = new Users(resultSet.getInt("id_Users"),
						resultSet.getString("login"),
						resultSet.getString("password"));
			}
		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot get User with email: " + email, e);
			throw new PersistException("Cannot get User with email: " + email,
					e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		Log.trace("Returning User: " + user);
		Log.info("Returning User: " + user);
		return user;
	}

	/**
	 * Get user by id
	 * 
	 * @param id
	 * @return user
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public Users getUserById(int id) throws PersistException,
			ConnectionPoolException {
		Log.info("Getting User with id: " + id);
		Users user = null;
		String query = getSelectQuery();
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, id);
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				user = new Users(resultSet.getInt("id_Users"),
						resultSet.getString("login"),
						resultSet.getString("password"));
			}
		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot get User with id: " + id, e);
			throw new PersistException("Cannot get User with id: " + id, e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		Log.trace("Returning User: " + user);
		Log.info("Returning User: " + user);
		return user;
	}

	/**
	 * get services by master
	 * 
	 * @param users
	 * @return list of orders
	 * @throws ConnectionPoolException
	 * @throws PersistException
	 */
	public List<OrderTable> getOrderServL(Users users)
			throws ConnectionPoolException, PersistException {
		Log.info("Getting master's orders");
		List<OrderTable> orderT = new ArrayList<OrderTable>();
		String query = properties.getString("getUserServByMaster");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, users.getId());
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				OrderTable ord = new OrderTable(resultSet.getInt("id_Order"),
						resultSet.getString("login"),
						resultSet.getString("brand"),
						resultSet.getString("model"), users.getLogin(),
						resultSet.getDate("Date"),
						resultSet.getString("status"),
						resultSet.getString("title"), resultSet.getInt("cost"));
				orderT.add(ord);
			}
			return orderT;
		} catch (SQLException e) {
			Log.error("Cannot get orders by master id: " + users.getId(), e);
			throw new PersistException("Cannot get order's by master id: "
					+ users.getId(), e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
	}

	/**
	 * get all orders
	 * 
	 * @return list of orders
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<OrderTable> getOrderL() throws PersistException,
			ConnectionPoolException {
		Log.info("Getting list of orders");
		List<OrderTable> orderT = new ArrayList<OrderTable>();
		String query = properties.getString("getOrders");
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
				OrderTable ord = new OrderTable(resultSet.getInt("id_Order"),
						resultSet.getString("login"),
						resultSet.getString("brand"),
						resultSet.getString("model"), getUserById(
								resultSet.getInt("id_master")).getLogin(),
						resultSet.getDate("Date"),
						resultSet.getString("status"),
						resultSet.getString("title"), resultSet.getInt("cost"));
				orderT.add(ord);
			}
			return orderT;
		} catch (SQLException e) {
			Log.error("Cannot get orders ", e);
			throw new PersistException("Cannot get orders", e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
	}

	/**
	 * get all masters
	 * 
	 * @return list of masters
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<Users> getMasterL() throws PersistException,
			ConnectionPoolException {
		Log.info("Getting all masters ");
		Users user = null;
		List<Users> users = new ArrayList<Users>();
		String query = properties.getString("getAllMasters");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, 2);
			Log.trace("Getting result set");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				user = new Users(resultSet.getInt("id_Users"),
						resultSet.getString("login"),
						resultSet.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot get masters", e);
			throw new PersistException("Cannot get masters ", e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		Log.trace("Returning User: " + user);
		Log.info("Returning User: " + user);
		return users;
	}

	/**
	 * get all roles
	 * 
	 * @return list of roles
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public List<Role> getRoleL() throws PersistException,
			ConnectionPoolException {
		Log.info("Getting all roles ");
		Role role = null;
		List<Role> roles = new ArrayList<Role>();
		String query = properties.getString("selectAllRoles");
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
				role = new Role(resultSet.getInt("id_Role"),
						resultSet.getString("role"));
				roles.add(role);
			}
		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot get roles", e);
			throw new PersistException("Cannot get roles ", e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}
		Log.trace("Returning User: " + role);
		Log.info("Returning User: " + role);
		return roles;
	}

}
