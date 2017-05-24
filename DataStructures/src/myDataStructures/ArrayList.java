package myDataStructures;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//TODO make the method shiftBackward put the result in a new array if the new size is half the old size
//TODO finish ArrayListIterator

/**
 * @author Isaac Earnhart
 *
 * @param <E> type of data used in this data structure
 */
public class ArrayList<E> extends ArrayListAdapter<E>
{
	
	/**************************
	 * ***INSTANCE VARIABLES***
	 **************************/
	/**
	 * 
	 */
	public static final int INITIAL_SIZE = 10;
	
	/**
	 * Array of the data used in this data structure.
	 * This array will should always be instantiated and
	 * the methods in this structure to not check to see 
	 * if theData is null but instead it is never left as null
	 * by any method
	 */
	private E[] theData;
	
	/**
	 * Size of the spaces used in theData 
	 * Example 1. if no spaces are used in theData, usedSize is 0.
	 * Example 2. if theData is fully used than theData.length is equal to usedSize.
	 */
	private int usedSize = 0;
	
	
	/*********************************
	 * ***CONSTRUCTORS***
	 ********************************/
	
	/**
	 * 
	 */
	public ArrayList()
	{
		initializeArray();
	}
	
	public ArrayList(int initialSize)
	{
		initializeArray(initialSize);
	}
	
	public ArrayList(E[] data,int used)
	{
		this.theData = data;
		this.usedSize = used;
	}
	
	private void initializeArray()
	{
		initializeArray(INITIAL_SIZE); 
	}
	
	@SuppressWarnings("unchecked")
	private void initializeArray(int size)
	{
		this.theData = (E[])new Object[size]; 
	}
	
	//Does not use extendArray(int hole) to avoid unnecessary comparation counter == hole
	@SuppressWarnings("unchecked")
	private void reallocate()
	{
		E[] temp = (E[])new Object[theData.length * 2];
		for(int counter = 0;counter<usedSize;counter++)
		{
			temp[counter] = theData[counter];
		}
		this.theData = temp;	
	}
	
	@SuppressWarnings("unchecked")
	private void reallocate(int hole)
	{
		E[] temp = (E[])new Object[theData.length * 2];
		for(int counter = 0;counter<usedSize+1;counter++)
		{
			if(counter == hole)
			{
				counter++;
			}
			temp[counter] = theData[counter];
		}
		usedSize++;
	}
	
	private void shiftForward(int index)
	{
		if(index < 0 || index > usedSize)
		{
			throw new NoSuchElementException();
		}
		if(usedSize == theData.length)
		{
			reallocate(index);
		}
		else
		{
			for(int counter = usedSize;counter>index;counter--)
			{
				theData[counter] = theData[counter-1];
			}
		}
		usedSize++;
	}
	
	private void shiftBackward(int index)
	{
		if(index < 0 || index > usedSize)
		{
			throw new NoSuchElementException();
		}
		for(int counter = index;counter<usedSize;counter++)
		{
			theData[counter] = theData[counter+1];
		}
		usedSize--;
	}
	
	@Override
	public boolean add(E data) 
	{
		if(usedSize == theData.length)
		{
			this.reallocate();
		}
		theData[usedSize] = data;
		usedSize++;
		return true;
	}
	

	@Override
	public void add(int position, E data) 
	{
		if(usedSize == theData.length)
		{
			this.reallocate(position);
		}
		else
		{
			this.shiftForward(position);
		}
		theData[position] = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() 
	{
		this.theData = (E[])new Object[INITIAL_SIZE];
		usedSize = 0;
	}

	@Override
	public boolean contains(Object o) 
	{
		return indexOf(o) == -1;
	}

	@Override
	public E get(int index) 
	{
		if(index < 0 || index >= usedSize)
		{
			throw new NoSuchElementException();
		}
		return theData[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) 
	{
		E temp = (E)o;
		ListIterator<E> itr = this.listIterator();
		while(itr.hasNext())
		{
			if(itr.next().equals(temp))
			{
				return itr.previousIndex();
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() 
	{
		return usedSize == 0;
	}

	@Override
	public Iterator<E> iterator() 
	{
		return new ArrayIterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object o) 
	{
		E temp = (E)o;
		ListIterator<E> itr = this.listIterator(usedSize);
		while(itr.hasPrevious())
		{
			if(itr.previous().equals(temp))
			{
				return itr.nextIndex();
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() 
	{
		return new ArrayListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) 
	{
		return new ArrayListIterator(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) 
	{
		E temp = (E)o;
		ListIterator<E> itr = this.listIterator();
		while(itr.hasNext())
		{
			if(itr.next().equals(temp))
			{
				this.shiftBackward(itr.previousIndex() - 1);
				return true;
			}
		}
		return false;
	}

	@Override
	public E remove(int index) 
	{
		if(index < 0 || index > usedSize)
		{
			throw new NoSuchElementException();
		}
		E temp = theData[index];
		this.shiftBackward(index);
		return temp;
	}
	
	@Override
	public E set(int index, E element) 
	{
		if(index < 0 || index > usedSize)
		{
			throw new NoSuchElementException();
		}
		E temp = theData[index];
		theData[index] = element;
		return temp;
	}

	@Override
	public int size() 
	{
		return usedSize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> subList(int fromIndex, int toIndex) 
	{
		E[] data = (E[])new Object[fromIndex - toIndex];
		for(int counter = 0;counter<fromIndex-toIndex;counter++)
		{
			data[counter] = theData[fromIndex + counter];
		}
		return new ArrayList<E>(data,data.length);
	}

	@Override
	public Object[] toArray() 
	{
		return (Object[])theData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) 
	{
		return (T[])theData; 
	}
	
	private class ArrayListIterator implements ListIterator<E>
	{
		
		private int position = 0;
		
		public ArrayListIterator(int index)
		{
			position = index;
		}
		
		public ArrayListIterator()
		{
			this(0);
		}
		
		@Override
		public void add(Object arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasNext() 
		{
			return usedSize != position;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() 
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			return theData[position++];
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int previousIndex() 
		{
			return position - 1;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(Object arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * Gives no added functionality to ArrayList but enables the ability
	 * to swap this data structure out with others without code alterations
	 * @author Isaac Earnhart
	 *
	 * @param <E>
	 */
	private class ArrayIterator implements Iterator<E>
	{	
		private int position = 0;
		
		@Override
		public boolean hasNext() 
		{
			return usedSize != position;
		}

		@Override
		public E next() 
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			return theData[position++];
		}
		
	}
}
