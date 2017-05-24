package myDataStructures;

public interface Stack<E> 
{
	/**
	 * checks to see if the stack is empty or not
	 * @return returns true if the stack is empty
	 */
	public boolean isEmpty();
	
	/**
	 * looks at the object at the top of the stack
	 * without modifying it
	 * @return the object at the top of the stack
	 */
	public E peek();
	
	/**
	 * pulls the object off the top of the stack
	 * and deletes it from the stack
	 * @return the value on the top of the stack
	 */
	public E pop();
	
	/**
	 * adds a object to the top of the stack
	 * @param the object to add
	 * @return the object that was added to the top of the stack
	 */
	public E push(E obj);
}
