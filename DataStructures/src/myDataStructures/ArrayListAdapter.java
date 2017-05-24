package myDataStructures;

import java.util.Collection;
import java.util.List;

/**
 * Adapter that creates stubs for all methods in List that contain
 * Collection parameters
 * @author Isaac Earnhart
 * @since 2/27/2017
 * @param <E> generic placeholder
 */

public abstract class ArrayListAdapter<E> implements List<E>
{
	@Override
	public boolean addAll(Collection<? extends E> c) 
	{
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) 
	{
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) 
	{
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		return false;
	}

}
