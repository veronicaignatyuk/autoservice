package main.java.bntu.commandImpl;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.Command;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.TechServiceImpl;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

public class Main implements Command {

	public Logger Log = Logger.getLogger(Main.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ConnectionPoolException,
			PersistException {
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		Users user = null;
		Role role = null;
		UserServiceImpl userSer = new UserServiceImpl();
		TechServiceImpl tech = new TechServiceImpl();
		try {
			user = userSer.getUser(request.getParameter("Username"));
			Log.trace("Set Username");
			request.setAttribute("Username", user.getLogin());
			Log.trace("Get user's role");
			role = userSer.getUserRole(user);
			int r = role.getId();
			Log.trace("Choice userpage");
			switch (r) {
			case 1: {
				Log.trace("Set admin attributes");
				request.setAttribute("cars1", tech.getRepairableCars());
				request.setAttribute("service1", tech.getAllOrders());
				request.setAttribute("users1", userSer.getUsers());
				request.setAttribute("services", tech.getAllService());
				Log.trace("Choice admin page ");
				nextPage = properties.getString("PAGE_COMMAND_ADMIN");
				break;
			}
			case 2: {
				Log.trace("Set master attributes");
				request.setAttribute("service1",
						userSer.getOrdersByMaster(user));
				Log.trace("Choice master page");
				nextPage = properties.getString("PAGE_COMMAND_MASTER");
				break;
			}
			case 3: {
				Log.trace("Set user attributes");
				request.setAttribute("cars1", userSer.getCars(user));
				request.setAttribute("service1", userSer.getService(user));
				Log.trace("Choice user page");
				nextPage = properties.getString("PAGE_COMMAND_USERPAGE");
				break;
			}
			}
		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot sign in ", e);
		}
		return nextPage;
	}

}
