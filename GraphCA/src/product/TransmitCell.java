package product;

import automata_theory.Cell;
import information_theory.Source;
import information_theory.Transmitter;

public class TransmitCell extends Cell implements Transmitter{
	Source from;
	private char nullSymbol;
	public TransmitCell(Source from, Character initial, int radius, char nullSymbol) {
		super(initial, radius, true);
		this.from = from;
		this.nullSymbol = nullSymbol;
	}
	/**
	 * @throws IllegalStateException when a collision occurs
	 * 		A collision is caused here when Transmitting cell is not properly updated to a nullSymbol
	 */
	@Override
	public Character readIn() throws IllegalStateException, IllegalAccessException{
		// NOTE Auto-generated method stu
		char curr = from.getSymbol();
		if(curr != nullSymbol) throw new IllegalStateException(); //thrown if collision occurs
		overWrite(getCurrent());
		return curr;
	}

}
