package myDataStructures;

import java.util.NoSuchElementException;

/**
 * Use
 * @author Isaac Earnhart
 * @since 02/28/2017
 * @param <E> the object type this data structure works with
 */
public class ArrayStack<E> implements Stack<E>
{
	private ArrayList<E> theData;
	
	/**
	 * Calls the default constructor for theData
	 */
	public ArrayStack()
	{
		this.theData = new ArrayList<E>();
	}
	
	/**
	 * checks to see if the stack is empty or not
	 * @return returns true if the stack is empty
	 */
	public boolean isEmpty()
	{
		return theData.isEmpty();
	}
	
	/**
	 * looks at the object at the top of the stack
	 * without modifying it
	 * @return the object at the top of the stack
	 */
	public E peek()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return theData.get(theData.size() - 1);
	}
	
	/**
	 * pulls the object off the top of the stack
	 * and deletes it from the stack
	 * @return the value on the top of the stack
	 */
	public E pop()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return theData.remove(theData.size() - 1);
	}
	
	/**
	 * adds a object to the top of the stack
	 * @param the object to add
	 * @return the object that was added to the top of the stack
	 */
	public E push(E obj)
	{
		theData.add(obj);
		return obj;
	}
}
