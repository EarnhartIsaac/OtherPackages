package myDataStructures;

//TODO Create methods to add into this data structure
public class OptimizedBinaryTree<E extends Comparable<E>>
{
	private ArrayList<Node<E>> theData;
	private int headPosition;
	
	public E request(E data)
	{
		return callData(false,null,headPosition,new Node<E>(data)).item;
	}
	
	private Node<E> callData(boolean leftCall,
							Integer parentIndex,
							Integer currentIndex,
							Node<E> data)
	{
		if(data == null || currentIndex == null)
		{
			return null;
		}
		
		Node<E> currentData = theData.get(currentIndex);
		int comparison = data.item.compareTo(currentData.item);
		Node<E> result;
		
		if(comparison > 0)
		{
			int tempIndex = currentData.rightIndex;
			result = callData(false,currentIndex,tempIndex,data);
			
			//Swapping the parent with its right node
			if(currentData.priority < result.priority)
			{
				if(parentIndex != null)
				{
					if(leftCall)
					{
						theData.get(parentIndex).leftIndex = currentData.rightIndex;
					}
					else
					{
						theData.get(parentIndex).rightIndex = currentData.rightIndex;
					}
				}
				
				currentData.rightIndex = result.leftIndex;
				result.leftIndex = currentIndex;
			}
			return result;
		}
		else if(comparison < 0)
		{
			int tempIndex = currentData.leftIndex;
			result = callData(true,currentIndex,tempIndex,data);
			
			//Swapping the parent with its left node
			if(currentData.priority < result.priority)
			{
				if(leftCall)
				{
					theData.get(parentIndex).leftIndex = currentData.leftIndex;
				}
				else
				{
					theData.get(parentIndex).rightIndex = currentData.leftIndex;
				}
				currentData.leftIndex = result.rightIndex;
				result.rightIndex = currentIndex;
			}
			return result;
		}
		else
		{
			theData.get(currentIndex).priority++;
			return theData.get(currentIndex);
		}
	}
	
	protected static class Node<E>
	{
		private E item;
		private int priority;
		private int rightIndex;
		private int leftIndex;
		
		private Node(E item,int priority,int rightIndex,int leftIndex)
		{
			this.item = item;
			this.priority = priority;
			this.rightIndex = rightIndex;
			this.leftIndex = leftIndex;
		}
		
		private Node(E item)
		{
			this(item,0,0,0);
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
