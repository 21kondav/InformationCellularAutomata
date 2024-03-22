package information_theory;
/**
 * A reciever converts a signal to a symbol
 * @param <S> - transmittable Signal type
 */
public interface Receiver {

	void writeOut() throws IllegalAccessException;
	@Override
	public String toString();
}
