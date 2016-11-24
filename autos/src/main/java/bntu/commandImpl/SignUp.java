package main.java.bntu.commandImpl;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.Command;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

/**
 * Sign Up
 * 
 * @author Veronika
 *
 */
public class SignUp implements Command {

	public Logger Log = Logger.getLogger(SignUp.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Sign up ");
		UserServiceImpl userSer = new UserServiceImpl();
		Users user = null;
		String nextPage = properties.getString("LOGIN_PAGE_PATH");
		Log.trace("Check password");
		Log.trace("Validation");
		user = validate(request);
		if (user == null) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("LOGIN_PAGE_PATH");
		} else {
			try {
				Log.trace("Try to sign up");
				user = userSer.signUpUser(new Users(request
						.getParameter("email1"), request
						.getParameter("passwordF")));
				if (user == null) {
					Log.trace("Error page wrong request");
					request.setAttribute("error",
							content.getString("signup.wrong.input"));
					nextPage = properties.getString("LOGIN_PAGE_PATH");
				} else {
					Log.trace("Set username");
					request.setAttribute("Username", user.getLogin());

					Log.trace("Set attributes and nextpage");
					request.setAttribute("cars1", userSer.getCars(user));
					request.setAttribute("service1", userSer.getService(user));
					nextPage = properties.getString("PAGE_COMMAND_USERPAGE");
				}
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot sign up ", e);
				request.setAttribute("error",
						content.getString("login.wrong.signUp"));
				nextPage = properties.getString("LOGIN_PAGE_PATH");
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
		String email = request.getParameter("email1");
		String password = request.getParameter("passwordF");
		if (Objects.equals(email, "") || Objects.equals(password, "")
				|| Objects.equals(request.getParameter("passwordF"), "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("login.wrong.input.empty"));
		} else if (email.length() > 15 || email.length() < 4) {
			Log.debug("Input not valid. Email length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.email"));
		} else if (password.length() > 15 || password.length() < 4) {
			Log.debug("Input not valid. Password length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.password"));
		} else if (request.getParameter("passwordS").length() > 15
				|| request.getParameter("passwordS").length() < 4) {
			Log.debug("Input not valid. Password length must be from 4 to 15");
			request.setAttribute("error",
					content.getString("login.wrong.input.passwordS"));
		} else if (!Objects.equals(request.getParameter("passwordF"),
				request.getParameter("passwordS"))) {
			Log.debug("Input not valid. Password are not equals");
			request.setAttribute("error",
					content.getString("login.wrong.input.passwords.notequals"));
		} else {
			user = new Users(email, password);
		}
		return user;
	}
}
