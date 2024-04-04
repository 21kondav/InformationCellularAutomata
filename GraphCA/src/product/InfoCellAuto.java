package product;

import java.util.HashMap;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import automata_theory.AbstractCell;
import automata_theory.CellularAutomata;
import information_theory.Destination;
import information_theory.InfoSystem;
import information_theory.Source;

public class InfoCellAuto extends CellularAutomata implements InfoSystem{
	//acts as input and output
	private InOutTape readWrite;
	//special symbols in computation theory
	private char haltSymbol; //halts the program
	private TransmitCell trans;
	private RecieveCell recieve;
	private int relativeRunTime;
	public InfoCellAuto(char haltSymbol, Set<Character> alphabet, int radius, int size) throws Exception {
		super(alphabet, radius);
		this.readWrite = new InOutTape();
		trans = new TransmitCell(readWrite, null, 0);
		addNode(trans);
		for(int i = 0; i < size; i++) {
			addNode(new ChannelCell(null, i+1));
		}
		recieve = new RecieveCell(readWrite, null, size+1);
		addNode(recieve);
	}
	public Source getIn() {
		return readWrite;
	}
	public Destination getOut() {
		return readWrite;
	}
	/**
	 * Manages all of the tasks to ensure programs runs well. It's goal is to provide a method of halting and a susinct way of
	 * running the program
	 * NOTE: debugging should start here
	 * @throws IllegalAccessException 
	 * @throws IllegalStateException for collisions and other state issues
	 */
	@Override
	public void taskManager() throws IllegalAccessException, IllegalStateException{
		boolean halt = false;
		//while there is still symbols on input tape or in the automata, and we are not instructed to halt
		int count = 0;
		while((!readWrite.inputIsEmpty()|| !isEmpty()) && !halt) {
			transfer();
			if(recieve.getCurrent() != null && recieve.getCurrent() == haltSymbol ) {
				System.out.println("Halting symbol passed");
				halt = true;
				continue;
			}
			System.out.println("Automata: " + super.toString()+ ", Entropy: "+entropy());
			count++;
		}
		System.out.println("Run Time: " +count);
		relativeRunTime = count;
	}
	/**
	 * Check if there any symbols still left in the automata
	 * @return if it is empty
	 */
	public boolean isEmpty() {
		//if one of the cells is not the null symbol than there is still a symbol in the automat
		for(AbstractCell cell: getCells())
			if(cell.getCurrent() != null) return false;
		return true;
	}
	public double entropy() {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		//compute totals
		int totalSymbols = 0;
		getAlphabet().forEach(item -> map.put(item, 0));
		for(AbstractCell cell: getCells()) {
			Character item = cell.getCurrent();
			if(item==null ) continue;
			map.replace(item, map.get(item)+1);
			totalSymbols++;
		}
		BiFunction<Integer, Double, Double> log = (Integer base, Double arg) -> Math.log(base)/Math.log(arg);
		double H = 0;
		int n_chars = map.keySet().size();
		for(Character c: map.keySet()) {
			double proba = map.get(c)/(double)totalSymbols;
			H += proba*log.apply(n_chars,proba);
		}
		return -H;
	}
	//NOTE: this is probably overhead but kept for explanatory reasosn
	@Override
	public void transfer() throws IllegalStateException, IllegalAccessException {
		update();
	}

	@Override
	public int getRunTime() {
		return relativeRunTime;
	}
	@Override
	public void updateEndpoints() throws IllegalAccessException, IllegalStateException{
		trans.readIn();//read from input
		recieve.writeOut();//write to output

	}
	@Override
	public String toString() {
		return "\n"+readWrite.toString()+"\nAutomata State: " + super.toString();
		
	}

}
