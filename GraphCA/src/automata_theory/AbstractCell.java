package automata_theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public abstract class AbstractCell{
	/*
	 * Neihgborhood must be ordered and
	 */
	private List<AbstractCell> neighborhood; 
	private Character state;
	//NOTE: Required because we need to calculate all next states before updating
	private Character next_state;
	private boolean globWritePermis; //allows cell to write any char to itself from external sources
	private char[] allowedChars; //allows cell to write any of the characters
	public AbstractCell(Character initState, int radius) {
		this.state = initState;
		next_state = null;
		globWritePermis = false;
		allowedChars = new char[0]; // no writables allowed, TODO: check efficiency on this?
		neighborhood = new ArrayList<AbstractCell>(radius);
		neighborhood.set(radius, this);
	}

	/**
	 * Allows a 
	 * @param initState
	 * @param writePermission
	 */
	public AbstractCell(Character initState, int radius, boolean writePermission) {
		this.state = initState;
		next_state = null;
		this.globWritePermis = writePermission;
		if(globWritePermis)
			allowedChars = null;// all chars are allowed 
		else this.allowedChars = new char[0];//if not assume no chars are allowed
		neighborhood = new ArrayList<AbstractCell>(radius);
		neighborhood.set(radius, this);
	}
	public AbstractCell(Character initState, int radius, char... allowedChars) {
		this.state = initState;
		next_state = null;
		this.allowedChars = allowedChars;//only the characters given are allowed
		Arrays.sort(allowedChars); //for easy access
		neighborhood = new ArrayList<AbstractCell>(radius);
		neighborhood.set(radius, this);
	}
	public void overWrite(Character c) throws IllegalAccessException{
		//if no global permission and not assigned any character then throw
		if(allowedChars.length == 0)
			throw new IllegalAccessException("The cell calling this method does not have write permission for any character set.");
		
		else if(globWritePermis || Arrays.binarySearch(allowedChars, c) >= 0) {
			state = c;
			return;
		}
		throw new IllegalAccessException("The character given was not found in the set of allowed characters");
		
 	}
	/**
	 * Semi-constructor
	 * Copies current and next state, not neighbors
	 * @param from
	 */
	public AbstractCell(AbstractCell from) {
		this.state = from.state;
		this.next_state = from.next_state;
		this.allowedChars = from.allowedChars;
		this.globWritePermis = from.globWritePermis;
		this.neighborhood = from.neighborhood;
		
	}
	public List<AbstractCell> getNeihgborhood(){
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
	/**
	 * - Returns the symbol to replace current state after update 
	 * - Calculates 'next_state'
	 * @return S - updated symbol
	 */
	public abstract Character localUpdate();
	//Maybe needed? public abstract S getNeighbor(int idx);

}
