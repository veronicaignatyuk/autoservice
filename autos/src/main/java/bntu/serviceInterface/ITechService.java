package main.java.bntu.serviceInterface;

import java.util.List;

import main.java.bntu.entity.Car;
import main.java.bntu.entity.OrderTable;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.TechService;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;

/**
 * Methods for do same operations with database
 * 
 * @author Veronika
 *
 */
public interface ITechService {
	/**
	 * get all cars
	 * 
	 * @return list of cars
	 * @throws ServiceException
	 */
	public List<Car> getRepairableCars() throws ServiceException;

	/**
	 * change status in order by id
	 * 
	 * @param id
	 * @param Status
	 * @throws ServiceException
	 */
	public void changeOrderStatus(String id, String Status);

	/**
	 * get all orders in table
	 * 
	 * @return list of order tables
	 * @throws ServiceException
	 */
	public List<OrderTable> getAllOrders() throws ServiceException;

	/**
	 * get list of masters
	 * 
	 * @return list of users
	 * @throws ServiceException
	 */
	public List<Users> getAllMasters() throws ServiceException;

	/**
	 * update master in order with id
	 * 
	 * @param id_Order
	 * @param master
	 * @throws ServiceException
	 */
	public void updateMaster(String id_Order, String master)
			throws ServiceException;

	/**
	 * get roles
	 * 
	 * @return list of roles
	 * @throws ServiceException
	 */
	public List<Role> getAllRole() throws ServiceException;

	/**
	 * add new type of service
	 * 
	 * @param type
	 * @param cost
	 * @throws ServiceException
	 */
	public void addService(String type, String cost) throws ServiceException;

	/**
	 * get all services
	 * 
	 * @return list of services
	 * @throws ServiceException
	 */
	public List<TechService> getAllService() throws ServiceException;

}