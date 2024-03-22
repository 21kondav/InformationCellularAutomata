package product;

import automata_theory.Cell;
import information_theory.Destination;
import information_theory.Receiver;

public class RecieveCell extends Cell implements Receiver {
	private char nullSymbol;
	private Destination to;
	public RecieveCell(Destination to, Character initial, int radius, char nullSymbol) {
		super(initial, radius, new char[]{nullSymbol});
		this.nullSymbol = nullSymbol;
		this.to = to;
	}

	@Override
	public void writeOut() throws IllegalAccessException{
		// NOTE Auto-generated method stub
		char curr = getCurrent();
		if(curr != nullSymbol) {//don't send null characters, they are assumed to contain no information
			to.recieve(this.getCurrent());//send symbol to output
			overWrite(nullSymbol); //writes null symbol to prepare for next recieved			
		}

	}

}
