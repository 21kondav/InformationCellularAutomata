package product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		int start_radii = 1;
		int end_radii = 20;
		//constance size of 30 cells
		radii_test(start_radii, end_radii, "010101100110", '!', 30);
		int start_size = 10;
		int end_size = 50;
		//constant radii of 3 cells in either direction
		size_test(start_size, end_size, "1001010001011101", '!', 3);
	}

	public static Set<Character> getAlphabet(String message) {
		Set<Character> alphabet = new HashSet<Character>();
		for (char c : message.toCharArray()) {
			alphabet.add(c);
		}
		return alphabet;

	}

	public static void test(InfoCellAuto mainAutomata, String message) {

		mainAutomata.getIn().generateMessage(message);
		System.out.println(mainAutomata);

		try {
			mainAutomata.taskManager();
		} catch (StackOverflowError e) {
			// NOTE: handle exception
			System.err.println("Will likely never halt");

		} catch (Exception e) {
			// NOTE: handle exception
			System.err.println(mainAutomata);
			e.printStackTrace();
		}
		System.out.println(mainAutomata);
	}

	/**
	 * Run many tests, all array paramets must be the same lenth
	 * 
	 * @param messages - the messages to be run
	 * @param halts
	 * @param rads
	 * @param sizes
	 * @throws Exception
	 */
	public static void runMany(String[] messages, char[] halts, int[] rads, int[] sizes) throws Exception {
		boolean equality_check = true;
		equality_check &= (messages.length == halts.length) && (rads.length == sizes.length);
		equality_check &= halts.length == rads.length;
		if (!equality_check)
			throw new IllegalArgumentException("Size of all input arrays must be equal");
		// itterate overall combinations
		int size = messages.length;
		for (int i = 0; i < size; i++) {
			Set<Character> alphabet = getAlphabet(messages[i]);
			InfoCellAuto cellauto = new InfoCellAuto(halts[i], alphabet, rads[i], sizes[i]);
			cellauto.getIn().generateMessage(messages[i]);
			cellauto.taskManager();
		}
	}

	/**
	 * Runs tests on the same radius, message, and halt symbol with varying start
	 * sizes
	 * 
	 * @param start_size
	 * @param end_size
	 */
	public static void size_test(int start_size, int end_size, String message, char halt, int radius) {
		if (start_size > end_size && start_size > 0) {
			throw new IllegalArgumentException("Inputs must be postive and end size greater than start size");
		}
		int list_sizes = (end_size + 1) - start_size;
		int[] sizes = new int[list_sizes];
		String[] messages = new String[list_sizes];
		char[] halts = new char[list_sizes];
		int[] radii = new int[list_sizes];
		for (int i = start_size; i <= end_size; i++) {
			int j = i - start_size;
			sizes[j] = i;
			messages[j] = message;
			halts[j] = halt;
			radii[j] = radius;
		}
		try {
			runMany(messages, halts, radii, sizes);
		} catch (Exception e) {
			// NOTE Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Runs tests on the same radius, message, and halt symbol with varying start
	 * sizes
	 * 
	 * @param start_size
	 * @param end_size
	 */
	public static void radii_test(int start_radius, int end_radius, String message, char halt, int size) {
		if (start_radius > end_radius && start_radius > 0) {
			throw new IllegalArgumentException("Radii must be postive and end radii greater than start radii");
		}
		int list_sizes = (end_radius + 1) - start_radius;
		int[] sizes = new int[list_sizes];
		String[] messages = new String[list_sizes];
		char[] halts = new char[list_sizes];
		int[] radii = new int[list_sizes];
		for (int i = start_radius; i <= end_radius; i++) {
			int j = i-start_radius;
			sizes[j] = size;
			messages[j] = message;
			halts[j] = halt;
			radii[j] = i;
		}
		try {
			runMany(messages, halts, radii, sizes);
		} catch (Exception e) {
			// NOTE Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Runs tests on the same radius, message, and halt symbol with varying start
	 * sizes
	 * 
	 * @param start_size
	 * @param end_size
	 */
	public static void message_test(int radius, Set<Character> alphabet, int message_size, int n_messages, char halt, int size) {
		if (alphabet.isEmpty()) {
			throw new IllegalArgumentException("Alphabet cannot be empty");
		}
		int[] sizes = new int[n_messages];
		String[] messages = new String[n_messages];
		char[] halts = new char[n_messages];
		int[] radii = new int[n_messages];
		List<Character> list_alpha = new ArrayList<Character>(alphabet);
		for (int i = 0; i <= n_messages; i++) {
			String message= "";
			for(int j = 0; j < message_size;j++) {
				message+=list_alpha.get(i);
			}
			
			sizes[i] = size;
			messages[i] = message;
			halts[i] = halt;
			radii[i] = i;
		}
		try {
			runMany(messages, halts, radii, sizes);
		} catch (Exception e) {
			// NOTE Auto-generated catch block
			e.printStackTrace();
		}

	}

}
