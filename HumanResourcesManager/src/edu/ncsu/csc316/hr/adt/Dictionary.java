package edu.ncsu.csc316.hr.adt;

/**
 * Interface class that represents the Dictionary abstract data type.
 * Note that most of the Dictionary operations were reused from the
 * Dictionary interface class from the CSC316 project 1.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public interface Dictionary<E> {

	/**
	 * Adds an element and its corresponding key to the dictionary.
	 * 
	 * @param k the key to add to the dictionary
	 * @param e the element to add to the dictionary
	 */
	void insert(String k, E e);
	
	/**
	 * Returns the element from the dictionary based on the given key value.
	 * 
	 * @param k the key value to search for
	 * @return the element found or null if the element was not found
	 */
	E lookUp(String k);
	
	/**
	 * Returns the size of the dictionary.
	 * 
	 * @return the size of the dictionary
	 */
	int size();
}

