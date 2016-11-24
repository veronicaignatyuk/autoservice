package main.java.bntu.commandImpl.admin;

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
 * Show add service page
 * 
 * @author Veronika
 *
 */
public class AddService implements Command {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	public Logger Log = Logger.getLogger(AddService.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ConnectionPoolException,
			PersistException {
		Log.info("Show add service page");
		String nextPage = properties.getString("PAGE_COMMAND_ADMIN_ADDSERV");
		Users user = null;
		UserServiceImpl userSer = new UserServiceImpl();
		TechServiceImpl tech = new TechServiceImpl();
		try {
			Log.trace("Set attributes");
			user = userSer.getUser(request.getParameter("Username"));
			request.setAttribute("Username", user.getLogin());
			request.setAttribute("services", tech.getAllService());
			Log.trace("Choice admin page ");
			nextPage = properties.getString("PAGE_COMMAND_ADMIN_ADDSERV");
		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot open add_service page ", e);
		}
		return nextPage;
	}

}
