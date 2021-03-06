package main.java.bntu.commandImpl;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.*;
import main.java.bntu.entity.Role;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.TechServiceImpl;
import main.java.bntu.serviceImpl.UserServiceImpl;
import org.apache.log4j.Logger;

/**
 * Sign in user
 * 
 * @author Veronika
 *
 */
public class SignIn implements Command {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");
	public Logger Log = Logger.getLogger(SignIn.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Sign in ");
		TechServiceImpl tech = new TechServiceImpl();
		UserServiceImpl userSer = new UserServiceImpl();
		Role role;
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		Log.trace("Validation request ");
		Users user = validate(request);
		if (user == null) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("LOGIN_PAGE_PATH");
		} else {
			try {
				Log.trace("Try sign in ");
				user = userSer.signInUser(request.getParameter("email"),
						request.getParameter("password"));
				if (user == null) {
					Log.trace("Error page wrong request");
					request.setAttribute("error",
							content.getString("login.wrong.input"));
					nextPage = properties.getString("LOGIN_PAGE_PATH");
				} else {
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
						request.setAttribute("service1",
								userSer.getService(user));
						Log.trace("Choice user page");
						nextPage = properties
								.getString("PAGE_COMMAND_USERPAGE");
						break;

					}
					}
				}
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot sign in ", e);
				request.setAttribute("error",
						content.getString("login.wrong.signIn"));
				nextPage = properties.getString("LOGIN_PAGE_PATH");
				return nextPage;
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
	public Users validate(HttpServletRequest request) {
		Users user = null;
		Log.debug("Checking valid input");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (Objects.equals(email, "") || Objects.equals(password, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("login.wrong.input.empty"));
		} else {
			if (email.length() > 15 || email.length() < 4) {
				Log.debug("Input not valid. Email length must be from 4 to 15");
				request.setAttribute("error",
						content.getString("login.wrong.input.email"));
			} else {
				if (password.length() > 15 || password.length() < 4) {
					Log.debug("Input not valid. Password length must be from 4 to 15");
					request.setAttribute("error",
							content.getString("login.wrong.input.password"));
				} else {
					user = new Users(email, password);
				}
			}
		}

		return user;
	}

}
