package main.java.bntu.commandImpl;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.Command;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

/**
 * Order Service page
 * 
 * @author Veronika
 *
 */
public class OrderService implements Command {

	public Logger Log = Logger.getLogger(OrderService.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Order service");
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		Users user = null;
		UserServiceImpl userSer = new UserServiceImpl();
		try {
			Log.trace("Get Username");
			user = userSer.getUser(request.getParameter("Username"));
			Log.trace("Set attributes");
			request.setAttribute("Username", user.getLogin());
			request.setAttribute("car1", userSer.getCars(user));
			request.setAttribute("service1", userSer.getService(user));
			Log.trace("Choice user page");
			nextPage = properties.getString("PAGE_COMMAND_USER_ORDER");
		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot sign in ", e);
		}
		return nextPage;
	}

}
