package myDataStructures;

public class Tower<E extends Comparable<E>>
{
	private ArrayStack<E> topStack;
	private ArrayStack<E> bottomStack;
	
	public boolean drop(E item)
	{
		if(topStack.isEmpty())
		{
			return addBottom(item);
		}
		else
		{
			return addTop(item);
		}
	}
	
	private boolean addBottom(E item)
	{
		switch(bottomStack.peek().compareTo(item))
		{
			case 1:
				topStack.push(item);
				return true;
			case 0:
				bottomStack.push(item);
				return true;
			default:
				return false;
		}
	}
	
	private boolean addTop(E item)
	{
		switch(topStack.peek().compareTo(item))
		{
			case 1:
			case 0:
				topStack.push(item);
				return true;
			default:
				return false;
		}
	}
	
	
}
