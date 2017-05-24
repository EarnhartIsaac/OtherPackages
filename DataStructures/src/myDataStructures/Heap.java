package myDataStructures;

import java.util.ArrayList;

public abstract class Heap<E> implements HeapInterface<E>
{
	/****************/
	/***** DATA *****/
	private ArrayList<E> data;

	/****************************/
	/***** ABSTRACT METHODS *****/
	
	/**
	 * Compares to objects in order to organize the heap
	 * @param first first object to compare
	 * @param second second object to compare
	 * @return a positive, negative, or zero relative to first and second
	 */
	protected abstract int compare(E first,E second);
	
	/*******************/
	/***** METHODS *****/
	@Override
	public boolean contains(E e) 
	{
		return data.contains(e);
	}

	@Override
	public void clear() 
	{
		data = new ArrayList<E>();
	}

	//TODO organize with this method
	@Override
	public boolean offer(E e) 
	{
		data.add(e);
		
		int child = data.size() - 1;
		int parent = (child - 1) / 2; 

		while (parent >= 0 && compare(data.get(parent),
		data.get(child)) > 0) 
		{
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}
		return true;
	}

	@Override
	public E peek() 
	{
		return data.get(0);
	}

	@Override
	public E poll() 
	{
		if (data.isEmpty()) 
		{
			return null;
		}
		
		E result = data.get(0);

		if (data.size() == 1) 
		{
			data.remove(0);
			return result;
		}
		
		data.set(0, data.remove(data.size() - 1));
		int parent = 0;
		
		while (true) 
		{
			int leftChild = 2 * parent + 1;
			if (leftChild >= data.size()) 
			{
				break; 
			}
			int rightChild = leftChild + 1;
			int minChild = leftChild;
			if (rightChild < data.size()
					&& compare(data.get(leftChild), data.get(rightChild)) > 0) 
			{
				minChild = rightChild;
			}
		
			if (compare(data.get(parent), data.get(minChild)) > 0) 
			{
				swap(parent, minChild);
				parent = minChild;
			} 
			else 
			{ 
				break;
			}
		}
		return result;
	}

	@Override
	public boolean remove()
	{
		return this.poll() != null;
	}
	
	@Override
	public int size() 
	{
		return data.size();
	}

	@Override
	public Object[] toArray() 
	{
		return data.toArray();
	}
	
	/**
	 * Swaps two values within the data ArrayList
	 * @param first first value to be swapped
	 * @param second second value to be swapped
	 */
	private void swap(int first,int second)
	{
		E temp = data.get(first);
		data.set(first,data.get(second));
		data.set(second, temp);
	}
	
}
