package edu.ncsu.csc316.hr.adt;

/**
 * Interface class that represents the Queue ADT.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public interface Queue<E> {

	/**
	 * Adds an element at the end of the queue.
	 * 
	 * @param e the element to add
	 */
	void enqueue(E e);
	
	/**
	 * Removes and returns an element at the beginning of the queue.
	 * 
	 * @return the element removed at the beginning of the queue
	 */
	E dequeue();
	
	/**
	 * Returns the element at the beginning of the queue without
	 * removing it.
	 * 
	 * @return the element at the beginning of the queue
	 */
	E peek();
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return the number of elements in the queue
	 */
	int size();
	
	/**
	 * Determines whether the queue has any elements or not. If the queue 
	 * is empty, then the method returns true. Otherwise, the method returns
	 * false.
	 * 
	 * @return true if the queue is empty or false otherwise
	 */
	boolean isEmpty();
}
