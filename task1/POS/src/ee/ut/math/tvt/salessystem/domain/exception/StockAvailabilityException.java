package ee.ut.math.tvt.salessystem.domain.exception;

/**
 * Thrown when the requested quantity exceeds available
 */
public class StockAvailabilityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs new <code>StockAvailabilityException</code>.
	 */
	public StockAvailabilityException() {
		super();
	}
	
	/**
	 * Constructs new <code>StockAvailabilityException</code> with  with the specified detail message.
	 * @param message the detail message.
	 */
	public StockAvailabilityException(final String message) {
		super(message);
	}
}
