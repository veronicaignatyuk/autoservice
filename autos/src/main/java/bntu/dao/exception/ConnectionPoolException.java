package main.java.bntu.dao.exception;

import org.apache.log4j.Logger;

/**
 * Checked exception thrown when something is wrong in Check underlying
 * exception for more details.
 * 
 * @author Veronika
 *
 */
public class ConnectionPoolException extends AutoserviceException {

	private static final long serialVersionUID = 6890070980400263853L;
	private static final String MESSAGE = "Error during operation with connection pool";
	private final Logger log = Logger.getLogger(getClass().getSimpleName());

	public ConnectionPoolException() {
		log.error(MESSAGE);
	}

	/**
	 * 
	 * @param message
	 */
	public ConnectionPoolException(String message) {
		super(message);
		log.error(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
		log.error(MESSAGE);
	}
}
