package myDataStructures;

public interface KWHashMap <K,V>
{
	/**
	 * Returns the value associated with the specific key. 
	 * Returns null  if the key is not present
	 * @param key the key for the value
	 * @return value for key, null if no value
	 */
	public V get(Object key);
	
	/**
	 * Returns true if this table contains 
	 * no key-value mappings
	 * @return true if HashMap is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Associates the specified value with the specified
	 * key.Returns the previous value associated with the
	 * specified key, or null if there was no mapping for 
	 * the key
	 * @param key the key of the entry
	 * @param value the value of the entry
	 * @return previous value for this key, 
	 * null if no previous mapping
	 */
	public V put(K key,V value);
	
	/**
	 * Removes the mapping for this key from this table if
	 * it is present (optional operation). Returns the 
	 * previous value associated with the specified key,
	 * or null if there was no mapping
	 * @param key key of value to remove
	 * @return value removed, null if no value
	 */
	public V remove(Object key);
	
	/**
	 * Returns the size of the table
	 * @return the size
	 */
	public int size();
}
