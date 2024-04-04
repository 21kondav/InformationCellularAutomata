package product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import automata_theory.AbstractCell;

public class ChannelCell extends AbstractCell {
	public ChannelCell(Character initial, int id) {
		super(initial, id);
		// NOTE Auto-generated constructor stub
	}
	/*
	 *NOTE: This the rule we will use for all of our tests, any collision error are probably caused by this method
	 */
	@Override
	public Character localUpdate() {
		Character c = basicUpdate();
		setNextState(c);
		return c;
	}
	//below are several methods to choose from for updating the state
	/**
	 * Null items choose random character to update with information unless all options are null
	 * Similar to random 
	 * @return character chosen
	 */
	public Character nondeterminsitic() {
		List<AbstractCell> neighbors = getNeighborhood();
		ArrayList<AbstractCell> notNull = new ArrayList<AbstractCell>();
		neighbors.forEach(item ->{
			if(!item.equals(this)) notNull.add(item);
		});//adding non null characters to list
		if(notNull.isEmpty()) return null;
		Random rand = new Random();
		return notNull.get(rand.nextInt(0, notNull.size())).getCurrent();
	}
	/**
	 * Use next character for choice
	 * @return character chosen
	 */
	public Character basicUpdate() {
		// neighbors
		List<AbstractCell> neighbors = getNeighborhood();
		/*
		 * the radius, also where the current cell will be at
		 * 0<= c< r is left of this instance and r<c<= 2r is to the right
		 */
		int currIndex =  neighbors.indexOf(this);
		/*
		 * Information transmission from point a to point b over the channel requires, at minimum, 
		 * that after each timestep, the information is closer to reciever than it was in any of the steps prior
		 */
		/*
		 * most basic rule for any system
		 * a_{i}=a_{i-1}
		 * take the previous symbol and move it up one
		 */
		Character c = neighbors.get(currIndex-1).getCurrent();
		return c;
	}
	/**
	 * Choose the symbol which furthers behind the current cell.
	 * Transfers more information faster
	 * @return character chosen
	 */
	public Character fastBasic() {
		return getNeighborhood().get(0).getCurrent();
	}
	//chooses some previous cell to move forward
	/**
	 * Choose a random cell which is before the current cell
	 * @return
	 */
	public Character undeterminedPrior() {
		List<AbstractCell> neighbors = getNeighborhood();
		int currIndex = neighbors.indexOf(this);
		Random rand = new Random();
		return neighbors.get(rand.nextInt(0, currIndex)).getCurrent();
	}
	/**
	 * Find the nearest neighbor to current instance which has a given character
	 * @param c
	 * @return
	 */
	public AbstractCell nearestNeighbor(Character c) {
		return null;
	}
}
