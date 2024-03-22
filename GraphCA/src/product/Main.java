package product;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		//special symbols
		char halt = '!';
		char nullSymbol = '-';
		//set up the alphabet
		Set<Character> alphabet = new HashSet<Character>();
		alphabet.add(halt);
		alphabet.add(nullSymbol);
		alphabet.add('0');
		alphabet.add('1');
		//radius
		int radius = 1;
		int size = 20;
		try {
			InfoCellAuto mainAutomata = new InfoCellAuto(nullSymbol, halt, alphabet, radius, size);
			mainAutomata.getIn().generateMessage("0101110!");
			System.out.println(mainAutomata);
			mainAutomata.taskManager();
			System.out.println(mainAutomata);
		} catch (Exception e) {
			// NOTE Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * Main test 
	 */
	public static void mainTest() {
		
	}
	/**
	 * Tests the halting problem with this configuration
	 */
	public static void haltingTest() {
		
	}
	/**
	 * Tests how the automata handles large amounts of symbols
	 */
	public static void testSize() {
		
	}
	/**
	 * Tests how the automata handles complex information (large variety of symbols)
	 */
	public static void infoComplexTest() {
		
	}
	/**
	 * Combines the goals @see infoComplexTest() and @see testSize (large number of symbols and large problem sizes
	 */
	public void complexityTest() {
		
	}
}
