package main.java.bntu.dao.exception;

import org.apache.log4j.Logger;

/**
 * Checked exception thrown when something is wrong in Check underlying
 * exception for more details.
 * 
 * @author Veronika
 *
 */
public class AutoserviceException extends Exception {

	private static final long serialVersionUID = -7846932447271292171L;
	private static final String MESSAGE = "Error during operation with application";
	private final Logger log = Logger.getLogger(getClass().getSimpleName());

	public AutoserviceException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public AutoserviceException(String message) {
		super(message);
		log.error(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public AutoserviceException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public AutoserviceException(Throwable cause) {
		super(cause);
		log.error(MESSAGE);
	}
}
