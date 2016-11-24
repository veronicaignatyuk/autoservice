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
 * Order user service
 * 
 * @author Veronika
 *
 */
public class OrderUserService implements Command {

	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");
	public Logger Log = Logger.getLogger(OrderUserService.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Order user ");
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		UserServiceImpl userSer = new UserServiceImpl();
		Users user;
		int d = validate(request);
		if (d == 0) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_USER_ORDER");
		} else {
			try {
				Log.trace("Get Username");
				user = userSer.getUser(request.getParameter("Username"));
				Log.trace("Order service by user");
				userSer.orderService(user, request.getParameter("Car"),
						request.getParameter("Service"),
						request.getParameter("Date"));
				Log.trace("Set attributes");
				request.setAttribute("Username", user.getLogin());
				request.setAttribute("cars1", userSer.getCars(user));
				request.setAttribute("service1", userSer.getService(user));
				Log.trace("Choice admin page ");
				nextPage = properties.getString("PAGE_COMMAND_USERPAGE");
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot order", e);
				request.setAttribute("error",
						content.getString("order.wrong"));
				nextPage = properties.getString("PAGE_COMMAND_USER_ORDER");
			}
		}
		return nextPage;
	}

	/**
	 * 
	 * @param request
	 */
	private int validate(HttpServletRequest request) {
		Log.debug("Checking valid input");
		String car = request.getParameter("Car");
		String service = request.getParameter("Service");
		String date = request.getParameter("Date");
		if (Objects.equals(car, "") || Objects.equals(service, "")
				|| Objects.equals(date, "")) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("login.wrong.input.empty"));
		} else {
			return 1;
		}
		return 0;
	}
}
