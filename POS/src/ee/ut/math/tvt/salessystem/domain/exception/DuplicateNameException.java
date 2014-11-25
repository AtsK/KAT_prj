package ee.ut.math.tvt.salessystem.domain.exception;

/**
 * Thrown when the requested quantity exceeds available
 */
public class DuplicateNameException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs new <code>StockAvailabilityException</code>.
	 */
	public DuplicateNameException() {
		super();
	}
	
	/**
	 * Constructs new <code>StockAvailabilityException</code> with  with the specified detail message.
	 * @param message the detail message.
	 */
	public DuplicateNameException(final String message) {
		super(message);
	}
}
