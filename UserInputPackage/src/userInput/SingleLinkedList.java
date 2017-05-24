package userInput;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements List<E>
{
	private Node<E> head;
	private int size;
	
	@Override
	public boolean add(E data)
	{
		System.out.println(data);
		this.listIterator(size).add(data);
		return true;
	}

	@Override
	public void add(int index, E data) 
	{
		this.listIterator(index).add(data);
	}

	@Override
	public boolean contains(Object object) 
	{
		return this.indexOf(object) != -1;
	}


	@Override
	public E get(int index) 
	{
		return this.listIterator(index).next();
	}

	@Override
	public int indexOf(Object object) 
	{
		SingleIterator temp = (SingleIterator)this.iterator();
		while(temp.hasNext())
		{
			if(temp.next().equals(object))
			{
				return temp.index - 1;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}
	
	@Override
	public Iterator<E> iterator() 
	{
		return new SingleIterator(0);
	}

	@Override
	public boolean remove(Object object) 
	{
		SingleIterator temp = (SingleIterator)this.iterator();
		while(temp.hasNext())
		{
			if(temp.next().equals(object))
			{
				temp.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public E remove(int index) 
	{
		if(index > -1 && index < size)
		{
			SingleIterator temp = (SingleIterator)this.iterator();
			Node<E> tempData = null;
			for(int counter = 0;counter<index;index++)
			{
				if(!temp.hasNext())
				{
					return null;
				}
				else
				{
					tempData = temp.nextItem;
					temp.next();
				}
			}
			tempData.nextNode = tempData.nextNode.nextNode;
			return temp.nextItem.data;
		}
		else
		{
			return null;
		}
	}

	@Override
	public E set(int index, E data) 
	{
		SingleIterator temp = (SingleIterator)this.iterator();
		E tempData = temp.next();
		temp.set(data);
		return tempData;
	}

	@Override
	public int size() 
	{
		return size;
	}

	
	/*************************
	 * INCOMPLETE METHOD STUBS
	 * *************************
	 */
	

	@Override
	public boolean addAll(Collection<? extends E> arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsAll(Collection<?> arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int lastIndexOf(Object object)
	{
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() 
	{
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) 
	{
		return null;
	}
	
	@Override
	public boolean removeAll(Collection<?> arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<E> subList(int arg0, int arg1) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class Node<E>
	{
		private E data;
		private Node<E> nextNode;
		
		private Node(E data)
		{
			this.data = data;
		}
		
		public Node(E data,Node<E> nextNode)
		{
			this.data = data;
			this.nextNode = nextNode;
		}
	}
	
	public class SingleIterator implements Iterator<E>
	{
		private Node<E> nextItem;
		private Node<E> lastItemReturned;
		private int index = 0;

		public SingleIterator(int i) 
		{
			// error check index
			if (i < 0 || i > size) 
			{
				throw new IndexOutOfBoundsException("Invalid index " + i);
			}
			lastItemReturned = null; // No item returned yet
			// Special case of last item (why not let loop do this?)
			if (i == size) 
			{
				index = size;
				nextItem = null;
			} 
			else 
			{ // Start at the beginning
				nextItem = head;
				for (index = 0; index < i; index++) 
				{
					nextItem = nextItem.nextNode;
				}
			}
		}
	

		public void add(E data) 
		{
			if(head == null)
			{
				head = new Node<E>(data);
			}
			else if (nextItem == head) 
			{
				Node<E> newNode = new Node<E>(data);
				newNode.nextNode = nextItem;
				head = newNode;
			}
			else if (nextItem == null) 
			{
				lastItemReturned = new Node<E>(data,null);
			}
			else 
			{
				Node<E> newNode = new Node<E>(data);
				newNode.nextNode = nextItem;
				lastItemReturned.nextNode = newNode;
			}
			size++;
			index++;
			lastItemReturned = null;
		} 

		@Override
		public boolean hasNext() 
		{
			return this.nextItem != null;
		}

		@Override
		public E next() 
		{
			if (!hasNext()) 
			{
				throw new NoSuchElementException();
			}
			lastItemReturned = nextItem;
			nextItem = nextItem.nextNode;
			index++;
			return lastItemReturned.data;
		}

		@Override
		public void remove() 
		{

		}
		
		public void set(E data)
		{
			if(lastItemReturned != null)
			{
				lastItemReturned.data = data;
			}
		}
	}
}