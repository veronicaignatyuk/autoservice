package main.java.bntu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.java.bntu.command.Command;
import main.java.bntu.command.utils.CommandFactory;
import main.java.bntu.dao.abstr.AbstractJDBCDao;
import main.java.bntu.dao.exception.ConnectionPoolException;
import main.java.bntu.dao.exception.PersistException;

/**
 * Servlet implementation class Application Controller
 * 
 * @author Veronika
 *
 */
@WebServlet(name = "controller", displayName = "autoservice", urlPatterns = "/controller", description = "Application Controller")
public class Controller extends HttpServlet {
	public Logger Log = LogManager.getLogger(AbstractJDBCDao.class.getName());

	private static final long serialVersionUID = -3528652920110865101L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Request function
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String paramCommand = request.getParameter("command");

		if (paramCommand == null) {
			throw new IllegalArgumentException("parametr is null");
		}

		Command command = CommandFactory.getCommand(paramCommand);
		String nextPage = null;
		try {
			nextPage = command.execute(request, response);
		} catch (ConnectionPoolException | PersistException e) {
			Log.error("Cannot execute new page", e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
}