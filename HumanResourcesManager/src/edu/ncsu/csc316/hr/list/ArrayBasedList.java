package edu.ncsu.csc316.hr.list;

import edu.ncsu.csc316.hr.adt.List;

/**
 * Class that uses an array as the underlying data structure.
 * 
 * @author Jimmy Nguyen
 * @param <E> the generic element type
 */
public class ArrayBasedList<E> implements List<E> {

	/** The initial size of the array list. */
	private static final int SIZE = 10;
	/** The list of generic objects. */
	private E[] list;
	/** The size of the array list. */
	private int size;
	
	/**
	 * Constructor that creates an empty array-based list of generic 
	 * elements, which an initial capacity of 10.
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList() {
		list = (E[]) (new Object[SIZE]);
		size = 0;
	}
	
	/**
	 * Adds an element and its corresponding key at the end of the list. 
	 * Note that this method will not be used in the HumanResourcesManager 
	 * program, so the method will be left unimplemented.
	 * 
	 * @param k the key to add to the end of the array list
	 * @param e the element to add to the end of the array list
	 */
	@Override
	public void insert(String k, E e) {
		//not used
	}
	
	/**
	 * Adds an element at the end of the list. If the list is at maximum 
	 * capacity, then expandArray is called to allow for more room. If the
	 * given element is null, then a NullPointerException is thrown.
	 * 
	 * @param e the element to add to the array list
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public void add(E e) {
		if (size() == list.length) {
			expandArray();
		}
		if (e == null) {
			throw new NullPointerException("The element to add cannot be null.");
		}
		list[size()] = e;
		size++;
	}
	
	/**
	 * Adds an element at a specific index in the list. If the given index is 
	 * invalid, then an IndexOutOfBoundsException is thrown. If the given element
	 * is null, then a NullPointerException is thrown. Note that this method is 
	 * referenced from page 261 of the Data Structures & Algorithm textbook by
	 * Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser.
	 * 
	 * @param index the index to add the element at
	 * @param e the element to add to the list
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("The given index is invalid.");
		}
		if (e == null) {
			throw new NullPointerException("The element to add cannot be null.");
		}
		if (size() == list.length) {
			expandArray();
		}
		for (int i = size() - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}
		list[index] = e;
		size++;
	}

	/**
	 * Returns the element from the list based on the given key value. Note 
	 * that this method is not used for the HumanResourcesManager program, 
	 * so the method is left unimplemented.
	 * 
	 * @param k the key value to search for
	 * @return the element found or null if the element is not found
	 */
	@Override
	public E lookUp(String k) {
		// not used
		return null;
	}

	/**
	 * Returns the size of the array list.
	 * 
	 * @return the size of the array list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes the element from the list at a specific index. If the given 
	 * index is invalid, then an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index the index to the remove the element at
	 * @return the element removed from the list
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException("The given index is invalid.");
		}
		E removedVal = list[index];
		for (int i = index; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size() - 1] = null;
		size--;
		return removedVal;
	}

	
	/**
	 * Creates a new array list with increased size and moves the elements 
	 * from the old list to the newly created list.
	 */
	public void expandArray() {
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) (new Object[list.length * 2]);
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}

	/**
	 * Returns the element retrieved from the list at a specific index. If the 
	 * given index is invalid, then an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index the index to retrieve the element at
	 * @return the element that was accessed from the list
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException("The given index is invalid.");
		}
		return list[index];
	}

	/**
	 * Replaces the element in the list with a new element at a specific index. 
	 * If the given index is invalid, then an IndexOutOfBoundsException is thrown.
	 * If the given element is null, then a NullPointerException is thrown.
	 * Returns the element that was replaced.
	 * 
	 * @param index the index to set the element at
	 * @param e the element to set in the list
	 * @return the element that was replaced
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 * @throws NullPointerException if the given element is null
	 */
	@Override
	public E set(int index, E e) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException("The given index is invalid.");
		}
		if (e == null) {
			throw new NullPointerException("The element to set cannot be null.");
		}
		E replacedVal = list[index];
		list[index] = e;
		return replacedVal;
	}
}

