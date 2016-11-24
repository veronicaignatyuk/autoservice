package main.java.bntu.commandImpl;

import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.Command;
import main.java.bntu.entity.Users;
import main.java.bntu.serviceException.ServiceException;
import main.java.bntu.serviceImpl.TechServiceImpl;
import main.java.bntu.serviceImpl.UserServiceImpl;

import org.apache.log4j.Logger;

/**
 * Change order status
 * 
 * @author Veronika
 *
 */
public class ChangeStatus implements Command {
	public Logger Log = Logger.getLogger(SignIn.class.getName());
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");
	public final ResourceBundle content = ResourceBundle
			.getBundle("resources/content");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		Log.info("Change order status");
		Users user;
		String nextPage = properties.getString("ERROR_PAGE_PATH");
		TechServiceImpl tech = new TechServiceImpl();
		UserServiceImpl userSer = new UserServiceImpl();
		Log.trace("Validation request");
		int id = validate(request);
		if (id == 0) {
			Log.trace("Error page wrong request");
			nextPage = properties.getString("PAGE_COMMAND_MASTER");
		} else {
			try {
				Log.trace("Try change status ");
				tech.changeOrderStatus(request.getParameter("Id"),
						request.getParameter("Stat"));
				Log.trace("Get Username");
				user = userSer.getUser(request.getParameter("Username"));
				Log.trace("Set attributes");
				request.setAttribute("Username", user.getLogin());
				request.setAttribute("service1",
						userSer.getOrdersByMaster(user));
				Log.trace("Choice master page");
				nextPage = properties.getString("PAGE_COMMAND_MASTER");
			} catch (ServiceException e) {
				Log.trace("error");
				Log.error("Cannot change status ", e);
				request.setAttribute("error",
						content.getString("login.wrong.change"));
				nextPage = properties.getString("PAGE_COMMAND_MASTER");
			}
		}
		return nextPage;
	}

	private int validate(HttpServletRequest request) {
		Log.debug("Checking valid input");
		String id = request.getParameter("Id");
		String stat = request.getParameter("Stat");
		if (Objects.equals(id, "") || Objects.equals(stat, "")
				|| !(id.matches("[-+]?\\d+"))) {
			Log.debug("Input not valid. Empty form.");
			request.setAttribute("error",
					content.getString("change.wrong.input.empty"));
		} else {
			return 1;
		}
		return 0;
	}

}
