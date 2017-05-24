package myDataStructures;

public interface BinaryTreeInterface<E> 
{
	public BinaryTree<E> getLeftSubtree();

	public BinaryTree<E> getRightSubtree();

	public E getData();

	public boolean isLeaf();

	public String toString();

}
