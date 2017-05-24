package myDataStructures;

public interface SearchTree<E>
{
	public boolean add(E item);
	
	public boolean contains(E target);
	
	public E find(E target);
	
	public E delete(E target);
	
	boolean remove(E target);
}
