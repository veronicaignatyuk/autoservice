package main.java.bntu.commandImpl.admin;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.*;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.TechServiceImpl;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

/**
 * Update user page
 * 
 * @author Veronika
 *
 */
public class UpdateUserPage implements Command {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	public Logger Log = Logger.getLogger(UpdateUserPage.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Log.info("Show userPage");
		TechServiceImpl tech = new TechServiceImpl();
		UserServiceImpl userSer = new UserServiceImpl();
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		try {
			Log.trace("Try show userPage ");
			Users user = userSer.getUser(request.getParameter("Username"));
			Log.trace("Set Username");
			request.setAttribute("Username", user.getLogin());
			Log.trace("Set admin attributes");
			request.setAttribute("users1", userSer.getUsers());
			request.setAttribute("role1", tech.getAllRole());
			Log.trace("Choice admin page ");
			nextPage = properties.getString("PAGE_COMMAND_UPDATE_USER");
			return nextPage;
		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot show page ", e);
		}
		return nextPage;
	}

}
