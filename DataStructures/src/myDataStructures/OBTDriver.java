package myDataStructures;

public class OBTDriver 
{
	public static void main(String[] args)
	{
		OptimizedBinaryTree2<String> tree = new OptimizedBinaryTree2<String>();
		tree.add("Hello");
		tree.add("Jim");
		tree.add("George");
		tree.add("Banana");
		
		tree.request("Hello");
		tree.request("Hello");
		tree.request("Jim");
		tree.request("Jim");
		tree.request("Jim");
		tree.request("Jim");

		tree.request("Banana");
		tree.request("Banana");
		tree.request("Banana");
		tree.request("Banana");

		tree.request("Banana");

		
		System.out.println(tree);
	}
}
