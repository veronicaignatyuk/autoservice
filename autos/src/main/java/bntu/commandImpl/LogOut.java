package main.java.bntu.commandImpl;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.command.*;

import org.apache.log4j.Logger;

/**
 * Log out
 * 
 * @author Veronika
 *
 */
public class LogOut implements Command {
	public final ResourceBundle properties = ResourceBundle
			.getBundle("resources/config");

	public Logger Log = Logger.getLogger(LogOut.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		Log.info("Log Out ");
		String nextPage = ResourceBundle.getBundle("resources/config")
				.getString("LOGIN_PAGE_PATH");
		request.getSession().invalidate();
		return nextPage;
	}
}
