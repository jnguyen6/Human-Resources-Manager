package edu.ncsu.csc316.hr.adt;

import java.util.Comparator;

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
	void insert(E k, E e);
	
	/**
	 * Returns the element from the list based on the given key value.
	 * 
	 * @param k the key value to search for
	 * @return the element found or null if the element was not found
	 */
	E lookUp(E k);
	
	/**
	 * Returns the element from the list based on the given key value and
	 * the specific Comparator class.
	 * 
	 * @param k the key value to search for
	 * @param c the Comparator used for comparison
	 * @return the element found or null if the element was not found
	 */
	E lookUp(E k, Comparator<E> c);
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return the size of the list
	 */
	int size();
}

