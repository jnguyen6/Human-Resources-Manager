package edu.ncsu.csc316.hr.list;

import edu.ncsu.csc316.hr.adt.Queue;

/**
 * Class that represents a linked list with a front and a back reference.
 * The LinkedList class is expected to behave like a Queue.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public class LinkedList<E> implements Queue<E> {

	/** The reference to the front of the list. */
	private Node front;
	/** The reference to the back of the list. */
	private Node back;
	/** The size of the linked list. */
	private int size;
	
	/**
	 * Constructor that initializes the front and back fields to null and
	 * the size field to 0.
	 */
	public LinkedList() {
		front = null;
		back = null;
		size = 0;
	}
	
	/**
	 * Adds an element at the end of the linked list. If the element
	 * is null, then a NullPointerException is thrown.
	 * 
	 * @param e the element to add to the linked list
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public void enqueue(E e) {
		if (e == null) {
			throw new NullPointerException("The element to add cannot be null.");
		}
		Node n = new Node(e);
		if (front == null) {
			front = n;
		} else {
			if (size() == 1) {
				front.next = n;
				back = n;
			} else {
				back.next = n;
				back = n;
			}
		}
		size++;
	}

	/**
	 * Removes and returns an element from the beginning of the linked list. 
	 * If the list is empty, then an IllegalArgumentException is thrown.
	 * 
	 * @return the element removed from the beginning of the list
	 * @throws IllegalArgumentException if the list is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new IllegalArgumentException("The list is empty.");
		}
		E removedVal = front.data;
		front = front.next;
		size--;
		return removedVal;
	}

	/**
	 * Returns the element at the beginning of the linked list without
	 * removing it. If the list is empty, then an IllegalArgumentException
	 * is thrown.
	 * 
	 * @return the element at the beginning of the list
	 * @throws IllegalArgumentException if the list is empty
	 */
	@Override
	public E peek() {
		if (isEmpty()) {
			throw new IllegalArgumentException("The list is empty.");
		}
		return front.data;
	}

	/**
	 * Returns the size of the linked list.
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines whether the linked list has any elements or not. If
	 * the list is empty, then the method returns true. Otherwise, the
	 * method returns false.
	 * 
	 * @return true if the linked list is empty or false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * The inner class of the LinkedList class that stores an element in
	 * a node and a reference to the next node in the list. Note that the
	 * inner class is reused from the Node inner class from the CSC316
	 * project 1.
	 * 
	 * @author Jimmy Nguyen
	 */
	private class Node {
		
		/** The element stored in the node. */
		public E data;
		/** The reference to the next node in the list. */
		public Node next;
		
		/**
		 * Constructor that creates an instance of a Node, initializes the
		 * data field to the given parameter value, and initializes the next
		 * field to null.
		 * 
		 * @param data the element to set to the node
		 */
		public Node(E data) {
			this(data, null);
		}
		
		/**
		 * Constructor that creates an instance of a Node and initializes
		 * the data and next fields to the given parameter values.
		 * 
		 * @param data the element to set to the node
		 * @param next the next node reference to set
		 */
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

}
