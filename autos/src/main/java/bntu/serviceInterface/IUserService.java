package main.java.bntu.serviceInterface;

import java.util.List;

import main.java.bntu.dao.abstr.GenericDao;
import main.java.bntu.dao.sql.MySqlUsersDao;
import main.java.bntu.entity.Car;
import main.java.bntu.entity.OrderTable;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;

/**
 * Methods for do same operations with user
 * 
 * @author Veronika
 *
 */
public interface IUserService {
	/**
	 * registration new user in database
	 * 
	 * @param user
	 * @return user
	 * @throws ServiceException
	 */
	public Users signUpUser(Users user) throws ServiceException;

	/**
	 * sign in user page
	 * 
	 * @param login
	 * @param password
	 * @return user
	 * @throws ServiceException
	 */
	public Users signInUser(String login, String password)
			throws ServiceException;

	/**
	 * update fields in entity user
	 * 
	 * @param user
	 * @return id
	 * @throws ServiceException
	 */
	public Integer updateUser(Users user) throws ServiceException;

	/**
	 * get user's role
	 * 
	 * @param users
	 * @return role
	 * @throws ServiceException
	 */
	public Role getUserRole(Users users) throws ServiceException;

	/**
	 * @param userDao
	 */
	public void setUserDao(MySqlUsersDao userDao);

	public void setUserGenericDao(GenericDao<Users> userGenDao);

	/**
	 * get UserDao
	 * 
	 * @return MySqlUsersDao
	 */
	public MySqlUsersDao getUserDao();

	public GenericDao<Users> getUserGenericDao();

	/**
	 * get all user cars
	 * 
	 * @param users
	 * @return list of cars
	 * @throws ServiceException
	 */
	public List<Car> getCars(Users users) throws ServiceException;

	/**
	 * get all services was added by user
	 * 
	 * @param users
	 * @return list of technical service
	 * @throws ServiceException
	 */
	public List<OrderTable> getService(Users users) throws ServiceException;

	/**
	 * get all users
	 * 
	 * @return list of users
	 * @throws ServiceException
	 */
	public List<Users> getUsers() throws ServiceException;

	/**
	 * get user by email
	 * 
	 * @param parameter
	 * @return user
	 * @throws ServiceException
	 */
	public Users getUser(String email) throws ServiceException;

	/**
	 * add new user by login, password and role
	 * 
	 * @param login
	 * @param password
	 * @param role
	 * @throws ServiceException
	 */
	public void addUser(String login, String password, String role)
			throws ServiceException;

	/**
	 * update user in database
	 * 
	 * @param id
	 * @param login
	 * @param password
	 * @param role
	 * @throws ServiceException
	 */
	public void updateUser(String id, String login, String password, String role)
			throws ServiceException;

	/**
	 * delete user by id
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteUser(String id) throws ServiceException;

	/**
	 * add new user's car by model and brand
	 * 
	 * @param user
	 * @param brand
	 * @param model
	 * @throws ServiceException
	 */
	public void newCar(Users user, String brand, String model)
			throws ServiceException;

	/**
	 * get list of orders for jsp table
	 * 
	 * @param users
	 * @return list of orders for jsp table
	 * @throws ServiceException
	 */
	public List<OrderTable> getOrdersByMaster(Users users)
			throws ServiceException;

	/**
	 * order service for user car
	 * 
	 * @param user
	 * @param car
	 * @param service
	 * @param date
	 * @throws ServiceException
	 */
	public void orderService(Users user, String car, String service, String date)
			throws ServiceException;

}