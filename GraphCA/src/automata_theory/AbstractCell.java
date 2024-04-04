package automata_theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
public abstract class AbstractCell{
	/*
	 * Neihgborhood must be ordered and
	 */
	private List<AbstractCell> neighborhood; 
	int id;
	private Character state;
	//NOTE: Required because we need to calculate all next states before updating
	private Character next_state;
	private boolean globWritePermis; //allows cell to write any char to itself from external sources
	public AbstractCell(Character initState, int id) {
		this.state = initState;
		next_state = null;
		globWritePermis = false;
		neighborhood = new ArrayList<AbstractCell>();
		this.id = id;
	}

	/**
	 * Allows a 
	 * @param initState
	 * @param writePermission
	 */
	public AbstractCell(Character initState, boolean writePermission, int id) {
		this.state = initState;
		next_state = null;
		this.globWritePermis = writePermission;
	
		neighborhood = new ArrayList<AbstractCell>();
		this.id = id;
	}



	/**
	 * Semi-constructor
	 * Copies current and next state, not neighbors
	 * @param from
	 */
	public AbstractCell(AbstractCell from) {
		this.state = from.state;
		this.next_state = from.next_state;
		this.globWritePermis = from.globWritePermis;
		this.neighborhood = from.neighborhood;
		this.id = from.id;
		
	}
	public void overWrite(Character c) throws IllegalAccessException{
		//if no global permission and not assigned any character then throw
		 if(globWritePermis) {
			state = c;
			return;
		}		
		throw new IllegalAccessException("The character given was not found in the set of allowed characters");
		
 	}
	public List<AbstractCell> getNeighborhood(){
		return neighborhood;
	}
	/**
	 * Adds a neighbor to this cell
	 * @param cell
	 * @return whether the appendage was successful
	 */
	public boolean addNeighbor(AbstractCell cell) {
		if(!hasNeighbor(cell)) return neighborhood.add(cell);
		return false;
	}
	public boolean removeNeighbor(AbstractCell cell) {
		if(hasNeighbor(cell)) return neighborhood.remove(cell);
		return false;
	}
	/**
	 * Check if the given cell is already a neighbor
	 * @param neighbor
	 * @return whether cell is a neighbor of current instance
	 */
	public boolean hasNeighbor(AbstractCell neighbor) {
		return neighborhood.contains(neighbor);
	}
	/**
	 * Get the symbol at the current state
	 * @return return state
	 */
	public Character getCurrent() {
		return state;
	}

	/*
	 * updates the state from next_state
	 */
	public void updateState() {
		this.state = next_state;
	}
	public void setNextState(Character c) {
		next_state = c;
	}
	/**
	 * - Returns the symbol to replace current state after update 
	 * - Calculates 'next_state'
	 * @return S - updated symbol
	 * @throws IllegalAccessException 
	 * @throws IllegalStateException 
	 */
	public abstract Character localUpdate() throws IllegalStateException, IllegalAccessException;
	//Maybe needed? public abstract S getNeighbor(int idx);
	@Override
	public String toString() {
		return id+"+"+state;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCell other = (AbstractCell) obj;
		return  globWritePermis == other.globWritePermis
				&& id == other.id && Objects.equals(neighborhood, other.neighborhood)
				&& Objects.equals(next_state, other.next_state) && Objects.equals(state, other.state);
	}
}
