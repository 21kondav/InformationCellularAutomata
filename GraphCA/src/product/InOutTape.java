package product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import information_theory.Destination;
import information_theory.Source;
/**
 * can act as in and out tape
 */
public class InOutTape implements Destination, Source {
	private Queue<Character> start;
	private Queue<Character> end;
	int size;
	public InOutTape() {
		// NOTE Auto-generated constructor stub
		end = new LinkedList<Character>();
		start = new LinkedList<Character>();
	}

	@Override
	public String getMessage() {
		// NOTE Auto-generated method stub
		String str = "";
		while(!end.isEmpty()) {
			str+=end.remove();
		}
		return str;
	}

	@Override
	public void recieve(Character s) {
		// NOTE Auto-generated method stub
		end.add(s);
	}
	//TODO: Add later, simply writes to file f
	@Override
	public void outputMessage(String fileName) {
		// NOTE Auto-generated method stub
		
	}


	@Override
	public void generateMessage(String str) {
		// NOTE Auto-generated method stub
		char[] charArr = str.toCharArray();
		for(int i =0; i< charArr.length ; i++) {
			start.add(charArr[i]);
		}
	}

	@Override
	public String generateRandomMessage() {
		// NOTE Auto-generated method stub
		Random rand = new Random();
		char[] tape = new char[size];
		for(int i = 0; i< size ; i++) {
			tape[i] = (char) rand.nextInt(60, 84);
			start.add(tape[i]);
		}
		
		return new String(tape);
	}

	@Override
	public String readMessage(File f) {
		// NOTE Auto-generated method stub
		String str = "";
		try {
			Scanner scan = new Scanner(f);
			while(str.length() < size && scan.hasNext()) {
				str+=scan.next();
			}	
			scan.close();
		} catch (FileNotFoundException e) {
			// NOTE Auto-generated catch block
			e.printStackTrace();
		}
		addToTape(str, true);
		return str;
	}

	@Override
	public String enterMessage() {
		// NOTE Auto-generated method stub
		String str = "";
		Scanner scan = new Scanner(System.in);

		while(str.length() < size && scan.hasNext())str+=scan.next();
		scan.close();
		addToTape(str, true);
		return str;
	}
	/**
	 * Adds the given string to the in or out tape
	 * 
	 * @param str
	 * @param in - true if add to in, false if add to out
	 */
	private void addToTape(String str, boolean in) {
		if(in)
			for(char c: str.toCharArray())
				start.add(c);
		else
			for(char c: str.toCharArray())
				start.add(c);
	}
	@Override
	public Character getSymbol() {
		return start.remove();
	}
	public boolean inputIsEmpty() {
		return start.isEmpty();
	}
	@Override
	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("Input Tape: |");
		start.forEach(c -> strBuild.append(new char[] {c, '|'}));//build input tape to string
		strBuild.append("\nOutput Tape: |");
		end.forEach(c -> strBuild.append(new char[] {c, '|'}));
		return strBuild.toString();
	}
}
