package automata_theory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class CellularAutomata implements GraphI<AbstractCell>{

	private int radius; //neighborhood radius
	//Must be ordered
	private List<AbstractCell> allCells;
	private Set<Character> alphabet;//set of recognized symbols
	/**
	 * Cellular Automata must have an alphabet 
	 * @param alphabet
	 */
	public CellularAutomata(Set<Character> alphabet) {
		this.alphabet = alphabet;
		allCells = new ArrayList<AbstractCell>();
	}
	
	/**
	 * Updates the automaton according to its cells
	 */
	public void update() {
		//local update
		for(AbstractCell item: allCells) {
			item.localUpdate();
		}
		//global update
		for(AbstractCell item: allCells) {
			item.updateState();
		}
	}

	public int getRadius() {
		return radius;
	}
	public Set<Character> getAlphabet(){
		return alphabet;
	}
	/**
	 * @see GraphI#addNode(Object)
	 */
	@Override
	public void addNode(AbstractCell node) {
		// NOTE Auto-generated method stub
		allCells.add(node);
		int size = allCells.size(); //size of automata after addition
		int nodePos = allCells.indexOf(node);
		int nbrhdStart = radius - nodePos;//beginning of the neighborhood of node
		int nbrhdEnd = radius+nodePos;//end of the neighbor hood of node
		//if node is at the lower end and doesn't have any neigh
		for(int i = nbrhdStart; i < nbrhdEnd ; i++) {
			//if 'neighbor' below doesnt exist
			if(i < 0) node.addNeighbor(null);
			else if(i > size) break;
			else connect(node, (allCells.get(i)));
		}
	}
	/**
	 * @see GraphI#addNodes(Collection)
	 */
	@Override
	public void addNodes(Collection<AbstractCell> nodes) {
		// NOTE Auto-generated method stub
		for(AbstractCell cell: nodes) addNode(cell);
	}
	/**
	 * @see GraphI#removeNode(Object)
	 */
	@Override
	public void removeNode(AbstractCell node) {
		// NOTE Auto-generated method stub
		allCells.remove(node);
	}
	/**
	 * @see GraphI#removeNodes(Collection)
	 */
	@Override
	public void removeNodes(Collection<AbstractCell> nodes) {
		// NOTE Auto-generated method stub
		allCells.removeAll(nodes);
	}
	/**
	 * @see GraphI#removeIf(Predicate)
	 */
	@Override
	public void removeIf(Predicate<AbstractCell> pred) {
		// NOTE Auto-generated method stub
		allCells.removeIf(pred);
	}
	/**
	 * @see GraphI#getNodes()
	 */
	@Override
	public Set<AbstractCell> getNodes() {
		// NOTE Auto-generated method stub
		return new HashSet<AbstractCell>(allCells);
	}
	/**
	 * @see GraphI#connect(Object, Object)
	 */
	@Override
	public boolean connect(AbstractCell start, AbstractCell to) {
		// NOTE Auto-generated method stub
		if(!(start.hasNeighbor(to) && to.hasNeighbor(to)))
			return start.addNeighbor(to) && to.addNeighbor(start);
		return false;
	}
	/**
	 * @see GraphI#disconnect(Object, Object)
	 */
	@Override
	public boolean disconnect(AbstractCell start, AbstractCell to) {
		// NOTE Auto-generated method stub
		if(start.hasNeighbor(to) && to.hasNeighbor(to))
			return start.removeNeighbor(to) && to.removeNeighbor(start);
		return false;
	}
	/**
	 * Returns an array of cells which is disconnected from the current listing
	 * @return 
	 */
	public AbstractCell[] getCells() {
		return allCells.toArray(new AbstractCell[0]);
	}
	@Override
	public String toString() {
		String str = "|";
		for(AbstractCell cell: allCells) {
			str+=cell.getCurrent()+"|";
		}
		return str;
	}
}
