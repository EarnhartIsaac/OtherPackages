package myDataStructures;

import java.util.Collection;

public interface Queue<E> extends Collection<E>
{
	/**
	 * Inserts item at the rear of the queue. 
	 * Returns true if successful; returns false if the item
	 * could not be inserted
	 * @param item the item to be inserted
	 * @return true if successful;false if item could not be inserted
	 */
	public boolean offer(E item);
	
	/**
	 * Removes an entry at the front f the queue 
	 * and returns it if the queue is not empty.
	 * If the queue is not empty, throws a NoSuchElementException
	 * @return the item removed
	 * @throws NoSuchElementException
	 */
	public E remove();
	
	/**
	 * Inserts the specified element into this queue 
	 * if it is possible to do so immediately without 
	 * violating capacity restrictions, returning true 
	 * upon success and throwing an IllegalStateException 
	 * if no space is currently available
	 * @param item the element to add
	 * @return true when successful
	 * @throws IllegalStateException
	 */
	public boolean add(E item);
	
	/**
	 * Removes the entry at the front of the queue and returns it;
	 * returns null if the queue is empty
	 * @return item removed, or null if empty
	 */
	public E poll();
	
	/**
	 * Returns the entry at the front of the queue 
	 * without removing it;
	 * returns null if the queue is empty
	 * @return item at front, or null if empty
	 */
	public E peek();
	
	/**
	 * Returns the entry at the front of the queue 
	 * without removing it.
	 * If the queue is empty, throws a NoSuchElementException
	 * @return the top of the queue
	 * NoSuchElementException
	 */
	public E element();
}
