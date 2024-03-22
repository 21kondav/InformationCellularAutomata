package product;

import java.util.Set;

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
	private char nullSymbol;
	private TransmitCell trans;
	private RecieveCell recieve;
	private int relativeRunTime;
	public InfoCellAuto(char nullSymbol, char haltSymbol, Set<Character> alphabet, int radius, int size) throws Exception {
		super(alphabet);
		if(!alphabet.contains(nullSymbol)) {
			throw new Exception("Designated Null symbol must be in alphabet");
		}
		this.nullSymbol = nullSymbol;
		this.readWrite = new InOutTape();
		trans = new TransmitCell(readWrite, nullSymbol, radius, nullSymbol);
		super.addNode(trans);
		for(int i = 0; i < size; i++) {
			super.addNode(new ChannelCell(nullSymbol, radius));
		}
		recieve = new RecieveCell(readWrite, nullSymbol, radius, nullSymbol);
		super.addNode(recieve);
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
			updateEndpoints();
			if(trans.getCurrent()== haltSymbol) {
				System.out.println("Halting symbol passed");
				halt =  true;
				continue;
			}
			transfer();
			count++;
		}
		relativeRunTime = count;
	}
	/**
	 * Check if there any symbols still left in the automata
	 * @return if it is empty
	 */
	public boolean isEmpty() {
		//if one of the cells is not the null symbol than there is still a symbol in the automat
		for(AbstractCell cell: getCells())
			if(cell.getCurrent() != nullSymbol) return false;
		return true;
	}
	//NOTE: this is probably overhead but kept for explanatory reasosn
	@Override
	public void transfer() {
		update();
	}

	@Override
	public int getRunTime() {
		return relativeRunTime;
	}
	@Override
	public void updateEndpoints() throws IllegalAccessException, IllegalStateException{
		trans.readIn();//read from recieve
		recieve.writeOut();//write to transmitter

	}
	@Override
	public String toString() {
		return readWrite.toString()+"\n Automata State: " + super.toString();
		
	}

}
