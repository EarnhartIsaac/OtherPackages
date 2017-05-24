package myDataStructures;

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements BinaryTreeInterface<E>
{
	protected Node<E> root;
	
	
	public BinaryTree()
	{
		root = null;
	}
	
	protected BinaryTree(Node<E> root)
	{
		this.root = root;
	}
	
	public BinaryTree(E data,BinaryTree<E> leftTree, BinaryTree<E> rightTree)
	{
		root = new Node<E>(data);
		if(leftTree != null)
		{
			root.left = leftTree.root;
		}
		else
		{
			root.left = null;
		}
		if(rightTree != null)
		{
			root.right = rightTree.root;
		}
		else
		{
			root.right = null;
		}
	}
	
	@SuppressWarnings("serial")
	protected static class Node<E> implements Serializable
	{
		protected E data;
		protected Node<E> left;
		protected Node<E> right;
		
		public Node(E data)
		{
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public String toString()
		{
			return data.toString();
		}
	}

	@Override
	public BinaryTree<E> getLeftSubtree() 
	{
		if(root != null && root.left != null)
		{
			return new BinaryTree<E>(root.left);
		}
		else
		{
			return null;
		}
	}

	@Override
	public BinaryTree<E> getRightSubtree() 
	{
		if(root != null && root.right != null)
		{
			return new BinaryTree<E>(root.right);
		}
		else
		{
			return null;
		}
	}

	@Override
	public E getData() 
	{
		if(root != null)
		{
			return root.data;
		}
		return null;
	}

	@Override
	public boolean isLeaf() 
	{
		return (root.left == null && root.right == null);
	}
	
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
	 * @param code
	 * @param leftChar
	 * @param rightChar
	 * @return
	 */
	public E decode(String code,final char leftChar,final char rightChar)
	{
		if(code.length() != 0)
		{
			if(code.charAt(0) == leftChar)
			{
				return this.getLeftSubtree().decode(code.substring(1),leftChar,rightChar);
			}
			else if(code.charAt(0) == rightChar)
			{
				return this.getRightSubtree().decode(code.substring(1),leftChar,rightChar);
			}
			else
			{
				return null;
			}
		}
		else
		{
			return this.root.data;
		}
	}
	
	/**
	 * Method that returns a string corresponding to data argument.
	 * @param data data to be encoded
	 * @param leftChar character to represent left tree movement
	 * @param rightChar character to represent right tree movement
	 * @return Returns the code for given data. This method will return an empty string if data does not have an code
	 */
	public String encode(E data,char leftChar,char rightChar)
	{
		StringBuilder sb = new StringBuilder();
		findPath(this.root,data,"",sb,leftChar,rightChar);
		return sb.toString();
	}
	
	/**
	 * Recursive method for finding the path to a certain piece of data in the tree.
	 * Built for wrapper method encode. 
	 * sb will have not been appended if no correlating data found.
	 * @param node starting node
	 * @param data the data being searched for
	 * @param currentPath the String representing the path this Recursive method has taken
	 * @param sb String Builder to hold the correct path when it is found
	 * @param leftChar character to represent left tree movement
	 * @param rightChar character to represent right tree movement
	 */
	private void findPath(Node<E> node,E data,String currentPath,StringBuilder sb,char leftChar,char rightChar)
	{
		if(node != null)
		{
			if(node.data != null && node.data.equals(data))
			{
				sb.append(currentPath);
			}
			else
			{
				findPath(node.left,data,currentPath + leftChar,sb,leftChar,rightChar);
				findPath(node.right,data,currentPath + rightChar,sb,leftChar,rightChar);
			}
		}
	}
	
	public static BinaryTree<String> readBinary(Scanner scan)
	{
		String data = scan.next();
		if(data.equals("null"))
		{
			return null;
		}
		else
		{
			BinaryTree<String> leftTree = readBinary(scan);
			BinaryTree<String> rightTree = readBinary(scan);
			return new BinaryTree<String>(data, leftTree, rightTree);
		}
	}
	
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		preOrderTraversal(root, 1, sb);
		return sb.toString();
	}
} 
