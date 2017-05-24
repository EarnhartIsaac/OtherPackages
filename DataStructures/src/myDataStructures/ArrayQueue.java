package myDataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> extends ArrayQueueAdapter<E>
{
	public static final int DEFAULT_START_SIZE = 10;
	
	private E[] theData;
	private int front;
	private int rear;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int initCapacity)
	{
		theData = (E[])new Object[initCapacity];
		front = 0;
		rear = initCapacity - 1;
		size = 0;
	}
	
	public ArrayQueue()
	{
		this(DEFAULT_START_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	private void reallocate() 
	{
		int initialCapacity = theData.length;
		E[] newData = (E[])new Object[2 * theData.length];
		int j = front;
		for (int i = 0; i < size; i++) 
		{
			newData[i] = theData[j];
			j = (j + 1) % initialCapacity;
		}
		front = 0;
		rear = size - 1;
		theData = newData;
	} 
	
	@Override
	public boolean offer(E item) 
	{
		if (size == theData.length) 
		{
			reallocate();
		}
		size++;
		rear = (rear + 1) % theData.length;
		theData[rear] = item;
		return true;
	}
	
	@Override
	public E remove() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean add(E item) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public E poll() 
	{
		if (size == 0) 
		{
			return null;
		}
		E result = theData[front];
		front = (front + 1) % theData.length;
		size--;
		return result;
	}
	
	@Override
	public E peek() 
	{
		if(size == 0)
		{
			return null;
		}
		return theData[front];
	}
	
	@Override
	public E element() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clear() 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean contains(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Iterator<E> iterator() 
	{
		return new QueueIterator();
	}
	
	@Override
	public boolean remove(Object o) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int size() 
	{
		return this.rear - this.front + 1;
	}
	
	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> T[] toArray(T[] a) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private class QueueIterator implements Iterator<E> 
	{
		private int index;
		private int count = 0;
		
		public QueueIterator() 
		{
			index = front;
		}
		
		@Override
		public boolean hasNext() 
		{
			return count < size;
		}

		@Override
		public E next() 
		{
			if (!hasNext()) 
			{
			throw new NoSuchElementException();
			}
			E returnValue = theData[index];
			index = (index + 1) % theData.length;
			count++;
			return returnValue;
		}
		
		@Override
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
	}
}
