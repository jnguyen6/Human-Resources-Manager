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
	private final String resumeFile = "input/sample-resume.txt";
	
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
		try {
			hrm = new HumanResourcesManager(employeeFile, "notafile.txt");
			fail();
		} catch (IllegalArgumentException iae) {
			assertEquals("There was a problem reading the input file.", iae.getMessage());
		}
		String expected = "R123582991, 1, B R228401745, 1, M, R000634703, 1, M R040123346, 1, B, "
				+ "R200581294, 1, N, R829476581, 1, A R891429182, 1, P, ";
		assertEquals(expected, hrm.getResumeTree().elementsInLevelOrder());
	}

	/**
	 * Tests the removeEmployee() method.
	 */
	@Test
	public void testRemoveEmployee() {
		HumanResourcesManager hrm = new HumanResourcesManager(employeeFile, resumeFile);
		String expectedOP = "OrganizationalProfile[\n";
		expectedOP += "   Sarah Jones\n";
		expectedOP += "   John Smith\n";
		expectedOP += "   Jane Doe\n";
		expectedOP += "   Suzanne Meadows\n";
		expectedOP += "   Thomas Webb\n";
		expectedOP += "   Jessica Daniels\n";
		expectedOP += "   Kyle DeMarcino";
		expectedOP += "\n]";
		assertEquals(expectedOP, hrm.generateOrganizationalProfile());
		
		String expectedR = "R123582991, 1, B R228401745, 1, M, R000634703, 1, M R040123346, 1, B, "
				+ "R200581294, 1, N, R829476581, 1, A R891429182, 1, P, ";
		assertEquals(expectedR, hrm.getResumeTree().elementsInLevelOrder());
		
		assertEquals("Jane Doe", hrm.removeEmployee("Sarah", "Jones"));
		expectedOP = "OrganizationalProfile[\n";
		expectedOP += "   Jane Doe\n";
		expectedOP += "   John Smith\n";
		expectedOP += "   Thomas Webb\n";
		expectedOP += "   Suzanne Meadows\n";
		expectedOP += "   Jessica Daniels\n";
		expectedOP += "   Kyle DeMarcino";
		expectedOP += "\n]";
		assertEquals(expectedOP, hrm.generateOrganizationalProfile());
		expectedR = "R040123346, 1, B R228401745, 1, M, R000634703, 1, M, R123582991, 1, B, "
				+ "R829476581, 1, A R891429182, 1, P, ";
		assertEquals(expectedR, hrm.getResumeTree().elementsInLevelOrder());
		
		assertEquals("Employee was not found.", hrm.removeEmployee("Jimmy", "Nguyen"));
		assertEquals(expectedOP, hrm.generateOrganizationalProfile());
		assertEquals(expectedR, hrm.getResumeTree().elementsInLevelOrder());
		
		assertEquals("The employee Kyle DeMarcino had no employees to supervise.", hrm.removeEmployee("Kyle", "DeMarcino"));
		expectedOP = "OrganizationalProfile[\n";
		expectedOP += "   Jane Doe\n";
		expectedOP += "   John Smith\n";
		expectedOP += "   Thomas Webb\n";
		expectedOP += "   Suzanne Meadows\n";
		expectedOP += "   Jessica Daniels";
		expectedOP += "\n]";
		expectedR = "R040123346, 1, B R829476581, 1, A, R000634703, 1, M, R228401745, 1, M, R891429182, 1, P, ";
		assertEquals(expectedR, hrm.getResumeTree().elementsInLevelOrder());
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
