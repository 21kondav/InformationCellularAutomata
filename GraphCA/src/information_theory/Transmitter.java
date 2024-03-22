package information_theory;

@FunctionalInterface
/**
 * A transmitter accepts a symbol and turns into a signal
 * @param <S> - transmittable signal type
 */
public interface Transmitter {
	
	/**
	 * relays between its source and transmitter
	 * @param symbol
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalStateException 
	 */
	public Character readIn() throws IllegalStateException, IllegalAccessException;
}
