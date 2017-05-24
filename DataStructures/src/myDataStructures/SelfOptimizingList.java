package myDataStructures;

import java.util.Iterator;

public class SelfOptimizingList<E>
{
	public static final int INITIAL_SIZE = 10;
	
	private ArrayList<Node<E>> data;
	
	public SelfOptimizingList()
	{
		this.data = new ArrayList<Node<E>>(INITIAL_SIZE);
	}
	
	public E request(E item)
	{
		int index = data.indexOf(new Node<E>(item));

		if(index < 0)
		{
			return null;
		}
		
		Node<E> result = data.get(index);

		result.incrementPriority();
		moveUp(index,result);
		return result.item;
	}
	
	private boolean moveUp(int index,Node<E> result)
	{
		if(index != 0 && result.priority > data.get(index - 1).priority)
		{
			this.swapUp(index);
			return moveUp(index - 1,result);
		}
		else
		{
			return true;
		}
	}
	
	protected boolean add(E item,int priority)
	{
		return data.add(new Node<E>(item,priority));
	}
	
	public boolean add(E item)
	{
		return this.add(item,0);
	}
	
	protected E get(int index) 
	{
		return data.get(index).item;
	}
	
	private Node<E> swapUp(int index)
	{
		if(index <= 0 || index > data.size() - 1)
		{
			return null;
		}
		else
		{
			Node<E> temp = data.get(index - 1);
			data.set(index - 1, data.get(index));
			data.set(index, temp);
			return temp;
		}
	}
	
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		Iterator<Node<E>> itr = data.iterator();
		Node<E> temp;
		
		while(itr.hasNext())
		{
			temp = itr.next();
			string.append("Entry: " + temp.item + "\tPriority: " + temp.priority + "\n");
		}
		return string.toString();
	}
	
	/*
	private Node<E> swapDown(int index)
	{
		if(index < 0 || index >= data.size() - 1)
		{
			return null;
		}
		else
		{
			Node<E> temp = data.get(index + 1);
			data.set(index + 1, data.get(index));
			data.set(index, temp);
			return temp;
		}
	}
	*/
	
	protected static class Node<E>
	{
		private E item;
		private int priority;
		
		private Node(E item,int priority)
		{
			this.item = item;
			this.priority = priority;
		}
		
		private Node(E item)
		{
			this(item,0);
		}
		
		protected int incrementPriority()
		{
			return ++this.priority;
		}
		
		@Override
		public boolean equals(Object object)
		{
			if(object != null)
			{
				@SuppressWarnings("unchecked")
				Node<E> node = (Node<E>)object;
				return this.item.equals(node.item);
			}
			return false;
		}
	}
}
