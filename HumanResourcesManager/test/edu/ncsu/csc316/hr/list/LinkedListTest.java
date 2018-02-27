package edu.ncsu.csc316.hr.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the LinkedList class.
 * 
 * @author Jimmy Nguyen
 */
public class LinkedListTest {

	/**
	 * Tests the LinkedList constructor.
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> l = new LinkedList<String>();
		assertTrue(l instanceof LinkedList);
		assertEquals(0, l.size());
	}

	/**
	 * Tests the enqueue() method.
	 */
	@Test
	public void testEnqueue() {
		LinkedList<String> l = new LinkedList<String>();
		assertEquals(0, l.size());
		try {
			l.enqueue(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The element to add cannot be null.", npe.getMessage());
			assertEquals(0, l.size());
		}
		l.enqueue("apple");
		assertEquals(1, l.size());
		assertEquals("apple", l.peek());
		l.enqueue("orange");
		assertEquals(2, l.size());
		assertEquals("apple", l.peek());
		assertEquals("apple", l.dequeue());
		assertEquals("orange", l.peek());
		assertEquals(1, l.size());
	}

	/**
	 * Tests the dequeue() method.
	 */
	@Test
	public void testDequeue() {
		LinkedList<String> l = new LinkedList<String>();
		assertEquals(0, l.size());
		l.enqueue("apple");
		assertEquals(1, l.size());
		l.dequeue();
		assertEquals(0, l.size());
		l.enqueue("apple");
		assertEquals(1, l.size());
		l.enqueue("orange");
		assertEquals(2, l.size());
		l.enqueue("kiwi");
		assertEquals(3, l.size());
		l.dequeue();
		assertEquals(2, l.size());
		l.dequeue();
		assertEquals(1, l.size());
		l.dequeue();
		assertEquals(0, l.size());
		try {
			l.dequeue();
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The list is empty.", iae.getMessage());
			assertEquals(0, l.size());
		}
	}

	/**
	 * Tests the peek() method.
	 */
	@Test
	public void testPeek() {
		LinkedList<String> l = new LinkedList<String>();
		try {
			l.peek();
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The list is empty.", iae.getMessage());
			assertEquals(0, l.size());
		}
		l.enqueue("apple");
		assertEquals("apple", l.peek());
	}

	/**
	 * Tests the isEmpty() method.
	 */
	@Test
	public void testIsEmpty() {
		LinkedList<String> l = new LinkedList<String>();
		assertTrue(l.isEmpty());
		l.enqueue("apple");
		assertFalse(l.isEmpty());
		l.dequeue();
		assertTrue(l.isEmpty());
	}

}
