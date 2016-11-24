package main.java.bntu.command.utils;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.java.bntu.command.*;
import main.java.bntu.commandImpl.SignIn;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * abstract class for command
 * 
 * @author Veronika
 *
 */
public abstract class AbstractCommand implements Command {

	public Logger Log = LogManager.getLogger(SignIn.class.getName());

	/**
	 * registration check
	 * 
	 * @param request
	 * @return
	 */
	public boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return Objects.equals(session.getAttribute("userID"), null);
	}

	/**
	 * error handling for errors in app
	 * 
	 * @param request
	 * @param message
	 * @param cause
	 */
	protected void errorHandling(HttpServletRequest request, String message,
			Throwable cause) {
		request.getSession().getId();
		Log.error("In session: " + request.getSession().getId() + " " + message
				+ " " + cause);
	}

}
