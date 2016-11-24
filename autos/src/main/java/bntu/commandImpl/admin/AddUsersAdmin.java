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
 * Add new user by admin
 * 
 * @author Veronika
 *
 */
public class AddUsersAdmin implements Command {
	public Logger Log = Logger.getLogger(AddUsersAdmin.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ConnectionPoolException,
			PersistException {
		Log.info("Add new user ");
		String nextPage = null;
		UserServiceImpl userSer = new UserServiceImpl();
		TechServiceImpl tech = new TechServiceImpl();
		Log.trace("Validation request ");
		Users user = validate(request);
		if (user == null) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_ADD_USER");
		} else {
			try {
				Log.trace("Try add user ");
				userSer.addUser(request.getParameter("Log"),
						request.getParameter("Pas"),
						request.getParameter("Role"));
				Log.trace("Set admin attributes");
				user = userSer.getUser(request.getParameter("Username"));
				request.setAttribute("Username", user.getLogin());
				request.setAttribute("cars1", tech.getRepairableCars());
				request.setAttribute("service1", tech.getAllOrders());
				request.setAttribute("users1", userSer.getUsers());
				request.setAttribute("services", tech.getAllService());
				Log.trace("Choice admin page ");
				nextPage = properties.getString("PAGE_COMMAND_ADMIN");
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot add user ", e);
				request.setAttribute("error",
						content.getString("signup.wrong.input"));
				nextPage = properties.getString("PAGE_COMMAND_ADD_USER");
			}
		}
		return nextPage;
	}

	private Users validate(HttpServletRequest request) {
		Users user = null;
		Log.debug("Checking valid input");
		String log = request.getParameter("Log");
		String pas = request.getParameter("Pas");
		String role = request.getParameter("Role");
		if (Objects.equals(log, "") || Objects.equals(pas, "")
				|| Objects.equals(role, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("login.wrong.input.empty"));
		} else if (log.length() < 3 || log.length() > 15) {
			Log.debug("Input not valid. Email length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.email"));
		} else if (pas.length() < 3 || pas.length() > 15) {
			Log.debug("Input not valid. Password length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.password"));
		} else {
			user = new Users(log, pas);
		}
		return user;
	}
}
