package information_theory;

import java.io.File;
/**
 * Any information source should have these methods
 */
public interface Source {
	/**
	 * Generate a random Message
	 * @return message
	 */
	public String generateRandomMessage();
	/**
	 * Read a message from a file
	 * @param f - read from a file
	 * @return message
	 */
	public String readMessage(File f);
	/**
	 * Take a message as an input
	 * @return message
	 */
	public String enterMessage();
	/**
	 * Removes symbol from the beginning tape to be used in transport
	 * @return symbol at the 'beginning' of tape
	 */
	public Character getSymbol();
	/**
	 * Add the given str to the input tape
	 * @param str - input string
	 */
	public void generateMessage(String str);
	@Override
	public String toString();
}
