package main.java.bntu.serviceImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import main.java.bntu.dao.abstr.GenericDao;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.dao.sql.MySqlCarDao;
import main.java.bntu.dao.sql.MySqlOrderDao;
import main.java.bntu.dao.sql.MySqlOrderTechServDao;
import main.java.bntu.dao.sql.MySqlRoleDao;
import main.java.bntu.dao.sql.MySqlUserRoleDao;
import main.java.bntu.dao.sql.MySqlUsersDao;
import main.java.bntu.entity.Car;
import main.java.bntu.entity.Order;
import main.java.bntu.entity.OrderTable;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.UserRole;
import main.java.bntu.entity.Users;
import main.java.bntu.security.SHA1;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceInterface.IUserService;

import org.apache.log4j.Logger;

/**
 * 
 * @author Veronika
 *
 */
public class UserServiceImpl implements IUserService {
	public static Logger Log = Logger.getLogger(UserServiceImpl.class);

	private static volatile UserServiceImpl instance;
	private GenericDao<Users> userDao;
	MySqlUsersDao user;

	public static synchronized UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	public UserServiceImpl() {
	}

	@Override
	public void setUserDao(MySqlUsersDao userDao) {
		this.user = userDao;

	}

	@Override
	public void setUserGenericDao(GenericDao<Users> userGenDao) {
		this.userDao = userGenDao;
	}

	@Override
	public MySqlUsersDao getUserDao() {
		return this.user;

	}

	@Override
	public GenericDao<Users> getUserGenericDao() {
		return this.userDao;
	}

	@Override
	public Users signUpUser(Users user) throws ServiceException {
		Log.info("Sign up user in database");
		Users user1 = null;
		Role role = null;
		MySqlUsersDao userDao = new MySqlUsersDao();
		MySqlUserRoleDao usroleDao = new MySqlUserRoleDao();
		MySqlRoleDao roleDao = new MySqlRoleDao();
		Log.trace("Convert password in SHA1");
		String passwordSHA = SHA1.getHash(user.getPassword().getBytes());
		user.setPassword(passwordSHA);
		try {
			Log.trace("Create new user");
			userDao.create(user);
			Log.trace("Get user by id");
			user1 = userDao.getUserByEmail(user.getLogin());
			Log.trace("Get role 'user'");
			role = roleDao.getByPK(3);
			Log.trace("Create relation user-role");
			usroleDao.create(new UserRole(user1, role));
		} catch (IOException | ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot sign up user", e);
			throw new ServiceException("Cannot sign up user", e);
		}
		return user1;
	}

	@Override
	public Users signInUser(String login, String password)
			throws ServiceException {
		Log.info("Sign in user");
		Users signInUser = null;
		MySqlUsersDao user = new MySqlUsersDao();
		Log.trace("Convert password in SHA1");
		String passwordSHA = SHA1.getHash(password.getBytes());
		try {
			Log.trace("Get user by email and password");
			signInUser = user.getUserByEmailAndPassword(login, passwordSHA);
			return signInUser;
		} catch (PersistException | ConnectionPoolException e) {
			Log.trace("error");
			Log.error("Cannot sign in user", e);
			throw new ServiceException("Cannot sign in user", e);
		}
	}

	@Override
	public Integer updateUser(Users user) throws ServiceException {
		Log.info("Update user");
		try {
			Log.trace("Update user in dao");
			userDao.update(user);
		} catch (PersistException | ConnectionPoolException e) {
			Log.trace("error");
			Log.error("Cannot update user", e);
			throw new ServiceException("Cannot update user", e);
		}
		return user.getId();
	}

	@Override
	public Role getUserRole(Users users) throws ServiceException {
		Log.info("Update user");
		MySqlUsersDao user = new MySqlUsersDao();
		Role roles = null;
		try {
			Log.trace("Getting user's role");
			roles = user.getUserRoles(users);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get role for user with id : " + users.getId(), e);
			throw new ServiceException("Cannot get role for user with id : "
					+ users.getId(), e);
		}
		return roles;
	}

	@Override
	public List<Car> getCars(Users users) throws ServiceException {
		Log.info("Getting list of user's cars");
		MySqlUsersDao user = new MySqlUsersDao();
		List<Car> cars = null;
		try {
			Log.trace("Get user's cars");
			cars = user.getCarL(users);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error(
					"Cannot get list of cars for user with id : "
							+ users.getId(), e);
			throw new ServiceException(
					"Cannot get list of cars for user with id : "
							+ users.getId(), e);
		}
		return cars;
	}

	@Override
	public List<OrderTable> getService(Users users) throws ServiceException {
		Log.info("Get user's technical services");
		MySqlUsersDao user = new MySqlUsersDao();
		List<OrderTable> techServ = null;
		try {
			Log.trace("Get user's technical services ");
			techServ = user.getServL(users);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error(
					"Cannot get list of technical services for user with id : "
							+ users.getId(), e);
			throw new ServiceException(
					"Cannot get list of technical services for user with id : "
							+ users.getId(), e);
		}
		return techServ;
	}

	@Override
	public List<Users> getUsers() throws ServiceException {
		Log.info("Get list of all users");
		MySqlUsersDao users = new MySqlUsersDao();
		List<Users> userL = null;
		try {
			Log.trace("Get all users ");
			userL = users.getAll();
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get list of users  ", e);
			throw new ServiceException("Cannot get list of users ", e);
		}
		return userL;
	}

	@Override
	public Users getUser(String email) throws ServiceException {
		Log.info("Getting user by email");
		MySqlUsersDao users = new MySqlUsersDao();
		Users user = null;
		try {
			Log.trace("Get user by email");
			user = users.getUserByEmail(email);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get user with email " + email, e);
			throw new ServiceException("Cannot get user with email " + email, e);
		}
		return user;
	}

	@Override
	public void addUser(String login, String password, String role)
			throws ServiceException {
		Log.info("Create new user by login, password and role");
		Users user = new Users(login, password);
		Users user1 = null;
		Role role1;
		MySqlUsersDao userDao = new MySqlUsersDao();
		MySqlUserRoleDao usroleDao = new MySqlUserRoleDao();
		MySqlRoleDao roleDao = new MySqlRoleDao();
		Log.trace("Convert password in SHA1");
		String passwordSHA = SHA1.getHash(user.getPassword().getBytes());
		user.setPassword(passwordSHA);
		try {
			Log.trace("Create new user");
			 userDao.create(user);
			Log.trace("Get new user's id");
			user1 = userDao.getUserByEmail(login);
			Log.trace("Get role for new user by id");
			role1 = roleDao.getByPK(Integer.parseInt(role));
			Log.trace("Create role for user");
			usroleDao.create(new UserRole(user1, role1));
		} catch (ConnectionPoolException | PersistException | IOException e) {
			Log.trace("error");
			Log.error("Cannot get user with login : " + login, e);
			throw new ServiceException("Cannot get user with login : " + login,
					e);
		}

	}

	@Override
	public void updateUser(String id, String login, String password, String role)
			throws ServiceException {
		Log.info("Update full user ");
		Role role1;
		Users user = new Users(Integer.parseInt(id), login, password);
		MySqlUsersDao userDao = new MySqlUsersDao();
		MySqlUserRoleDao usroleDao = new MySqlUserRoleDao();
		MySqlRoleDao roleDao = new MySqlRoleDao();
		Log.trace("Convert password in SHA1");
		String passwordSHA = SHA1.getHash(user.getPassword().getBytes());
		user.setPassword(passwordSHA);
		try {
			Log.trace("Update user");
			userDao.update(user);
			Log.trace("Get role by id");
			role1 = roleDao.getByPK(Integer.parseInt(role));
			Log.trace("Update user-role");
			usroleDao.update(new UserRole(user, role1));
		} catch (ConnectionPoolException | PersistException | IOException e) {
			Log.trace("error");
			Log.error("Cannot update with id : " + id, e);
			throw new ServiceException("Cannot update with id : " + id, e);
		}
	}

	@Override
	public void deleteUser(String id) throws ServiceException {
		Log.info("Delete user with id : " + id);
		MySqlUsersDao userDao = new MySqlUsersDao();
		MySqlUserRoleDao usroleDao = new MySqlUserRoleDao();
		try {
			Log.trace("Delete user with id : " + id);
			usroleDao.delete(Integer.parseInt(id));
			userDao.delete(Integer.parseInt(id));

		} catch (NumberFormatException | ConnectionPoolException
				| PersistException e) {
			Log.trace("error");
			Log.error("Cannot delete user with id : " + id, e);
			throw new ServiceException("Cannot delete user with id : " + id, e);
		}

	}

	@Override
	public void newCar(Users user, String brand, String model)
			throws ServiceException {
		Log.info("Add new car to user");
		Car car = new Car(user, brand, model);
		MySqlCarDao cars = new MySqlCarDao();
		try {
			Log.trace("Create new car");
			cars.create(car);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot add new car ", e);
			throw new ServiceException("Cannot add new car ", e);
		}
	}

	@Override
	public List<OrderTable> getOrdersByMaster(Users users)
			throws ServiceException {
		Log.info("Getting list of orders by masters");
		MySqlUsersDao user = new MySqlUsersDao();
		List<OrderTable> orderTable = null;
		try {
			Log.trace("Get order list");
			orderTable = user.getOrderServL(users);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get order list", e);
			throw new ServiceException("Cannot get order list", e);
		}
		return orderTable;
	}

	@Override
	public void orderService(Users user, String idCar, String idService,
			String date) throws ServiceException {
		Log.info("Setting order");
		MySqlCarDao car = new MySqlCarDao();
		MySqlOrderDao orderDao = new MySqlOrderDao();
		MySqlOrderTechServDao OrderTechService = new MySqlOrderTechServDao();
		Order order = null;
		int orderPk;
		try {
			Log.trace("Set order entity");
			order = new Order(user, car.getByPK(Integer.parseInt(idCar)),
					Date.valueOf(date), "queue", null);
			Log.trace("Create order");
			orderPk = orderDao.create(order);
			Log.trace("Create order-service");
			OrderTechService.createByPk(orderPk, idService);
		} catch (ConnectionPoolException | PersistException
				| NumberFormatException | IOException e) {
			Log.trace("error");
			Log.error("Cannot set order ", e);
			throw new ServiceException("Cannot set order ", e);
		}
	}
}
