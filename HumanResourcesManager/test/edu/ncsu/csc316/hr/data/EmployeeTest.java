package edu.ncsu.csc316.hr.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Employee class.
 * 
 * @author Jimmy Nguyen
 */
public class EmployeeTest {

	/**
	 * Tests the Employee constructor.
	 */
	@Test
	public void testEmployee() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		assertTrue(e instanceof Employee);
		assertEquals("Jimmy", e.getFirstName());
		assertEquals("Nguyen", e.getLastName());
		assertEquals("R001", e.getResumeID());
	}

	/**
	 * Tests the setFirstName() method.
	 */
	@Test
	public void testSetFirstName() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		assertEquals("Jimmy", e.getFirstName());
		try {
			e.setFirstName(null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid first name.", iae.getMessage());
			assertEquals("Jimmy", e.getFirstName());
		}
		try {
			e.setFirstName("");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid first name.", iae.getMessage());
			assertEquals("Jimmy", e.getFirstName());
		}
		e.setFirstName("James");
		assertEquals("James", e.getFirstName());
	}

	/**
	 * Tests the setLastName() method.
	 */
	@Test
	public void testSetLastName() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		assertEquals("Nguyen", e.getLastName());
		try {
			e.setLastName(null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid last name.", iae.getMessage());
			assertEquals("Nguyen", e.getLastName());
		}
		try {
			e.setLastName("");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid last name.", iae.getMessage());
			assertEquals("Nguyen", e.getLastName());
		}
		e.setLastName("James");
		assertEquals("James", e.getLastName());
	}

	/**
	 * Tests the setResumeID() method.
	 */
	@Test
	public void testSetResumeID() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		assertEquals("R001", e.getResumeID());
		try {
			e.setResumeID(null);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid resume ID.", iae.getMessage());
			assertEquals("R001", e.getResumeID());
		}
		try {
			e.setResumeID("");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("Invalid resume ID.", iae.getMessage());
			assertEquals("R001", e.getResumeID());
		}
		e.setResumeID("R002");
		assertEquals("R002", e.getResumeID());
	}

	/**
	 * Tests the toString() method.
	 */
	@Test
	public void testToString() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		String expected = "Jimmy Nguyen";
		assertEquals(expected, e.toString());
	}
	
	/**
	 * Tests the hashCode() method.
	 */
	@Test
	public void testHashCode() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		Employee e2 = new Employee("James", "Nguyen", "R001");
		Employee e3 = new Employee("Jimmy", "Doe", "R001");
		Employee e4 = new Employee("Jimmy", "Nguyen", "R002");
		assertTrue(e.hashCode() == e.hashCode());
		assertFalse(e.hashCode() == e2.hashCode());
		assertFalse(e.hashCode() == e3.hashCode());
		assertFalse(e.hashCode() == e4.hashCode());
	}

	/**
	 * Tests the equals() method.
	 */
	@Test
	public void testEquals() {
		Employee e = new Employee("Jimmy", "Nguyen", "R001");
		Employee e2 = new Employee("James", "Nguyen", "R001");
		Employee e3 = new Employee("Jimmy", "Doe", "R001");
		Employee e4 = new Employee("Jimmy", "Nguyen", "R002");
		assertTrue(e.equals(e));
		assertFalse(e.equals(e2));
		assertFalse(e.equals(e3));
		assertFalse(e.equals(e4));
		assertFalse(e.equals(null));
	}

}
