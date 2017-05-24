package myDataStructures;

import java.util.LinkedList;

public class LinkedListStack<E> implements Stack<E>
{

	LinkedList<E> theData = new LinkedList<E>();
	
	@Override
	public boolean isEmpty() 
	{
		return theData.isEmpty();
	}

	@Override
	public E peek() 
	{
		return theData.getFirst();
	}

	@Override
	public E pop() 
	{
		return theData.removeFirst();
	}

	@Override
	public E push(E data) 
	{
		theData.addFirst(data);
		return data;
	}
	
}
