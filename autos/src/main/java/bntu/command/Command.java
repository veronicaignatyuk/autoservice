package main.java.bntu.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;

/**
 * Common interface for every command in the application
 * 
 * @author Veronika
 *
 */
public interface Command {

	/**
	 * Method processes request and returns corresponding page path
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws PersistException
	 * @throws ConnectionPoolException
	 */

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws PersistException,
			ConnectionPoolException;
}