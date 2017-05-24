package myDataStructures;

import java.util.Comparator;

public class MaxHeap<E extends Comparable<E>> extends Heap<E>
{
	Comparator<E> comparator;
	
	/**
	 * Compares to objects in order to organize the heap
	 * @param first first object to compare
	 * @param second second object to compare
	 * @return positive if second > first, negative if second < first
	 */
	protected int compare(E first, E second) 
	{
		if(comparator != null)
		{
			//Comparator returns neg. for first > second, positive for first < second
			return comparator.compare(second,first);
		}
		else
		{
			//CompareTo returns neg. for first > second, positive for first < second
			return second.compareTo(first);
		}
	}
	
	public MaxHeap(Comparator<E> comparator)
	{
		this.comparator = comparator;
	}
	
	public MaxHeap()
	{
		this.comparator = null;
	}
}
