package myDataStructures;

//TODO Documentation for this structure
public class OptimizedBinaryTree2<E extends Comparable<E>> 
{
	Node<E> head;
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean add(E data)
	{
		//No previousNode at start, leftCall irrelevant when previousNode null
		return helpAdd(data,head,null,false);
	}
	
	//TODO Clean up this method
	/**
	 * 
	 * @param data
	 * @param currentNode
	 * @param previousNode
	 * @param leftCall
	 * @return
	 */
	private boolean helpAdd(E data,
							Node<E> currentNode,
							Node<E> previousNode,
							boolean leftCall)
	{
		if(currentNode != null)
		{
			int comparison = data.compareTo(currentNode.data);
			if(comparison > 0)
			{
				return helpAdd(data,currentNode.right,currentNode,false);
			}
			else if(comparison < 0)
			{
				return helpAdd(data,currentNode.left,currentNode,true);
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(previousNode != null)
			{
				if(leftCall)
				{
					previousNode.left = new Node<E>(data);
				}
				else
				{
					previousNode.right = new Node<E>(data);
				}
				return true;
			}
			else
			{
				this.head = new Node<E>(data);
				return true;
			}
		}
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public E request(E data)
	{
		Node<E> result = callData(data,head,null,false);
		if(result != null)
		{
			//No previousNode at start, leftCall irrelevant when previousNode null
			return result.data;
		}
		return null;
	}
	
	/**
	 * ALGORITHM: callData
	 * 
	 */
	
	/**
	 * Recursive helper method for the request(E) method.
	 * This method will increment the priority for the object
	 * if it is found and then re-balance the tree accordingly
	 * @param data data being searched for
	 * @param currentNode the node that this frame of the method will compare to data
	 * @param previousNode the parent node of currentNode needed for re-balancing priorities
	 * @param leftCall true if currentNode is the left node of previousNode, false if the opposite
	 * @return null if object not found
	 */
	private Node<E> callData(E data,
							Node<E> currentNode,
							Node<E> previousNode,
							boolean leftCall)
	{
		if(data != null && currentNode != null)
		{
			int comparison = data.compareTo(currentNode.data);
			if(comparison > 0)
			{
				Node<E> result = callData(data,currentNode.right,currentNode,false);
				
				/**
				 * Re-Organizing the tree so that nodes with higher priority can be accessed quicker
				 */
				if(result != null)
				{
					if(result.priority > currentNode.priority)
					{
						if(previousNode != null)
						{
							if(leftCall)
							{
								previousNode.left = currentNode.right;
							}
							else
							{
								previousNode.right = currentNode.right;
							}
						}
						else
						{
							head = result;
						}
						currentNode.right = result.left;
						result.left = currentNode;
					}
				}
				
				return result;
			}
			else if(comparison < 0)
			{
				Node<E> result = callData(data,currentNode.left,currentNode,true);
				
				/**
				 * Re-Organizing the tree so that nodes with higher priority can be accessed quicker
				 */
				if(result != null)
				{
					if(result.priority > currentNode.priority)
					{
						if(previousNode != null)
						{
							if(leftCall)
							{
								previousNode.left = currentNode.left;
							}
							else
							{
								previousNode.right = currentNode.left;
							}
						}
						else
						{
							head = result;
						}
						currentNode.left = result.right;
						result.right = currentNode;
					}
				}
				
				return result;
			}
			else
			{
				currentNode.priority++;
				return currentNode;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param node
	 * @param depth
	 * @param sb
	 */
	private void inOrderTraversal(Node<E> node,int depth,StringBuilder sb)
	{
		
		if (node != null) 
		{
			inOrderTraversal(node.left, depth + 1, sb);
			sb.append(node.toString() + "\n");
			inOrderTraversal(node.right, depth + 1, sb);
		} 
	}
	
	/**
	 * 
	 * @param node
	 * @param depth
	 * @param sb
	 */
	private void preOrderTraversal(Node<E> node,int depth,StringBuilder sb)
	{
		for (int i = 1; i < depth; i++) 
		{
			sb.append(" ");
		}
		if (node == null) 
		{
			sb.append("null\n");
		} 
		else 
		{
			sb.append(node.toString() + "\n");
			preOrderTraversal(node.left, depth + 1, sb);
			preOrderTraversal(node.right, depth + 1, sb);
		}
	}
	
	/**
	 * 
	 */
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		inOrderTraversal(head, 1, sb);
		preOrderTraversal(head, 1, sb);
		return sb.toString();
	}
	
	/**
	 * 
	 * @author W7262233
	 *
	 * @param <E>
	 */
	protected static class Node<E>
	{
		protected E data;
		protected int priority;
		protected Node<E> left;
		protected Node<E> right;
		
		public Node(E data)
		{
			this.data = data;
			this.priority = 0;
			this.left = null;
			this.right = null;
		}
		
		public boolean equals(Object object)
		{
			if(object != null)
			{
				@SuppressWarnings("unchecked")
				Node<E> node = (Node<E>)object;
				return this.data.equals(node.data);
			}
			return false;
		}
		
		
		protected int incrementPriority()
		{
			return ++this.priority;
		}
		
		public String toString()
		{
			return data.toString() + ":\t\t" + priority;
		}
	}
}
