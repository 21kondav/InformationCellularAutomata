package product;

import automata_theory.AbstractCell;
import information_theory.Source;
import information_theory.Transmitter;

public class TransmitCell extends AbstractCell implements Transmitter{
	Source from;
	public TransmitCell(Source from, Character initial, int id) {
		super(initial, true, id);
		this.from = from;
	}
	/**
	 * @throws IllegalStateException when a collision occurs
	 * 		A collision is caused here when Transmitting cell is not properly updated to a nullSymbol
	 */
	@Override
	public Character readIn() throws IllegalStateException, IllegalAccessException{
		// NOTE Auto-generated method stu
		Character curr = from.getSymbol();
		return curr;
	}
	@Override
	public Character localUpdate() throws IllegalStateException, IllegalAccessException {
		// NOTE Auto-generated method stub
		Character in = readIn();
		setNextState(in);
		return in;
	}

}
