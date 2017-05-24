package myDataStructures;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashTableChain<K,V> extends AbstractMap<K,V> implements KWHashMap<K,V>
{
	/**** CONSTANTS ****/
	/**
	 * Initial capacity of table array
	 */
	private static final int INITIAL_CAPACITY = 101;
	
	/**
	 * Determines how full this HashTable must be to be rehashed
	 */
	private static final double LOAD_THRESHOLD = 3.0;
	/*******************/
	
	/**** INSTANCE VARIABLES ****/
	private SingleLinkedList<Entry<K,V>>[] table;
	private int numKeys;
	/****************************/
	
	
	/**** CONSTRUCTOR ****/
	@SuppressWarnings("unchecked")
	public HashTableChain()
	{
		table = new SingleLinkedList[INITIAL_CAPACITY];
	}
	/*********************/
	
	/**** METHODS ****/
	/**
	 * Increases the size of the table array and
	 * moves old mappings to their new locations
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		SingleLinkedList<Entry<K,V>>[] temp = table;
		
		//Setting table beforehand ensures calling put
		//will not end in an infinite loop
		table = new SingleLinkedList[2 * table.length + 1];
		numKeys = 0;
		
		for(int counter = 0;counter < temp.length;counter++)
		{
			if(table[counter] != null)
			{
				for(Entry<K,V> item : temp[counter])
				{
					this.put(item.key,item.value);
				}
			}
		}
	}
	
	/**
	 * Returns the value associated with the specific key. 
	 * Returns null if the key is not present.
	 * @param key key of value being returned
	 * @return value mapped by key, null if no mapping
	 */
	@Override
	public V get(Object key) 
	{
		int index = key.hashCode() % table.length;
		if(index < 0)
		{
			index += table.length;
		}
		if(table[index] == null)
		{
			return null;
		}
		
		for(Entry<K,V> nextItem : table[index])
		{
			if(nextItem.key.equals(key))
			{
				return nextItem.value;
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() 
	{
		return this.numKeys == 0;
	}

	/**
	 * Associates the specified value with the specified key.
	 * Returns the previous value associated with the 
	 * specified key, or null if there was no mapping for the key
	 * @param key key of the mapping
	 * @param value value of the mapping
	 * @return value mapped to key previously, null if no prior mapping
	 */
	@Override
	public V put(K key, V value) 
	{
		int index = key.hashCode() % table.length;
		if(index < 0)
		{
			index += table.length;
		}
		if(table[index] == null)
		{
			table[index] = new SingleLinkedList<Entry<K,V>>();
		}
		
		for(Entry<K,V> nextItem : table[index])
		{
			if(nextItem.key.equals(key))
			{
				V oldVal = nextItem.value;
				nextItem.setValue(value);
				return oldVal;
			}
		}
		
		table[index].add(0,new Entry<K,V>(key,value));
		numKeys++;
		if(numKeys > (LOAD_THRESHOLD * table.length))
		{
			rehash();
		}
		return null;
	}

	/**
	 * Removes the mapping for this key from this 
	 * table if it is present (optional operation).
	 * Returns the previous value associated with 
	 * the specified key, or null if there was no mapping
	 * @param key key of value to remove
	 * @return value that was remove, null if no value found
	 */
	@Override
	public V remove(Object key) 
	{
		int index = key.hashCode() % table.length;
		if(index < 0)
		{
			index = index + table.length;
		}
		if(table[index] == null)
		{
			return null;
		}
		for(Entry<K,V> nextItem : table[index])
		{
			if(nextItem.key.equals(key))
			{
				V oldVal = nextItem.value;
				table[index].remove(nextItem);
				numKeys--;
				if(table[index].isEmpty())
				{
					table[index] = null;
				}
				return oldVal;
			}
		}
		return null;
		
	}

	@Override
	public int size() 
	{
		return numKeys;
	}

	
	/**
	 * Gives all the values in this HashTable in
	 * a table format with values written for each
	 * key they have
	 * @return String table of this HashTable
	 */
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		for(int counter = 0;counter < table.length;counter++)
		{
			if(table[counter] != null)
			{
				for(Entry<K,V> item : table[counter])
				{
					result.append(item.toString() + "\n");
				}
			}
		}
		return result.toString();
	}
	
	
	@Override
	public Set<Map.Entry<K, V>> entrySet() 
	{
		return new EntrySet();
	}
	
	/***********************/
	/**** INNER CLASSES ****/
	/***********************/

	/**
	 * Inner Class to hold key value pairs
	 * @author w7262233
	 *
	 * @param <K> key type
	 * @param <V> value type
	 */
	private static class Entry<K,V> implements Map.Entry<K, V>
	{
		private K key;
		private V value;
		
		public Entry(K key,V value)
		{
			this.key = key;
			this.value = value;
		}
		
		public K getKey()
		{
			return this.key;
		}
		
		public V getValue()
		{
			return this.value;
		}
		
		public V setValue(V val)
		{
			V temp = this.value;
			this.value = val;
			return temp;
		}
		
		public String toString()
		{
			return key + " = " + value;
		}
	}
	
	/**
	 * Inner class to implement the set view.
	 * Allows this HashTableChain to be treated
	 * as a set. 
	 * @author w7262233
	 * @since 4/23/2017
	 */
	private class EntrySet extends AbstractSet<Map.Entry<K, V>> 
	{
		/** Return the size of the set. */
		@Override
		public int size() 
		{
			return numKeys;
		}
		
		/** Return an iterator over the set. */
		@Override
		public Iterator<Map.Entry<K, V>> iterator() 
		{
			return new SetIterator();
		}
	}
	
	/**
	 * Inner class to implement the set iterator.
	 * Class to give EntrySet an iterator to work with
	 * this data structure.
	 * @author w7262233
	 * @since 4/23/2017
	 */
	private class SetIterator implements Iterator<Map.Entry<K, V>> 
	{
		int index = 0;
		Iterator<Entry<K, V>> localIterator = null;
		
		//Needed to alter hasNext to not update localIterator so remove method will work
		/**
		 * Determines if a call to next from this SetIterator
		 * will not result in null being returned. 
		 * @return true if calling next will give an element,
		 * 			false if calling next will return null
		 */
		@Override
		public boolean hasNext() 
		{
			if (localIterator != null &&
				localIterator.hasNext()) 
			{
				return true;
			}
			
			//Loops until a next element found or until table end
			int temp = index + 1;
			while (temp < table.length
					&& (table[temp] == null || !table[temp].iterator().hasNext())) 
			{
				temp++;
			}
			
			// If we reach table end then no next found, else there is a next
			return !(temp == table.length);
		}
		
		/**
		 * Returns the next element in the SetIterator. 
		 * If no element exists this method will return null
		 * @return next element, null if element does not exist
		 */
		@Override
		public Map.Entry<K, V> next() 
		{
			if (localIterator != null) 
			{
				if (localIterator.hasNext()) 
				{
					return localIterator.next();
				} 
				else 
				{
					localIterator = null;
					index++;
				}
			}
			while (index < table.length
					&& table[index] == null) 
			{
				index++;
			}
			if (index == table.length)
			{
				return null;
			}
			localIterator = table[index].iterator();
			return localIterator.next();
		}
		
		/**
		 * Removes the last element returned by next(). 
		 * Calling this method before calling next will result
		 * in an IllegalStateException being thrown.
		 * Calling this method after next has returned null
		 * will result in a NoSuchElementException being thrown
		 */
		@Override
		public void remove()
		{
			if(localIterator == null)
			{
				if(index == 0)
				{
					//Next has not been called yet
					throw new IllegalStateException();
				}
				else
				{
					//Last call to next resulted in null
					throw new NoSuchElementException();
				}
			}
			else
			{
				localIterator.remove();
			}
		}
	}
}
