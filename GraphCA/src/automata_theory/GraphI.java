package automata_theory;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Interface for any graph data strucutre
 * @param <T>
 */
public interface GraphI<T> {
	public void addNode(T node);
	public void addNodes(Collection<T> nodes);
	public void removeNode(T node);
	public void removeNodes(Collection<T> nodes);
	public void removeIf(Predicate<T> pred);
	public boolean connect(T start, T to);
	public boolean disconnect(T start, T from);
	public Set<T> getNodes();
}
