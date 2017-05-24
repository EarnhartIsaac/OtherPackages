package myDataStructures;

public interface HeapInterface<E> 
{
	
	/**
	 * Checks to see if an element is present 
	 * in the heap
	 * @param e the element to check for
	 * @return true if element is present, false if not
	 */
	public boolean contains(E e);
	
	/**
	 * Removes all of the elements from the heap.
	 */
	public void clear();
	
	/**
	 * Adds a new element e into the heap
	 * @param e the element to add
	 * @return true always
	 */
	public boolean offer(E e);
	
	/**
	 * Returns the head of the heap,
	 * returns null if heap is empty
	 * @return head of heap, null if empty
	 */
	public E peek();
	
	/**
	 * Returns and removes the head of the heap,
	 * returns null if heap is empty
	 * @return heap of heap, null if empty
	 */
	public E poll();
	
	/**
	 * Returns the number of elements in
	 * this heap
	 * @return number of elements
	 */
	public int size();
	
	/**
	 * Returns an array with all elements
	 * of heap in level-order
	 * @return
	 */
	public Object[] toArray();
	
	/**
	 * Removes the head of the heap
	 * @return true if an item was removed, false if empty
	 */
	public boolean remove();
}
