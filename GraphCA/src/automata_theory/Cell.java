package automata_theory;

import java.util.List;

/**
 * A concrete for cell with rule update
 * @param <S>
 */
public class Cell extends AbstractCell {
	public Cell(Character initSymbol, int radius) {
		super(initSymbol, radius);
	}
	public Cell(Character initSymbol, int radius, boolean writeAccess) {
		super(initSymbol, radius, writeAccess);
	}
	public Cell(Character initState,  int radius, char... allowedChars) {
		super(initState,radius, allowedChars);
	}
	/*
	 *NOTE: This the rule we will use for all of our tests, any collision error are probably caused by this method
	 */
	@Override
	public Character localUpdate() {
		// neighbors
		List<AbstractCell> neighbors = getNeihgborhood();
		/*
		 * the radius, also where the current cell will be at
		 * 0<= c< r is left of this instance and r<c<= 2r is to the right
		 */
		int radius =  neighbors.size()/2; 
		/*
		 * Information transmission from point a to point b over the channel requires, at minimum, 
		 * that after each timestep, the information is closer to reciever than it was in any of the steps prior
		 */
		/*
		 * most basic rule for any system
		 * a_{i}=a_{i-1}
		 * take the previous symbol and move it up one
		 */
		
		return neighbors.get(radius-1).getCurrent();
	}

}
