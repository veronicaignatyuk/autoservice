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
 * Update user in database
 * 
 * @author Veronika
 *
 */
public class UpdateUsersAdmin implements Command {
	public Logger Log = Logger.getLogger(UpdateUsersAdmin.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ConnectionPoolException,
			PersistException {
		Log.info("Admin update user in DB");
		String nextPage = null;
		UserServiceImpl userSer = new UserServiceImpl();
		TechServiceImpl tech = new TechServiceImpl();
		Log.trace("Validation");
		Users user = validate(request);
		if (user == null) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_UPDATE_USER");
		} else {
			try {
				user = userSer.getUser(request.getParameter("Username"));
				Log.trace("Set Username");
				request.setAttribute("Username", user.getLogin());
				Log.trace("Update user");
				userSer.updateUser(request.getParameter("Id"),
						request.getParameter("Log"),
						request.getParameter("Pas"),
						request.getParameter("Role"));
				Log.trace("Set attributes");
				request.setAttribute("cars1", tech.getRepairableCars());
				request.setAttribute("service1", tech.getAllOrders());
				request.setAttribute("master1", tech.getAllMasters());
				request.setAttribute("users1", userSer.getUsers());
				request.setAttribute("role1", tech.getAllRole());
				request.setAttribute("services", tech.getAllService());
				nextPage = properties.getString("PAGE_COMMAND_ADMIN");
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot sign in ", e);
				request.setAttribute("error",
						content.getString("user.wrong.update"));
				nextPage = properties.getString("PAGE_COMMAND_UPDATE_USER");
			}
		}
		return nextPage;
	}

	/**
	 * Validation
	 * 
	 * @param request
	 * @return user
	 */
	private Users validate(HttpServletRequest request) {
		Users user = null;
		Log.debug("Checking valid input");
		String id = request.getParameter("Id");
		String email = request.getParameter("Log");
		String password = request.getParameter("Pas");
		String role = request.getParameter("Role");
		if (Objects.equals(id, "") || Objects.equals(password, "")
				|| Objects.equals(role, "") || Objects.equals(email, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("login.wrong.input.empty"));
		} else if (email.length() < 3 || email.length() > 15) {
			Log.debug("Input not valid. Email length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.email"));
		} else if (password.length() < 3 || password.length() > 15) {
			Log.debug("Input not valid. Password length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.password"));
		} else if (!(id.matches("[-+]?\\d+"))) {
			Log.debug("Input not valid. id is a number.");
			request.setAttribute("error",
					content.getString("update.wrong.input.number"));
		} else {
			user = new Users(Integer.parseInt(id), email, password);
		}
		return user;
	}

}
