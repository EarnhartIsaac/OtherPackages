package myDataStructures;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> extends DoubleLinkedListAdapter<E>
{
	private Node<E> head;
	private Node<E> tail;
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
		DoubleListIterator temp = (DoubleListIterator)this.listIterator();
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
	public int lastIndexOf(Object object)
	{
		DoubleListIterator temp = (DoubleListIterator)this.listIterator(size);
		while(temp.hasPrevious())
		{
			if(temp.previous().equals(object))
			{
				return temp.index - 1;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() 
	{
		return new DoubleListIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(int index) 
	{
		return new DoubleListIterator(index);
	}

	@Override
	public boolean remove(Object object) 
	{
		DoubleListIterator temp = (DoubleListIterator)this.listIterator();
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
		DoubleListIterator temp = (DoubleListIterator)this.listIterator(index);
		E tempData = temp.next();
		temp.remove();
		return tempData;
	}

	@Override
	public E set(int index, E data) 
	{
		DoubleListIterator temp = (DoubleListIterator)this.listIterator(index);
		E tempData = temp.next();
		temp.set(data);
		return tempData;
	}

	@Override
	public int size() 
	{
		return size;
	}

	
	
	
	private static class Node<E>
	{
		private E data;
		private Node<E> nextNode;
		private Node<E> previousNode;
		
		private Node(E data)
		{
			this.data = data;
		}
		
		public Node(E data,Node<E> previousNode,Node<E> nextNode)
		{
			this.data = data;
			this.previousNode = previousNode;
			this.nextNode = nextNode;
		}
	}
	
	public class DoubleListIterator implements ListIterator<E>
	{
		private Node<E> nextItem;
		private Node<E> lastItemReturned;
		private int index = 0;

		public DoubleListIterator(int i) 
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
	
		
		@Override
		public void add(E data) 
		{
			if(head == null)
			{
				head = new Node<E>(data);
				tail = head;
			}
			else if (nextItem == head) 
			{
				Node<E> newNode = new Node<E>(data);
				newNode.nextNode = nextItem;
				nextItem.previousNode = newNode;
				head = newNode;
			}
			else if (nextItem == null) 
			{
				Node<E> newNode = new Node<E>(data,tail,null);
				tail.nextNode = newNode;
				newNode.previousNode = tail;
				tail = newNode;
			}
			else 
			{
				Node<E> newNode = new Node<E>(data);
				newNode.previousNode = nextItem.previousNode;
				nextItem.previousNode.nextNode = newNode;
				newNode.nextNode = nextItem;
				nextItem.previousNode = newNode;
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
		public boolean hasPrevious() 
		{
			//Changed given hasPrevious Method
			//Old method was 
			//		return (nextItem == null && size != 0)
			//				|| (nextItem.previousNode != null);
			//This throws an exception if nextItem is null but list isn't empty
			if(nextItem != null)
			{
				return (nextItem.previousNode != null);
			}
			else
			{
				return (size != 0);
			}
		}

		@Override
		public E next() 
		{
			if (!hasNext()) {
			throw new NoSuchElementException();
			}
			lastItemReturned = nextItem;
			nextItem = nextItem.nextNode;
			index++;
			return lastItemReturned.data;
		}

		@Override
		public int nextIndex() 
		{
			return index + 1;
		}
		
		@Override
		public int previousIndex() 
		{
			return index - 1;
		}

		@Override
		public E previous() 
		{
			if (!hasPrevious()) 
			{
				throw new NoSuchElementException();
			}
			if (nextItem == null)
			{
				nextItem = tail;
			}
			else 
			{
				nextItem = nextItem.previousNode;
			}
			lastItemReturned = nextItem;
			index--;
			return lastItemReturned.data;
		}

		@Override
		public void remove() 
		{
			if(lastItemReturned != null)
			{
				if(lastItemReturned.previousNode == null && lastItemReturned.nextNode == null)
				{
					lastItemReturned = null;
				}
				else if(lastItemReturned.previousNode == null)
				{
					lastItemReturned.nextNode.previousNode = null;
					head = lastItemReturned.nextNode;
					lastItemReturned = null;
				}
				else if(lastItemReturned.nextNode == null)
				{
					lastItemReturned.previousNode.nextNode = null;
					tail = lastItemReturned.previousNode;
					lastItemReturned = null;
				}
				else
				{
					lastItemReturned.previousNode.nextNode = lastItemReturned.nextNode;
					lastItemReturned.nextNode.previousNode = lastItemReturned.previousNode;
					lastItemReturned = null;
				}
			}
			
		}

		@Override
		public void set(E e) 
		{
			lastItemReturned.data = e;
		}
		
	}

}
