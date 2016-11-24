package main.java.bntu.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.java.bntu.dao.abstr.AbstractJDBCDao;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.entity.Car;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlCarDao extends AbstractJDBCDao<Car> {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");
	public Logger Log = LogManager.getLogger(MySqlCarDao.class.getName());

	@Override
	public String getSelectQuery() {

		return properties.getString("selectCar");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertCar");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateCar");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteCar");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("getAllCar");
	}

	@Override
	protected List<Car> parseResultSet(ResultSet rs) throws PersistException {
		List<Car> result = new ArrayList<Car>();
		try {
			while (rs.next()) {
				Car car = new Car();
				MySqlUsersDao user = new MySqlUsersDao();
				car.setId(rs.getInt("id_Car"));
				car.setUser(user.getUserById(rs.getInt("id_User")));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				result.add(car);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Car car) throws PersistException {
		try {
			statement.setInt(4, car.getId());
			statement.setInt(1, car.getUser().getId());
			statement.setString(2, car.getBrand());
			statement.setString(3, car.getModel());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Car object) throws PersistException {
		try {

			statement.setInt(1, object.getUser().getId());
			statement.setString(2, object.getBrand());
			statement.setString(3, object.getModel());

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

	@Override
	public int getId() {
		return 0;
	}

}
