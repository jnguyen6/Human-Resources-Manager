package edu.ncsu.csc316.hr.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Resume class.
 * 
 * @author Jimmy Nguyen
 */
public class ResumeTest {

	/**
	 * Tests the Resume constructor.
	 */
	@Test
	public void testResume() {
		Resume r = new Resume("R001", 1, "N");
		assertTrue(r instanceof Resume);
		assertEquals("R001", r.getResumeID());
		assertEquals(1, r.getYearsOfService());
		assertEquals("N", r.getHighestDegree());
	}

	/**
	 * Tests the setResumeID() method.
	 */
	@Test
	public void testSetResumeID() {
		Resume r = new Resume("R001", 1, "N");
		assertEquals("R001", r.getResumeID());
		try {
			r.setResumeID(null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid resume ID.", iae.getMessage());
			assertEquals("R001", r.getResumeID());
		}
		try {
			r.setResumeID("");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid resume ID.", iae.getMessage());
			assertEquals("R001", r.getResumeID());
		}
		r.setResumeID("R002");
		assertEquals("R002", r.getResumeID());
	}

	/**
	 * Tests the setYearsOfService() method.
	 */
	@Test
	public void testSetYearsOfService() {
		Resume r = new Resume("R001", 1, "N");
		assertEquals(1, r.getYearsOfService());
		try {
			r.setYearsOfService(-1);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid number of service years.", iae.getMessage());
			assertEquals(1, r.getYearsOfService());
		}
		r.setYearsOfService(2);
		assertEquals(2, r.getYearsOfService());
	}

	/**
	 * Tests the setHighestDegree() method.
	 */
	@Test
	public void testSetHighestDegree() {
		Resume r = new Resume("R001", 1, "N");
		assertEquals("N", r.getHighestDegree());
		try {
			r.setHighestDegree(null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid degree.", iae.getMessage());
			assertEquals("N", r.getHighestDegree());
		}
		try {
			r.setHighestDegree("");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid degree.", iae.getMessage());
			assertEquals("N", r.getHighestDegree());
		}
		r.setHighestDegree("B");
		assertEquals("B", r.getHighestDegree());
	}

	/**
	 * Tests the toString() method.
	 */
	@Test
	public void testToString() {
		Resume r = new Resume("R001", 1, "N");
		String expected = "R001, 1, N";
		assertEquals(expected, r.toString());
	}
	
	/**
	 * Tests the hashCode() method.
	 */
	@Test
	public void testHashCode() {
		Resume r = new Resume("R001", 1, "N");
		Resume r2 = new Resume("R002", 1, "N");
		Resume r3 = new Resume("R001", 2, "N");
		Resume r4 = new Resume("R001", 1, "B");
		assertTrue(r.hashCode() == r.hashCode());
		assertFalse(r.hashCode() == r2.hashCode());
		assertFalse(r.hashCode() == r3.hashCode());
		assertFalse(r.hashCode() == r4.hashCode());
	}

	/**
	 * Tests the equals() method.
	 */
	@Test
	public void testEqualsObject() {
		Resume r = new Resume("R001", 1, "N");
		Resume r2 = new Resume("R002", 1, "N");
		Resume r3 = new Resume("R001", 2, "N");
		Resume r4 = new Resume("R001", 1, "B");
		assertTrue(r.equals(r));
		assertFalse(r.equals(r2));
		assertFalse(r.equals(r3));
		assertFalse(r.equals(r4));
	}

}
