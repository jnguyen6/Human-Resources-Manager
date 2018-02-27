package edu.ncsu.csc316.hr.adt;

/**
 * Interface class that represents the Tree ADT.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public interface Tree<E> {

	/**
	 * Returns the element stored in the node or position.
	 * 
	 * @return the element stored in the node or position
	 */
	E getElement();
	
	/**
	 * Returns the root of the tree.
	 * 
	 * @return the root of the tree
	 */
	E root();
	
	/**
	 * Returns the parent of the given node or position.
	 * 
	 * @param n the node or position to find the parent for
	 * @return the parent of the given node or position
	 */
	E parent(E n);
	
	/**
	 * Returns the collection of children nodes or positions of
	 * a given node or position.
	 * 
	 * @param n the node or position to find the children for
	 * @return the collection of children nodes or positions
	 */
	
	/**
	 * Returns the number of children of the given node or position.
	 * 
	 * @param n the node or position to find the number of children for
	 * @return the number of children of the given node or position
	 */
	int numChildren(E n);
}
