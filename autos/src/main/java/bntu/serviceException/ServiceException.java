package main.java.bntu.serviceException;

import org.apache.log4j.Logger;

/**
 * Checked exception thrown when something is wrong in Check underlying
 * exception for more details.
 * 
 * @author Veronika
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Error during operation with Service";
	private final Logger log = Logger.getLogger(getClass().getSimpleName());

	public ServiceException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		log.error(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
		log.error(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
		log.error(MESSAGE);
	}

}
