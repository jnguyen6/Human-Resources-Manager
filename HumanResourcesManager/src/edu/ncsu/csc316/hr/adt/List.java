package edu.ncsu.csc316.hr.adt;

/**
 * Interface class that represents the List abstract data type.
 * Note that the List interface class is reused from the List
 * interface class from CSC316 project 1.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public interface List<E> extends Dictionary<E> {

	/**
	 * Removes and returns an element from the list at a specific index.
	 * 
	 * @param index the index to remove the element at
	 * @return the removed element
	 */
	E remove(int index);
	
	/**
	 * Adds an element at the end of the list.
	 * 
	 * @param e the element to add to the list
	 */
	void add(E e);
	
	/**
	 * Adds an element at a specific index in the list.
	 * 
	 * @param e the element to add to the list
	 * @param index the index to add the element at
	 */
	void add(int index, E e);
	
	/**
	 * Returns the element accessed from the list at a given index.
	 * 
	 * @param index the index to retrieve the element at
	 * @return the element accessed from the list at a given index
	 */
	E get(int index);
	
	/**
	 * Replaces the element in the list with a new element at a specific
	 * index. Returns the element that was replaced.
	 * 
	 * @param index the index to set the element at
	 * @param e the element to set in the list
	 * @return the element that was replaced
	 */
	E set(int index, E e);
}
