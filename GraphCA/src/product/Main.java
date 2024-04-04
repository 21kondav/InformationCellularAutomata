package product;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import automata_theory.AbstractCell;

public class Main {

	public static void main(String[] args) {
		//special symbols
		char halt = '!';
		//set up the alphabet
		Set<Character> alphabet = new HashSet<Character>();
		alphabet.addAll(new HashSet<Character>(){{
			add(halt);
			add('H');
			add('e');
			add('l');
			add('o');
			add('W');
			add(' ');
			add('r');
			add('d');
			}});
		//radius
		int radius = 5;
		int size = 10;
		try {
			InfoCellAuto mainAutomata = new InfoCellAuto( halt, alphabet, radius, size);
			test(mainAutomata);
			
		} catch (Exception e) {
			// NOTE Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void test(InfoCellAuto mainAutomata) {
		mainAutomata.getIn().generateMessage("Hello World!");
		System.out.println(mainAutomata);

		try {
			mainAutomata.taskManager();
		}catch (Exception e) {
			// NOTE: handle exception
			System.err.println(mainAutomata);
			e.printStackTrace();
		}
		System.out.println(mainAutomata);
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
