package edu.ncsu.csc316.hr.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the HumanResourcesManager class.
 * 
 * @author Jimmy Nguyen
 */
public class HumanResourcesManagerTest {

	/** The employee information test file. */
	private final String employeeFile = "input/sample.txt";
	/** The resume information test file. */
	private final String resumeFile = "input/sample_resume.txt";
	
	/**
	 * Tests the HumanResourcesManager constructor.
	 */
	@Test
	public void testHumanResourcesManager() {
		HumanResourcesManager hrm = new HumanResourcesManager(employeeFile, resumeFile);
		assertTrue(hrm instanceof HumanResourcesManager);
		try {
			hrm = new HumanResourcesManager("notafile.txt", resumeFile);
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("There was a problem reading the input file.", iae.getMessage());
		}
		//Implement resume reader
//		try {
//			hrm = new HumanResourcesManager(employeeFile, "notafile.txt");
//			fail();
//		} catch (IllegalArgumentException iae) {
//			assertEquals("There was a problem reading the input file.", iae.getMessage());
//		}
	}

	/**
	 * Tests the removeEmployee() method.
	 */
	@Test
	public void testRemoveEmployee() {
		fail("Not yet implemented");
	}

	/**
	 * Tests the generateOrganizationalProfile() method.
	 */
	@Test
	public void testGenerateOrganizationalProfile() {
		HumanResourcesManager hrm = new HumanResourcesManager(employeeFile, resumeFile);
		String expected = "OrganizationalProfile[\n";
		expected += "   Sarah Jones\n";
		expected += "   John Smith\n";
		expected += "   Jane Doe\n";
		expected += "   Suzanne Meadows\n";
		expected += "   Thomas Webb\n";
		expected += "   Jessica Daniels\n";
		expected += "   Kyle DeMarcino";
		expected += "\n]";
		assertEquals(expected, hrm.generateOrganizationalProfile());
	}

}
