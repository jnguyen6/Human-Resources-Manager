package edu.ncsu.csc316.hr.tree;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the GeneralTree class.
 * 
 * @author Jimmy Nguyen
 */
public class GeneralTreeTest {

	/**
	 * Tests the GeneralTree constructor.
	 */
	@Test
	public void testGeneralTree() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertTrue(gt instanceof GeneralTree);
		assertEquals(0, gt.size());
	}

	/**
	 * Tests the getElement() method.
	 */
	@Test
	public void testGetElement() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		gt.insert("fruit", "apple");
		assertEquals(1, gt.size());
		try {
			gt.getElement(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
		}
		assertEquals("apple", gt.getElement(gt.root()));
	}

	/**
	 * Tests the getKey() method.
	 */
	@Test
	public void testGetKey() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		gt.insert("fruit", "apple");
		assertEquals(1, gt.size());
		try {
			gt.getKey(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
		}
		assertEquals("fruit", gt.getKey(gt.root()));
	}

	/**
	 * Tests the root() method.
	 */
	@Test
	public void testRoot() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		gt.insert("fruit", "apple");
		assertEquals("fruit", gt.getKey(gt.root()));
		assertEquals("apple", gt.getElement(gt.root()));
		assertNull(gt.parent(gt.root()));
		assertNull(gt.getChildren(gt.root()));
	}

	/**
	 * Tests the parent() method.
	 */
	@Test
	public void testParent() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		try {
			gt.parent(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(0, gt.size());
		}
		gt.insert("fruit", "apple");
		assertNull(gt.parent(gt.root()));
		gt.insert("vegetable", "cucumber");
		assertEquals("fruit", gt.getKey(gt.parent(gt.getChildren(gt.root()).get(0))));
		assertEquals("apple", gt.getElement(gt.parent(gt.getChildren(gt.root()).get(0))));
		assertEquals(gt.root(), gt.parent(gt.getChildren(gt.root()).get(0)));
	}

	/**
	 * Tests the numChildren() method.
	 */
	@Test
	public void testNumChildren() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		try {
			gt.numChildren(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(0, gt.size());
		}
		gt.insert("fruit", "apple");
		assertEquals(1, gt.size());
		assertEquals(0, gt.numChildren(gt.root()));
		gt.insert("vegetable", "cucumber");
		assertEquals(2, gt.size());
		assertEquals(1, gt.numChildren(gt.root()));
		gt.insert("fruit", "grape");
		assertEquals(3, gt.size());
		assertEquals(2, gt.numChildren(gt.root()));	
	}

	/**
	 * Tests the getChildren() method.
	 */
	@Test
	public void testGetChildren() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		gt.insert("fruit", "apple");
		try {
			gt.getChildren(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		assertNull(gt.getChildren(gt.root()));
		gt.insert("vegetable", "cucumber");
		assertEquals(1, gt.numChildren(gt.root()));
		assertEquals("vegetable", gt.getKey(gt.getChildren(gt.root()).get(0)));
		assertEquals("cucumber", gt.getElement(gt.getChildren(gt.root()).get(0)));
	}

	/**
	 * Tests the insert() method, where the parent node is not a parameter value.
	 */
	@Test
	public void testInsert() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		try {
			gt.insert(null, "apple");
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(0, gt.size());
		}
		try {
			gt.insert("fruit", null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(0, gt.size());
		}
		gt.insert("fruit", "apple");
		assertEquals(1, gt.size());
		assertEquals("fruit", gt.getKey(gt.root()));
		assertEquals("apple", gt.getElement(gt.root()));
		gt.insert("vegetable", "cucumber");
		assertEquals(2, gt.size());
		assertEquals(1, gt.numChildren(gt.root()));
		assertEquals("fruit", gt.getKey(gt.root()));
		assertEquals("apple", gt.getElement(gt.root()));
		assertEquals("vegetable", gt.getKey(gt.getChildren(gt.root()).get(0)));
		assertEquals("cucumber", gt.getElement(gt.getChildren(gt.root()).get(0)));
	}
	
	/**
	 * Tests the insert() method, where the parent node is a given parameter value.
	 */
	@Test
	public void testInsertWithNode() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		assertNull(gt.root());
		gt.insert("fruit", "apple", null);
		assertEquals(1, gt.size());
		assertEquals("fruit", gt.getKey(gt.root()));
		assertEquals("apple", gt.getElement(gt.root()));
		try {
			gt.insert(null, "cucumber", gt.root());
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		try {
			gt.insert("vegetable", null, gt.root());
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		try {
			gt.insert("vegetable", "cucumber", null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		gt.insert("vegetable", "cucumber", gt.root());
		assertEquals(2, gt.size());
		assertEquals(1, gt.numChildren(gt.root()));
		assertEquals("vegetable", gt.getKey(gt.getChildren(gt.root()).get(0)));
		assertEquals("cucumber", gt.getElement(gt.getChildren(gt.root()).get(0)));
		assertEquals(0, gt.numChildren(gt.getChildren(gt.root()).get(0)));
		gt.insert("fruit", "grape", gt.root());
		assertEquals(3, gt.size());
		assertEquals(2, gt.numChildren(gt.root()));
		assertEquals("vegetable", gt.getKey(gt.getChildren(gt.root()).get(0)));
		assertEquals("cucumber", gt.getElement(gt.getChildren(gt.root()).get(0)));
		assertEquals("fruit", gt.getKey(gt.getChildren(gt.root()).get(1)));
		assertEquals("grape", gt.getElement(gt.getChildren(gt.root()).get(1)));
		gt.insert("starch", "corn", gt.root().children.get(0));
		assertEquals(4, gt.size());
		assertEquals(2, gt.numChildren(gt.root()));
		assertEquals(1, gt.numChildren(gt.root().children.get(0)));
		assertEquals(0, gt.numChildren(gt.root().children.get(1)));
		assertEquals("vegetable", gt.getKey(gt.getChildren(gt.root()).get(0)));
		assertEquals("cucumber", gt.getElement(gt.getChildren(gt.root()).get(0)));
		assertEquals("fruit", gt.getKey(gt.getChildren(gt.root()).get(1)));
		assertEquals("grape", gt.getElement(gt.getChildren(gt.root()).get(1)));
		assertEquals("starch", gt.getKey(gt.getChildren(gt.root().children.get(0)).get(0)));
		assertEquals("corn", gt.getElement(gt.getChildren(gt.root().children.get(0)).get(0)));
	}

	/**
	 * Tests the remove() method.
	 */
	@Test
	public void testRemove() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		try {
			gt.remove("fruit");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The tree is empty.", iae.getMessage());
			assertEquals(0, gt.size());
		}
		gt.insert("fruit", "apple");
		assertEquals(1, gt.size());
		assertEquals("fruit", gt.getKey(gt.root()));
		assertEquals("apple", gt.getElement(gt.root()));
		try {
			gt.remove(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		assertEquals("apple", gt.remove("fruit"));
		assertEquals(0, gt.size());
		assertNull(gt.root());
		
		gt.insert("fruit", "apple");
		gt.insert("vegetable", "cucumber");
		assertEquals(2, gt.size());
		assertEquals(1, gt.numChildren(gt.root()));
		assertEquals("vegetable", gt.getKey(gt.getChildren(gt.root()).get(0)));
		gt.insert("starch", "corn");
		assertEquals(3, gt.size());
		assertEquals(2, gt.numChildren(gt.root()));
		assertEquals("starch", gt.getKey(gt.getChildren(gt.root()).get(1)));
		assertEquals("cucumber", gt.remove("vegetable"));
		assertEquals(2, gt.size());
		assertEquals(1, gt.numChildren(gt.root()));
		assertEquals("starch", gt.getKey(gt.getChildren(gt.root()).get(0)));
	}
	
	/**
	 * Tests the setElement() method.
	 */
	@Test
	public void testSetElement() {
		GeneralTree<String> gt = new GeneralTree<String>();
		assertEquals(0, gt.size());
		try {
			gt.setElement("fruit", "apple", null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("The tree is empty.", iae.getMessage());
			assertEquals(0, gt.size());
		}
		gt.insert("fruit", "apple");
		assertEquals(1, gt.size());
		assertEquals("fruit", gt.getKey(gt.root()));
		assertEquals("apple", gt.getElement(gt.root()));
		try {
			gt.setElement(null, "cucumber", gt.root());
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		try {
			gt.setElement("vegetable", null, gt.root());
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		try {
			gt.setElement("vegetable", "cucumber", null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals("The given key/element or node cannot be null.", npe.getMessage());
			assertEquals(1, gt.size());
			assertEquals("fruit", gt.getKey(gt.root()));
			assertEquals("apple", gt.getElement(gt.root()));
		}
		assertEquals("apple", gt.setElement("vegetable", "cucumber", gt.root()));
		assertEquals(1, gt.size());
		assertEquals("vegetable", gt.getKey(gt.root()));
		assertEquals("cucumber", gt.getElement(gt.root()));
	}

}
