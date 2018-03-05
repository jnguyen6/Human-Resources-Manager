package edu.ncsu.csc316.hr.tree;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the BinarySearchTree class.
 * 
 * @author Jimmy Nguyen
 */
public class BinarySearchTreeTest {

	/**
	 * Tests the BinarySearchTree constructor.
	 */
	@Test
	public void testBinarySearchTree() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertTrue(bst instanceof BinarySearchTree);
		assertEquals(0, bst.size());
		assertNull(bst.root());
	}

	/**
	 * Tests the insert() method.
	 */
	@Test
	public void testInsertEE() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertEquals(0, bst.size());
		assertNull(bst.root());
		try {
			bst.insert(null, "apple");
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The key or element to add cannot be null.", npe.getMessage());
			assertEquals(0, bst.size());
			assertNull(bst.root());
		}
		try {
			bst.insert("fruit", null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The key or element to add cannot be null.", npe.getMessage());
			assertEquals(0, bst.size());
			assertNull(bst.root());
		}
		bst.insert("1", "1");
		assertNotNull(bst.root());
		assertEquals(1, bst.size());
		assertEquals("1", bst.lookUp("1"));
		try {
			bst.insert("1", "1");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The given key is a duplicate.", iae.getMessage());
			assertEquals(1, bst.size());
			assertEquals("1", bst.lookUp("1"));
		}
		bst.insert("3", "3");
		assertEquals(2, bst.size());
		assertEquals("3", bst.lookUp("3"));
		String expected = "1 3, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		try {
			bst.insert("1", "1");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The given key is a duplicate.", iae.getMessage());
			assertEquals(2, bst.size());
			assertEquals("1", bst.lookUp("1"));
			assertEquals("3", bst.lookUp("3"));
		}
		try {
			bst.insert("3", "3");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The given key is a duplicate.", iae.getMessage());
			assertEquals(2, bst.size());
			assertEquals("1", bst.lookUp("1"));
			assertEquals("3", bst.lookUp("3"));
		}
		
		bst.insert("2", "2");
		assertEquals(3, bst.size());
		assertEquals("2", bst.lookUp("2"));
		expected = "2, 1, 3, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("9", "9");
		assertEquals(4, bst.size());
		assertEquals("9", bst.lookUp("9"));
		expected = "2, 1, 3 9, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("6", "6");
		assertEquals(5, bst.size());
		assertEquals("6", bst.lookUp("6"));
		expected = "2 6, 1, 3, 9, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("5", "5");
		assertEquals(6, bst.size());
		assertEquals("5", bst.lookUp("5"));
		expected = "2 6, 1, 3 5, 9, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("4", "4");
		assertEquals(7, bst.size());
		assertEquals("4", bst.lookUp("4"));
		expected = "4, 2, 6, 1, 3, 5, 9, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("1", "1");
		assertEquals(1, bst.size());
		assertEquals("1", bst.lookUp("1"));
		
		bst.insert("2", "2");
		assertEquals(2, bst.size());
		assertEquals("2", bst.lookUp("2"));
		expected = "1 2, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("4", "4");
		assertEquals(3, bst.size());
		assertEquals("4", bst.lookUp("4"));
		expected = "2, 1, 4, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("5", "5");
		assertEquals(4, bst.size());
		assertEquals("5", bst.lookUp("5"));
		expected = "2, 1, 4 5, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("6", "6");
		assertEquals(5, bst.size());
		assertEquals("6", bst.lookUp("6"));
		expected = "2 5, 1, 4, 6, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("7", "7");
		assertEquals(6, bst.size());
		assertEquals("7", bst.lookUp("7"));
		expected = "2 5, 1, 4, 6 7, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("8", "8");
		assertEquals(7, bst.size());
		assertEquals("8", bst.lookUp("8"));
		expected = "5, 2, 7, 1, 4, 6, 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("8", "8");
		assertEquals(1, bst.size());
		assertEquals("8", bst.lookUp("8"));
		
		bst.insert("7", "7");
		assertEquals(2, bst.size());
		assertEquals("7", bst.lookUp("7"));
		expected = "7 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("6", "6");
		assertEquals(3, bst.size());
		assertEquals("6", bst.lookUp("6"));
		expected = "7, 6, 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("5", "5");
		assertEquals(4, bst.size());
		assertEquals("5", bst.lookUp("5"));
		expected = "7, 5 6, 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("4", "4");
		assertEquals(5, bst.size());
		assertEquals("4", bst.lookUp("4"));
		expected = "5 7, 4, 6, 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("3", "3");
		assertEquals(6, bst.size());
		assertEquals("3", bst.lookUp("3"));
		expected = "5 7, 3 4, 6, 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst.insert("2", "2");
		assertEquals(7, bst.size());
		assertEquals("2", bst.lookUp("2"));
		expected = "5, 3, 7, 2, 4, 6, 8, ";
		assertEquals(expected, bst.elementsInLevelOrder());
	}

	/**
	 * Tests the remove() method.
	 */
	@Test
	public void testRemove() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertEquals(0, bst.size());
		try {
			bst.remove(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key cannot be null.", npe.getMessage());
			assertEquals(0, bst.size());
		}
		bst.insert("A", "apple");
		assertEquals(1, bst.size());
		String expected = "apple, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		assertNull(bst.remove("B"));
		assertEquals(1, bst.size());
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("apple", bst.remove("A"));
		assertEquals(0, bst.size());
		expected = "";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		//Test fusion
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("M", "mango");
		expected = "mango, banana, xigua, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		assertEquals(3, bst.size());
		assertEquals("xigua", bst.remove("X"));
		assertEquals(2, bst.size());
		expected = "banana mango, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("banana", bst.remove("B"));
		assertEquals(1, bst.size());
		expected = "mango, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(0, bst.size());
		assertEquals("", bst.elementsInLevelOrder());
		
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("M", "mango");
		expected = "mango, banana, xigua, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		assertEquals(3, bst.size());
		assertEquals("banana", bst.remove("B"));
		assertEquals(2, bst.size());
		expected = "mango xigua, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("X"));
		assertEquals(1, bst.size());
		expected = "mango, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(0, bst.size());
		assertEquals("", bst.elementsInLevelOrder());
		
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("M", "mango");
		expected = "mango, banana, xigua, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		assertEquals(3, bst.size());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(2, bst.size());
		expected = "banana xigua, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("banana", bst.remove("B"));
		assertEquals(1, bst.size());
		expected = "xigua, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("X"));
		assertEquals(0, bst.size());
		assertEquals("", bst.elementsInLevelOrder());
		
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		assertEquals("zucchini", bst.remove("Z"));
		assertEquals(6, bst.size());
		expected = "durian mango, banana, grape, xigua yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("banana", bst.remove("B"));
		assertEquals(5, bst.size());
		expected = "mango, durian grape, xigua yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("durian", bst.remove("D"));
		assertEquals(4, bst.size());
		expected = "mango, grape, xigua yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("X"));
		assertEquals(6, bst.size());
		expected = "durian mango, banana, grape, yam zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("grape", bst.remove("G"));
		assertEquals(5, bst.size());
		expected = "durian yam, banana, mango, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("zucchini", bst.remove("Z"));
		assertEquals(4, bst.size());
		expected = "durian, banana, mango yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("banana", bst.remove("B"));
		assertEquals(3, bst.size());
		expected = "mango, durian, yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("yam", bst.remove("Y"));
		assertEquals(6, bst.size());
		expected = "durian mango, banana, grape, xigua zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("banana", bst.remove("B"));
		assertEquals(5, bst.size());
		expected = "mango, durian grape, xigua zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("X"));
		assertEquals(4, bst.size());
		expected = "mango, durian grape, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(3, bst.size());
		expected = "grape, durian, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("banana", bst.remove("B"));
		assertEquals(6, bst.size());
		expected = "mango yam, durian grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("X"));
		assertEquals(5, bst.size());
		expected = "grape yam, durian, mango, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("yam", bst.remove("Y"));
		assertEquals(4, bst.size());
		expected = "grape, durian, mango zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("grape", bst.remove("G"));
		assertEquals(6, bst.size());
		expected = "mango yam, banana durian, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(5, bst.size());
		expected = "durian yam, banana, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("durian", bst.remove("D"));
		assertEquals(6, bst.size());
		expected = "mango yam, banana grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("B", "banana");
		bst.insert("X", "xigua");
		bst.insert("G", "grape");
		bst.insert("D", "durian");
		bst.insert("Y", "yam");
		bst.insert("Z", "zucchini");
		assertEquals(7, bst.size());
		expected = "mango, durian, yam, banana, grape, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(6, bst.size());
		expected = "durian xigua, banana, grape, yam zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("Q", "quince");
		bst.insert("R", "raspberry");
		assertEquals(9, bst.size());
		expected = "mango, elderberry, quince strawberry, banana, isafruit, plum, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("zucchini", bst.remove("Z"));
		assertEquals(8, bst.size());
		expected = "mango, elderberry, quince, banana, isafruit, plum, raspberry strawberry, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("quince", bst.remove("Q"));
		assertEquals(7, bst.size());
		expected = "mango, elderberry, raspberry, banana, isafruit, plum, strawberry, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("Q", "quince");
		bst.insert("R", "raspberry");
		assertEquals(9, bst.size());
		expected = "mango, elderberry, quince strawberry, banana, isafruit, plum, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(8, bst.size());
		expected = "plum, elderberry, strawberry, banana, isafruit, quince raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("Q", "quince");
		bst.insert("R", "raspberry");
		assertEquals(9, bst.size());
		expected = "mango, elderberry, quince strawberry, banana, isafruit, plum, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("elderberry", bst.remove("E"));
		assertEquals(8, bst.size());
		expected = "quince, mango, strawberry, banana isafruit, plum, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("Q", "quince");
		bst.insert("R", "raspberry");
		assertEquals(9, bst.size());
		expected = "mango, elderberry, quince strawberry, banana, isafruit, plum, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("quince", bst.remove("Q"));
		assertEquals(8, bst.size());
		expected = "mango, elderberry, raspberry, banana, isafruit, plum, strawberry zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(7, bst.size());
		expected = "plum, elderberry, strawberry, banana, isafruit, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("Q", "quince");
		bst.insert("R", "raspberry");
		assertEquals(9, bst.size());
		expected = "mango, elderberry, quince strawberry, banana, isafruit, plum, raspberry, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("strawberry", bst.remove("S"));
		assertEquals(8, bst.size());
		expected = "mango, elderberry, quince, banana, isafruit, plum, raspberry zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("C", "cherry");
		bst.insert("D", "date");
		assertEquals(9, bst.size());
		expected = "mango, cherry elderberry, strawberry, banana, date, isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("date", bst.remove("D"));
		assertEquals(8, bst.size());
		expected = "mango, cherry, strawberry, banana, elderberry isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(7, bst.size());
		expected = "cherry plum, banana, elderberry isafruit, strawberry zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("C", "cherry");
		bst.insert("D", "date");
		assertEquals(9, bst.size());
		expected = "mango, cherry elderberry, strawberry, banana, date, isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("M"));
		assertEquals(8, bst.size());
		expected = "elderberry, cherry, plum, banana, date, isafruit, strawberry zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("C", "cherry");
		bst.insert("D", "date");
		assertEquals(9, bst.size());
		expected = "mango, cherry elderberry, strawberry, banana, date, isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("cherry", bst.remove("C"));
		assertEquals(8, bst.size());
		expected = "mango, date, strawberry, banana, elderberry isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("M", "mango");
		bst.insert("E", "elderberry");
		bst.insert("S", "strawberry");
		bst.insert("B", "banana");
		bst.insert("I", "isafruit");
		bst.insert("P", "plum");
		bst.insert("Z", "zucchini");
		bst.insert("C", "cherry");
		bst.insert("D", "date");
		assertEquals(9, bst.size());
		expected = "mango, cherry elderberry, strawberry, banana, date, isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("elderberry", bst.remove("E"));
		assertEquals(8, bst.size());
		expected = "mango, cherry, strawberry, banana, date isafruit, plum, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("Mango", "mango");
		bst.insert("Date", "date");
		bst.insert("Tomato", "tomato");
		bst.insert("Apple", "apple");
		bst.insert("Kale", "kale");
		bst.insert("Quince", "quince");
		bst.insert("Zucchini", "zucchini");
		bst.insert("Cherry", "cherry");
		bst.insert("Banana", "banana");
		bst.insert("Xigua", "xigua");
		bst.insert("Yam", "yam");
		bst.insert("Celery", "celery");
		bst.insert("Clementine", "clementine");
		bst.insert("Turnip", "turnip");
		bst.insert("Ugli", "ugli");
		bst.insert("Squash", "squash");
		bst.insert("Raspberry", "raspberry");
		bst.insert("Lemon", "lemon");
		bst.insert("Lime", "lime");
		assertEquals(19, bst.size());
		expected = "mango, cherry, ugli, banana, date lemon, raspberry tomato, yam, apple, celery, "
				+ "clementine, kale, lime, quince, squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("Mango"));
		assertEquals(18, bst.size());
		expected = "quince, cherry, ugli, banana, date lemon, tomato, yam, apple, celery, "
				+ "clementine, kale, lime, raspberry squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("tomato", bst.remove("Tomato"));
		assertEquals(17, bst.size());
		expected = "quince, cherry, ugli, banana, date lemon, squash, yam, apple, celery, "
				+ "clementine, kale, lime, raspberry, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("squash", bst.remove("Squash"));
		assertEquals(16, bst.size());
		expected = "cherry quince, banana, date lemon, ugli yam, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("zucchini", bst.remove("Zucchini"));
		assertEquals(15, bst.size());
		expected = "cherry quince, banana, date lemon, ugli, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, xigua yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("Xigua"));
		assertEquals(14, bst.size());
		expected = "cherry quince, banana, date lemon, ugli, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("yam", bst.remove("Yam"));
		assertEquals(13, bst.size());
		expected = "cherry quince, banana, date lemon, turnip, apple, celery, clementine, "
				+ "kale, lime, raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		//Above is the configuration to save, for now
		
		assertEquals("quince", bst.remove("Quince"));
		assertEquals(12, bst.size());
		expected = "cherry lemon, banana, date, raspberry, apple, celery, clementine, kale, "
				+ "lime, turnip ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("lemon", bst.remove("Lemon"));
		assertEquals(11, bst.size());
		expected = "cherry lime, banana, date, turnip, apple, celery, clementine, kale, "
				+ "raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		//Above is also the configuration we want to save, for now
		
		assertEquals("date", bst.remove("Date"));
		assertEquals(10, bst.size());
		expected = "cherry, banana, lime turnip, apple, celery, clementine kale, raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("Mango", "mango");
		bst.insert("Date", "date");
		bst.insert("Tomato", "tomato");
		bst.insert("Apple", "apple");
		bst.insert("Kale", "kale");
		bst.insert("Quince", "quince");
		bst.insert("Zucchini", "zucchini");
		bst.insert("Cherry", "cherry");
		bst.insert("Banana", "banana");
		bst.insert("Xigua", "xigua");
		bst.insert("Yam", "yam");
		bst.insert("Celery", "celery");
		bst.insert("Clementine", "clementine");
		bst.insert("Turnip", "turnip");
		bst.insert("Ugli", "ugli");
		bst.insert("Squash", "squash");
		bst.insert("Raspberry", "raspberry");
		bst.insert("Lemon", "lemon");
		bst.insert("Lime", "lime");
		assertEquals(19, bst.size());
		expected = "mango, cherry, ugli, banana, date lemon, raspberry tomato, yam, apple, celery, "
				+ "clementine, kale, lime, quince, squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("Mango"));
		assertEquals(18, bst.size());
		expected = "quince, cherry, ugli, banana, date lemon, tomato, yam, apple, celery, "
				+ "clementine, kale, lime, raspberry squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("tomato", bst.remove("Tomato"));
		assertEquals(17, bst.size());
		expected = "quince, cherry, ugli, banana, date lemon, squash, yam, apple, celery, "
				+ "clementine, kale, lime, raspberry, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("squash", bst.remove("Squash"));
		assertEquals(16, bst.size());
		expected = "cherry quince, banana, date lemon, ugli yam, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("zucchini", bst.remove("Zucchini"));
		assertEquals(15, bst.size());
		expected = "cherry quince, banana, date lemon, ugli, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, xigua yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("Xigua"));
		assertEquals(14, bst.size());
		expected = "cherry quince, banana, date lemon, ugli, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("yam", bst.remove("Yam"));
		assertEquals(13, bst.size());
		expected = "cherry quince, banana, date lemon, turnip, apple, celery, clementine, "
				+ "kale, lime, raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("quince", bst.remove("Quince"));
		assertEquals(12, bst.size());
		expected = "cherry lemon, banana, date, raspberry, apple, celery, clementine, kale, "
				+ "lime, turnip ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("lemon", bst.remove("Lemon"));
		assertEquals(11, bst.size());
		expected = "cherry lime, banana, date, turnip, apple, celery, clementine, kale, "
				+ "raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("turnip", bst.remove("Turnip"));
		assertEquals(10, bst.size());
		expected = "cherry, banana, date lime, apple, celery, clementine, kale, raspberry ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("Mango", "mango");
		bst.insert("Date", "date");
		bst.insert("Tomato", "tomato");
		bst.insert("Apple", "apple");
		bst.insert("Kale", "kale");
		bst.insert("Quince", "quince");
		bst.insert("Zucchini", "zucchini");
		bst.insert("Cherry", "cherry");
		bst.insert("Banana", "banana");
		bst.insert("Xigua", "xigua");
		bst.insert("Yam", "yam");
		bst.insert("Celery", "celery");
		bst.insert("Clementine", "clementine");
		bst.insert("Turnip", "turnip");
		bst.insert("Ugli", "ugli");
		bst.insert("Squash", "squash");
		bst.insert("Raspberry", "raspberry");
		bst.insert("Lemon", "lemon");
		bst.insert("Lime", "lime");
		assertEquals(19, bst.size());
		expected = "mango, cherry, ugli, banana, date lemon, raspberry tomato, yam, apple, celery, "
				+ "clementine, kale, lime, quince, squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("mango", bst.remove("Mango"));
		assertEquals(18, bst.size());
		expected = "quince, cherry, ugli, banana, date lemon, tomato, yam, apple, celery, "
				+ "clementine, kale, lime, raspberry squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("tomato", bst.remove("Tomato"));
		assertEquals(17, bst.size());
		expected = "quince, cherry, ugli, banana, date lemon, squash, yam, apple, celery, "
				+ "clementine, kale, lime, raspberry, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("squash", bst.remove("Squash"));
		assertEquals(16, bst.size());
		expected = "cherry quince, banana, date lemon, ugli yam, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("zucchini", bst.remove("Zucchini"));
		assertEquals(15, bst.size());
		expected = "cherry quince, banana, date lemon, ugli, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, xigua yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("xigua", bst.remove("Xigua"));
		assertEquals(14, bst.size());
		expected = "cherry quince, banana, date lemon, ugli, apple, celery, clementine, "
				+ "kale, lime, raspberry turnip, yam, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("yam", bst.remove("Yam"));
		assertEquals(13, bst.size());
		expected = "cherry quince, banana, date lemon, turnip, apple, celery, clementine, "
				+ "kale, lime, raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());

		assertEquals("quince", bst.remove("Quince"));
		assertEquals(12, bst.size());
		expected = "cherry lemon, banana, date, raspberry, apple, celery, clementine, kale, "
				+ "lime, turnip ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("lemon", bst.remove("Lemon"));
		assertEquals(11, bst.size());
		expected = "cherry lime, banana, date, turnip, apple, celery, clementine, kale, "
				+ "raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());

		assertEquals("cherry", bst.remove("Cherry"));
		assertEquals(10, bst.size());
		expected = "clementine, banana, lime turnip, apple, celery, date kale, raspberry, ugli, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		bst = new BinarySearchTree<String>();
		bst.insert("Mango", "mango");
		bst.insert("Date", "date");
		bst.insert("Tomato", "tomato");
		bst.insert("Apple", "apple");
		bst.insert("Kale", "kale");
		bst.insert("Quince", "quince");
		bst.insert("Zucchini", "zucchini");
		bst.insert("Cherry", "cherry");
		bst.insert("Banana", "banana");
		bst.insert("Xigua", "xigua");
		bst.insert("Yam", "yam");
		bst.insert("Celery", "celery");
		bst.insert("Clementine", "clementine");
		bst.insert("Turnip", "turnip");
		bst.insert("Ugli", "ugli");
		bst.insert("Squash", "squash");
		bst.insert("Raspberry", "raspberry");
		bst.insert("Lemon", "lemon");
		bst.insert("Lime", "lime");
		bst.insert("Kelp", "kelp");
		assertEquals(20, bst.size());
		expected = "mango, cherry, ugli, banana, date lemon, raspberry tomato, yam, apple, celery, "
				+ "clementine, kale kelp, lime, quince, squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
		
		assertEquals("clementine", bst.remove("Clementine"));
		assertEquals(19, bst.size());
		expected = "mango, cherry, ugli, banana, kale lemon, raspberry tomato, yam, apple, celery, "
				+ "date, kelp, lime, quince, squash, turnip, xigua, zucchini, ";
		assertEquals(expected, bst.elementsInLevelOrder());
	}

	/**
	 * Tests the isEmpty() method.
	 */
	@Test
	public void testIsEmpty() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertTrue(bst.isEmpty());
		bst.insert("fruit", "apple");
		assertFalse(bst.isEmpty());
	}

	/**
	 * Tests the lookUp() method.
	 */
	@Test
	public void testLookUpE() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertNull(bst.lookUp("fruit"));
		try {
			bst.lookUp(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key cannot be null.", npe.getMessage());
		}
		bst.insert("fruit", "apple");
		assertEquals("apple", bst.lookUp("fruit"));
		assertNull(bst.lookUp("vegetable"));
		bst.insert("vegetable", "cucumber");
		assertEquals("cucumber", bst.lookUp("vegetable"));
	}

	/**
	 * Tests the isLeaf() method.
	 */
	@Test
	public void testIsLeaf() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertEquals(0, bst.size());
		try {
			bst.isLeaf(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(0, bst.size());
		}
		bst.insert("fruit", "apple");
		assertTrue(bst.isLeaf(bst.root()));
		bst.insert("vegetable", "cucumber");
		assertTrue(bst.isLeaf(bst.root()));
		bst.insert("starch", "corn");
		assertFalse(bst.isLeaf(bst.root()));
	}

	/**
	 * Tests the isRoot() method.
	 */
	@Test
	public void testIsRoot() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		assertEquals(0, bst.size());
		try {
			bst.isRoot(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(0, bst.size());
		}
		bst.insert("fruit", "apple");
		assertTrue(bst.isRoot(bst.root()));
	}

}
