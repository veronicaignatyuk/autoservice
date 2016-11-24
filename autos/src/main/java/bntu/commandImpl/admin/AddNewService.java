package main.java.bntu.commandImpl.admin;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.Command;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.TechServiceImpl;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

/**
 * Add new user
 * 
 * @author Veronika
 *
 */
public class AddNewService implements Command {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	public Logger Log = Logger.getLogger(AddNewService.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ConnectionPoolException,
			PersistException {

		Log.info("Add new service type ");
		String nextPage = null;
		UserServiceImpl userSer = new UserServiceImpl();
		TechServiceImpl tech = new TechServiceImpl();
		Users user = null;
		Log.trace("Validation ");
		int id = validate(request);
		// if validation
		if (id == 0) {
			// error validation
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_ADMIN");
		} else {
			try {
				// Get attributes
				Log.trace("Get username ");
				user = userSer.getUser(request.getParameter("Username"));
				Log.trace("Add service to database ");
				tech.addService(request.getParameter("Type"),
						request.getParameter("Cost"));
				// Set attributes
				Log.trace("Set attributes");
				request.setAttribute("Username", user.getLogin());
				request.setAttribute("cars1", tech.getRepairableCars());
				request.setAttribute("service1", tech.getAllOrders());
				request.setAttribute("services", tech.getAllService());
				request.setAttribute("master1", tech.getAllMasters());
				request.setAttribute("users1", userSer.getUsers());
				Log.trace("Set nextpage");
				// Set nextpage
				nextPage = properties.getString("PAGE_COMMAND_ADMIN");
			} catch (ServiceException e) {
				// get errors
				Log.trace("error");
				Log.error("Cannot sign in ", e);
				request.setAttribute("error",
						content.getString("addservice.wrong.input"));
				nextPage = properties.getString("PAGE_COMMAND_ADMIN_ADDSERV");
			}
		}
		return nextPage;

	}

	private int validate(HttpServletRequest request) {
		Log.debug("Checking valid input");
		String type = request.getParameter("Type");
		String cost = request.getParameter("Cost");
		if (Objects.equals(type, "") || Objects.equals(cost, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("addservice.wrong.input.empty"));
		} else if (!(cost.matches("[-+]?\\d+")) || type.length() > 255
				|| cost.length() > 32767) {
			Log.debug("Input not valid. Incorrect input");
			request.setAttribute("error",
					content.getString("addservice.wrong.input.incorrect"));
		} else {
			return 1;
		}
		return 0;
	}
}
