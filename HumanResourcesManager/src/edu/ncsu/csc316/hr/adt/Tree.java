package edu.ncsu.csc316.hr.adt;

/**
 * Interface class that represents the Tree ADT.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public interface Tree<E> {
	
	/**
	 * Adds an element and its corresponding key to the tree.
	 * 
	 * @param k the key to add to the tree
	 * @param e the element to add to the tree
	 */
	void insert(String k, E e);
	
	/**
	 * Removes and returns an element from the tree, given a key.
	 * 
	 * @param k the key to remove the element from
	 * @return the removed element
	 */
	E remove(String k);
	
	/**
	 * Returns the size of the tree.
	 * 
	 * @return the size of the tree
	 */
	int size();
	
	/**
	 * Determines if the tree is empty. Returns true if the tree is empty
	 * or false otherwise.
	 * 
	 * @return true if the tree is empty or false otherwise
	 */
	boolean isEmpty();
}
