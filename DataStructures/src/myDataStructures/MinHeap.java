package myDataStructures;

import java.util.Comparator;

public class MinHeap<E extends Comparable<E>> extends Heap<E>
{
	Comparator<E> comparator;
	
	/**
	 * Compares to objects in order to organize the heap
	 * @param first first object to compare
	 * @param second second object to compare
	 * @return positive if first > second, negative if first < second
	 */
	protected int compare(E first, E second) 
	{
		if(comparator != null)
		{
			//Comparator returns neg. for first < second, positive for first > second
			return comparator.compare(first, second);
		}
		else
		{
			//CompareTo returns neg. for first < second, positive for first > second
			return first.compareTo(second);
		}
	}
	
	public MinHeap(Comparator<E> comparator)
	{
		this.comparator = comparator;
	}
	
	public MinHeap()
	{
		this.comparator = null;
	}
}
