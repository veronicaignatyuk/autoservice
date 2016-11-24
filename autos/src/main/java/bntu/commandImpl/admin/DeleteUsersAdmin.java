package main.java.bntu.commandImpl.admin;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.Command;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.TechServiceImpl;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

/**
 * Delete user in DB
 * 
 * @author Veronika
 *
 */
public class DeleteUsersAdmin implements Command {
	public Logger Log = Logger.getLogger(DeleteUsersAdmin.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Admin delete user in DB");
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		UserServiceImpl userSer = new UserServiceImpl();
		TechServiceImpl tech = new TechServiceImpl();
		Log.trace("Validation");
		Users user = validate(request);
		if (user == null) {
			Log.trace("Error");
			properties.getString("ERROR_PAGE_PATH");
		}
		try {
			user = userSer.getUser(request.getParameter("Username"));
			Log.trace("Set Username");
			request.setAttribute("Username", user.getLogin());
			Log.trace("Delete user");
			userSer.deleteUser(request.getParameter("Id"));
			Log.trace("Set attributes");
			request.setAttribute("cars1", tech.getRepairableCars());
			request.setAttribute("service1", tech.getAllOrders());
			request.setAttribute("master1", tech.getAllMasters());
			request.setAttribute("users1", userSer.getUsers());
			request.setAttribute("role1", tech.getAllRole());
			request.setAttribute("services", tech.getAllService());
			nextPage = properties.getString("PAGE_COMMAND_ADMIN");
			return nextPage;
		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot sign in ", e);
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
		if (Objects.equals(id, "") || !(id.matches("[-+]?\\d+"))) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("delete.wrong.input.number"));
		} else {
			user = new Users(Integer.parseInt(id), null, null);
		}
		return user;
	}

}
