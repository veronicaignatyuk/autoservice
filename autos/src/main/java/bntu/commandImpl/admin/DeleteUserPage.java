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
 * Delete user
 * 
 * @author Veronika
 *
 */
public class DeleteUserPage implements Command {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	public Logger Log = Logger.getLogger(DeleteUserPage.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Log.info("Delete user by id ");
		TechServiceImpl tech = new TechServiceImpl();
		UserServiceImpl userSer = new UserServiceImpl();
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		try {
			Log.trace("Try deiete by id ");
			Users user = userSer.getUser(request.getParameter("Username"));
			Log.trace("Set Username");
			request.setAttribute("Username", user.getLogin());
			Log.trace("Set admin attributes");
			request.setAttribute("users1", userSer.getUsers());
			request.setAttribute("role1", tech.getAllRole());
			Log.trace("Choice admin page ");
			nextPage = properties.getString("PAGE_COMMAND_DELETE_USER");
			return nextPage;
		} catch (ServiceException e) {
			Log.trace("error");
			Log.error("Cannot sign in ", e);
		}
		return nextPage;
	}

}
