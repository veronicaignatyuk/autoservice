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
 * Add new user car
 * 
 * @author Veronika
 *
 */
public class AddCarcar implements Command {

	public Logger Log = Logger.getLogger(AddCarcar.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Add car");
		String nextPage = properties.getString("PAGE_COMMAND_USER_ADDCAR");
		Users user = null;
		UserServiceImpl userSer = new UserServiceImpl();
		Log.trace("Validation request ");
		int check = validate(request);
		if (check == 0) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_USER_ADDCAR");
		} else {
			try {
				Log.trace("Create new car");
				userSer.newCar(user, request.getParameter("brand"),
						request.getParameter("model"));
				Log.trace("Get Username");
				user = userSer.getUser(request.getParameter("Username"));
				Log.trace("Set attributes");
				request.setAttribute("Username", user.getLogin());
				request.setAttribute("cars1", userSer.getCars(user));
				request.setAttribute("service1", userSer.getService(user));
				Log.trace("Choice user page");
				nextPage = properties.getString("PAGE_COMMAND_USERPAGE");
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot sign in ", e);
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
	public int validate(HttpServletRequest request) {
		Log.debug("Checking valid input");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		if (Objects.equals(model, "") || Objects.equals(brand, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error", content.getString("car.empty.form"));
		} else {
			if (brand.length() > 15 || brand.length() < 2) {
				Log.debug("Input not valid. Brand must be from 2 to 15 ");
				request.setAttribute("error",
						content.getString("car.wrong.input.brand"));
			} else if (model.length() > 15 || model.length() < 2) {
				Log.debug("Input not valid. Model must be from 2 to 15 ");
				request.setAttribute("error",
						content.getString("car.wrong.input.model"));
			} else {
				return 1;
			}
		}
		return 0;
	}

}
