package main.java.bntu.dao.exception;

import org.apache.log4j.Logger;

/**
 * Checked exception thrown when something is wrong in Check underlying
 * exception for more details.
 * 
 * @author Veronika
 *
 */
public class PersistException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Error during operation with abstract operations";
	private final Logger log = Logger.getLogger(getClass().getSimpleName());

	public PersistException() {
	}

	/**
	 * 
	 * @param message
	 */
	public PersistException(String message) {
		super(message);
		log.error(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PersistException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public PersistException(Throwable cause) {
		super(cause);
		log.error(MESSAGE);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PersistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		log.error(message);
	}
}
