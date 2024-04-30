package product;

import java.util.List;

import automata_theory.AbstractCell;
import information_theory.Destination;
import information_theory.Receiver;

public class RecieveCell extends AbstractCell implements Receiver {
	private Destination to;
	public RecieveCell(Destination to, Character initial, int id) {
		super(initial, id);
		this.to = to;
	}

	@Override
	public void writeOut() throws IllegalAccessException{
		// NOTE Auto-generated method stub
		Character curr = getCurrent();
		
		if(curr != null ) {//don't send null characters, they are assumed to contain no information
			to.recieve(curr);//send symbol to output
		}

	}

	@Override
	public Character localUpdate() throws IllegalAccessException {
		// NOTE Auto-generated method stub
		List<AbstractCell> neighbors = getNeighborhood();
		writeOut();
		Character next = neighbors.get(neighbors.indexOf(this)-1).getCurrent();
		setNextState(next);
		return next;
	}

}
