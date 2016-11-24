package test.main.java.bntu.commandImpl;

import java.util.ResourceBundle;

import org.junit.Test;

import main.java.bntu.connection.DbConnection;
import main.java.bntu.connection.PoolConnection;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.sql.MySqlUsersDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestClose {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Test
	public void testCloseACEG() {

		MySqlUsersDao nm = new MySqlUsersDao();
		nm.closeResources(null, null, null);

	}

	@Test
	public void testCloseABCEG() {
		String query = properties.getString("getUserCar");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			connection = PoolConnection.INSTANCE.getConnection();
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, 1);
			resultSet = pStatement.executeQuery();
		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
		}
		MySqlUsersDao nm = new MySqlUsersDao();
		nm.closeResources(null, null, resultSet);

	}

	@Test
	public void testCloseABCDEG() {
				String query = properties.getString("getUserCar");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			connection = PoolConnection.INSTANCE.getConnection();
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, 1);
			resultSet = pStatement.executeQuery();
		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
		}
		MySqlUsersDao nm = new MySqlUsersDao();
		nm.closeResources( connection, null, resultSet);
		
	}

	@Test
	public void testCloseABCDEFG() {
		String query = properties.getString("getUserCar");
		DbConnection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			connection = PoolConnection.INSTANCE.getConnection();
			pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, 1);
			resultSet = pStatement.executeQuery();
		} catch (SQLException | ConnectionPoolException e) {
			e.printStackTrace();
		}
		MySqlUsersDao nm = new MySqlUsersDao();
		nm.closeResources( connection, pStatement, resultSet);
	}
}
