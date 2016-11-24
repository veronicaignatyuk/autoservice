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
import main.java.bntu.entity.Role;

/**
 * 
 * @author Veronika
 *
 */
public class MySqlRoleDao extends AbstractJDBCDao<Role> {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/sqlrequest");
	public Logger Log = LogManager.getLogger(MySqlRoleDao.class.getName());

	@Override
	public String getSelectQuery() {
		return properties.getString("selectRole");
	}

	@Override
	public String getInsertQuery() {
		return properties.getString("insertRole");
	}

	@Override
	public String getUpdateQuery() {
		return properties.getString("updateRole");
	}

	@Override
	public String getDeleteQuery() {
		return properties.getString("deleteRole");
	}

	@Override
	public String getAllQuery() {
		return properties.getString("getAllRoles");
	}

	@Override
	protected List<Role> parseResultSet(ResultSet rs) throws PersistException {
		List<Role> result = new ArrayList<Role>();
		try {
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id_Role"));
				role.setRole(rs.getString("role"));
				result.add(role);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			Role object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setObject(2, object.getRole());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			Role object) throws PersistException {
		try {
			statement.setInt(1, object.getId());
			statement.setObject(2, object.getRole());

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
