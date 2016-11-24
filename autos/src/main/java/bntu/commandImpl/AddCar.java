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
 * Add car page
 * 
 * @author Veronika
 *
 */
public class AddCar implements Command {

	public Logger Log = Logger.getLogger(AddCar.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Show add car ");
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		Users user = null;
		UserServiceImpl userSer = new UserServiceImpl();
		try {
			Log.trace("Get Username");
			user = userSer.getUser(request.getParameter("Username"));
			Log.trace("Set attributes");
			request.setAttribute("Username", user.getLogin());
			request.setAttribute("users1", userSer.getUsers());
			request.setAttribute("cars1", userSer.getCars(user));
			Log.trace("Set user add car page as nextPage");
			nextPage = properties.getString("PAGE_COMMAND_USER_ADDCAR");

		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot sign in ", e);
		}
		return nextPage;
	}

}
