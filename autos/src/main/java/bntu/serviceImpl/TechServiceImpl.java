package main.java.bntu.serviceImpl;

import java.util.List;

import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.dao.sql.MySqlOrderDao;
import main.java.bntu.dao.sql.MySqlTechServiceDao;
import main.java.bntu.dao.sql.MySqlUsersDao;
import main.java.bntu.entity.Car;
import main.java.bntu.entity.OrderTable;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.TechService;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceInterface.ITechService;

import org.apache.log4j.Logger;

/**
 * 
 * @author Veronika
 *
 */
public class TechServiceImpl implements ITechService {
	public static Logger Log = Logger.getLogger(TechServiceImpl.class);

	private static volatile TechServiceImpl instance;
	MySqlUsersDao user;

	public static synchronized TechServiceImpl getInstance() {
		if (instance == null) {
			instance = new TechServiceImpl();
		}
		return instance;
	}

	public TechServiceImpl() {
	}

	@Override
	public List<Car> getRepairableCars() throws ServiceException {
		Log.info("Getting list of repairable cars");
		MySqlUsersDao user = new MySqlUsersDao();
		List<Car> cars = null;
		try {
			Log.trace("Get list of repairable cars in DB");
			cars = user.getRepairableCars();
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get list of cars ", e);
			throw new ServiceException("Cannot get list of cars", e);
		}
		return cars;
	}

	@Override
	public void changeOrderStatus(String id, String Status) {
		Log.info("Changing status of order");
		MySqlOrderDao order = new MySqlOrderDao();
		Log.trace("Change status of order by id");
		order.changeStatus(Status, id);
	}

	@Override
	public List<OrderTable> getAllOrders() throws ServiceException {
		Log.info("Getting list of orders in big table");
		MySqlUsersDao user = new MySqlUsersDao();
		List<OrderTable> orderTable = null;
		try {
			Log.trace("Get list of  orders");
			orderTable = user.getOrderL();
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get list of orders ", e);
			throw new ServiceException("Cannot get list of orders", e);
		}
		return orderTable;
	}

	@Override
	public List<Users> getAllMasters() throws ServiceException {
		Log.info("Getting list of users with role master");
		MySqlUsersDao user = new MySqlUsersDao();
		List<Users> users = null;
		try {
			Log.trace("Get master's list");
			users = user.getMasterL();
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get list of masters", e);
			throw new ServiceException("Cannot get list of masters", e);
		}
		return users;
	}

	@Override
	public void updateMaster(String id_Order, String master)
			throws ServiceException {
		Log.info("Update master in order with id : " + id_Order);
		MySqlOrderDao order = new MySqlOrderDao();
		try {
			Log.trace("Update master");
			order.updateMasterInOrder(id_Order, master);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot update master", e);
			throw new ServiceException("Cannot update master", e);
		}
	}

	@Override
	public List<Role> getAllRole() throws ServiceException {
		Log.info("Getting all roles");
		MySqlUsersDao user = new MySqlUsersDao();
		List<Role> roles = null;
		try {
			Log.trace("Get list of all roles");
			roles = user.getRoleL();
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot get all roles", e);
			throw new ServiceException("Cannot get all roles", e);
		}
		return roles;
	}

	@Override
	public void addService(String type, String cost) throws ServiceException {
		Log.info("Adding new service");
		TechService techService = new TechService(type, Integer.parseInt(cost));
		MySqlTechServiceDao serviceDao = new MySqlTechServiceDao();
		try {
			Log.trace("Add new service");
			serviceDao.create(techService);
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot add service", e);
			throw new ServiceException("Cannot add service", e);
		}
	}

	@Override
	public List<TechService> getAllService() throws ServiceException {
		Log.info("Getting all services");
		MySqlTechServiceDao service = new MySqlTechServiceDao();
		List<TechService> tService = null;
		try {
			Log.trace("Get list of all services");
			tService = service.getAllService();
		} catch (ConnectionPoolException | PersistException e) {
			Log.trace("error");
			Log.error("Cannot  get all services ", e);
			throw new ServiceException("Cannot get all services", e);
		}
		return tService;
	}

}
