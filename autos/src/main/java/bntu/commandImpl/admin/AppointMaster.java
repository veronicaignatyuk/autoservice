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
 * Appoint master in order table
 * 
 * @author Veronika
 *
 */
public class AppointMaster implements Command {
	public Logger Log = Logger.getLogger(AppointMaster.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ConnectionPoolException,
			PersistException {
		Log.info("Appoint new master in order");
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		Users user = null;
		TechServiceImpl tech = new TechServiceImpl();
		UserServiceImpl userSer = new UserServiceImpl();
		int id = validate(request);
		if (id == 0) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_CHANGE_MASTER");
		} else {
			try {
				Log.trace("Try appoint master ");
				user = userSer.getUser(request.getParameter("Username"));
				Log.trace("Appoint master in order");
				tech.updateMaster(request.getParameter("Id_Serv"),
						request.getParameter("Master"));
				Log.trace("Set admin attributes");
				request.setAttribute("Username", user.getLogin());
				request.setAttribute("cars1", tech.getRepairableCars());
				request.setAttribute("service1", tech.getAllOrders());
				request.setAttribute("master1", tech.getAllMasters());
				request.setAttribute("users1", userSer.getUsers());
				request.setAttribute("role1", tech.getAllRole());
				request.setAttribute("services", tech.getAllService());
				Log.trace("Choice admin page ");
				nextPage = properties.getString("PAGE_COMMAND_ADMIN");
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot add user ", e);
				request.setAttribute("error",
						content.getString("appoint.wrong.master"));
				nextPage = properties.getString("PAGE_COMMAND_CHANGE_MASTER");
			}
		}
		return nextPage;
	}

	/**
	 * Request validation
	 * 
	 * @param request
	 * @return user
	 */
	public int validate(HttpServletRequest request) {
		Log.debug("Checking valid input");
		String id = request.getParameter("Id_Serv");
		String master = request.getParameter("Master");
		if (Objects.equals(id, "") || Objects.equals(master, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("login.wrong.input.empty"));
		} else {
			return 1;
		}
		return 0;
	}
}
