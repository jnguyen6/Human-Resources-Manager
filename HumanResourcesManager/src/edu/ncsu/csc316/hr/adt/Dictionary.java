package edu.ncsu.csc316.hr.adt;

/**
 * Interface class that represents the Dictionary abstract data type.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public interface Dictionary<E> {

	/**
	 * Adds an element and its corresponding key to the end of the list.
	 * 
	 * @param k the key to add to the list
	 * @param e the element to add to the list
	 */
	void insert(String k, E e);
	
	/**
	 * Returns the element from the list based on the given key value.
	 * 
	 * @param k the key value to search for
	 * @return the element found or null if the element was not found
	 */
	E lookUp(String k);
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return the size of the list
	 */
	int size();
}

