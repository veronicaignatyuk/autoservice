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
import main.java.bntu.entity.OrderTechServ;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlOrderTechServDao extends
		AbstractJDBCDao<OrderTechServ> {

	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");
	public Logger Log = LogManager.getLogger(MySqlOrderTechServDao.class
			.getName());

	@Override
	public String getSelectQuery() {
		return properties.getString("selectOrderTechServ");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertOrderTechServ");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateOrderTechServ");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteOrderTechServ");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("getAllOrderTechServ");
	}

	@Override
	protected List<OrderTechServ> parseResultSet(ResultSet rs)
			throws PersistException {
		List<OrderTechServ> result = new ArrayList<OrderTechServ>();
		try {
			while (rs.next()) {
				OrderTechServ orderOperations = new OrderTechServ();
				MySqlOrderDao order = new MySqlOrderDao();
				MySqlTechServiceDao techServ = new MySqlTechServiceDao();
				orderOperations.setId(rs.getInt("id_Order_Service"));
				orderOperations.setOrder(order.getByPK(rs.getInt("id_order")));
				orderOperations.setOperations(techServ.getByPK(rs
						.getInt("id_techService")));
				result.add(orderOperations);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			OrderTechServ orderOperations) throws PersistException {
		try {
			statement.setInt(1, orderOperations.getId());
			statement.setObject(2, orderOperations.getOrder().getId());
			statement.setObject(3, orderOperations.getOperations().getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			OrderTechServ orderOperations) throws PersistException {
		try {

			statement.setInt(1, orderOperations.getId());
			statement.setObject(2, orderOperations.getOrder().getId());
			statement.setObject(3, orderOperations.getOperations().getId());
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
	 * Create new relation order-service
	 * 
	 * @param orderPk
	 * @param idService
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */
	public void createByPk(int orderPk, String idService)
			throws PersistException, ConnectionPoolException {
		Log.info("Create relation order-service with id order : " + orderPk
				+ " and id service : " + idService);
		String query = properties.getString("InsertOrderTechServ");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			Log.trace("Open connection");
			connection = PoolConnection.INSTANCE.getConnection();
			Log.trace("Create prepared statement");
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, orderPk);
			pStatement.setInt(2, Integer.parseInt(idService));
			Log.trace("Create execute");
			pStatement.executeUpdate();

		} catch (SQLException e) {
			Log.trace("error");
			Log.error("Cannot create order-servicewith id order : " + orderPk
					+ " and id service : " + idService, e);
			throw new PersistException(
					"Cannot create order-service with id order : " + orderPk
							+ " and id service : " + idService, e);
		} finally {
			closeResources(connection, pStatement, resultSet);
		}

	}

}
