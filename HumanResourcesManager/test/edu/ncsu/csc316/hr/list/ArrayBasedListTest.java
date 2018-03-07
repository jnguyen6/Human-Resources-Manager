package edu.ncsu.csc316.hr.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayBasedList class
 * 
 * @author Jimmy Nguyen
 */
public class ArrayBasedListTest {

	/**
	 * Tests the ArrayBasedList constructor.
	 */
	@Test
	public void testArrayBasedList() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		assertTrue(l instanceof ArrayBasedList);
		assertEquals(0, l.size());
	}

	/**
	 * Tests the add() method, where the element is added at the end.
	 */
	@Test
	public void testAddAtEnd() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		assertEquals(0, l.size());
		try {
			l.add(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The element to add cannot be null.", npe.getMessage());
			assertEquals(0, l.size());
		}
		l.add("apple");
		assertEquals(1, l.size());
		assertEquals("apple", l.get(0));
		l.add("grape");
		assertEquals(2, l.size());
		assertEquals("apple", l.get(0));
		assertEquals("grape", l.get(1));
		l.add("orange");
		l.add("cantelope");
		l.add("strawberry");
		l.add("mango");
		l.add("pear");
		l.add("peach");
		l.add("avocado");
		l.add("plum");
		assertEquals(10, l.size());
		assertEquals("plum", l.get(9));
		l.add("dragonfruit");
		assertEquals(11, l.size());
		assertEquals("dragonfruit", l.get(10));
		
	}

	/**
	 * Tests the add() method, where the element is added anywhere on the list.
	 */
	@Test
	public void testAdd() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		try {
			l.add(-1, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The given index is invalid.", e.getMessage());
			assertEquals(0, l.size());
		}
		try {
			l.add(1, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The given index is invalid.", e.getMessage());
			assertEquals(0, l.size());
		}
		try {
			l.add(0, null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The element to add cannot be null.", npe.getMessage());
			assertEquals(0, l.size());
		}
		assertEquals(0, l.size());
		l.add(0, "apple");
		assertEquals(1, l.size());
		assertEquals("apple", l.get(0));
		l.add(0, "grape");
		assertEquals(2, l.size());
		assertEquals("grape", l.get(0));
		assertEquals("apple", l.get(1));
		l.add(1, "pear");
		assertEquals(3, l.size());
		assertEquals("grape", l.get(0));
		assertEquals("pear", l.get(1));
		assertEquals("apple", l.get(2));
		l.add(3, "plum");
		assertEquals(4, l.size());
		assertEquals("grape", l.get(0));
		assertEquals("pear", l.get(1));
		assertEquals("apple", l.get(2));
		assertEquals("plum", l.get(3));
		
		//Since the ArrayBasedList class does not use the insert method from the Dictionary
		//ADT class, it is expected that the calling the insert method does nothing.
		l.insert("A", "apple");
	}

	/**
	 * Tests the size() method.
	 */
	@Test
	public void testSize() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		assertEquals(0, l.size());
		l.add("apple");
		assertEquals(1, l.size());
	}

	/**
	 * Tests the remove() method.
	 */
	@Test
	public void testRemove() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		l.add("apple");
		assertEquals(1, l.size());
		try {
			l.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The given index is invalid.", e.getMessage());
			assertEquals("apple", l.get(0));
			assertEquals(1, l.size());
		}
		try {
			l.remove(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The given index is invalid.", e.getMessage());
			assertEquals("apple", l.get(0));
			assertEquals(1, l.size());
		}
		assertEquals("apple", l.remove(0));
		assertEquals(0, l.size());
		l.add("apple");
		l.add("grape");
		l.add("orange");
		assertEquals("grape", l.remove(1));
		assertEquals(2, l.size());
		assertEquals("orange", l.remove(1));
		assertEquals(1, l.size());
	}

	/**
	 * Tests the set() method.
	 */
	@Test
	public void testSet() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		l.add("apple");
		try {
			l.set(-1, "grape");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The given index is invalid.", e.getMessage());
			assertEquals("apple", l.get(0));
			assertEquals(1, l.size());
		}
		try {
			l.set(1, "grape");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("The given index is invalid.", e.getMessage());
			assertEquals("apple", l.get(0));
			assertEquals(1, l.size());
		}
		try {
			l.set(0, null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The element to set cannot be null.", npe.getMessage());
			assertEquals("apple", l.get(0));
			assertEquals(1, l.size());
		}
		assertEquals("apple", l.set(0, "grape"));
		assertEquals(1, l.size());
		assertEquals("grape", l.get(0));
	}
	
	/**
	 * Tests the lookUp() methods. Note that the lookUp methods
	 * are expected to return null since those methods are left
	 * unimplemented for the HumanResourcesManager program.
	 */
	@Test
	public void testLookUp() {
		ArrayBasedList<String> l = new ArrayBasedList<String>();
		assertEquals(null, l.lookUp("apple"));
	}
}

