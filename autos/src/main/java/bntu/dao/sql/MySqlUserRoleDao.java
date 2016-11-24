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
import main.java.bntu.entity.UserRole;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlUserRoleDao extends AbstractJDBCDao<UserRole> {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");
	public Logger Log = LogManager.getLogger(MySqlUserRoleDao.class
			.getName());
	@Override
	public String getSelectQuery() {
		return properties.getString("selectUserRole");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertUserRole");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateUserRole");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteUserRole");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("getListUserRole");
	}

	@Override
	protected List<UserRole> parseResultSet(ResultSet rs)
			throws PersistException {
		List<UserRole> result = new ArrayList<UserRole>();
		try {
			while (rs.next()) {
				UserRole user_Role = new UserRole();
				MySqlUsersDao user = new MySqlUsersDao();
				MySqlRoleDao role = new MySqlRoleDao();
				user_Role.setUser(user.getUserById(rs.getInt("id_User")));
				user_Role.setRole(role.getByPK(rs.getInt("id_Role")));
				result.add(user_Role);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			UserRole object) throws PersistException {
		try {
			statement.setInt(2, object.getUser().getId());
			statement.setInt(1, object.getRole().getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			UserRole object) throws PersistException {
		try {
			statement.setInt(1, object.getUser().getId());
			statement.setInt(2, object.getRole().getId());

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

}
