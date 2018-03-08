package edu.ncsu.csc316.hr.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.hr.io.EmployeeReader;
import edu.ncsu.csc316.hr.io.ResumeReader;

/**
 * Tests the HumanResourcesManager class.
 * 
 * @author Jimmy Nguyen
 */
public class HumanResourcesManagerTest {

	/** The employee information test file. */
	private final String employeeFile = "input/sample.txt";
	/** The second employee information test file. */
	private final String employeeFile2 = "input/sample2.txt";
	/** The resume information test file. */
	private final String resumeFile = "input/sample-resume.txt";
	/** The second resume information test file. */
	private final String resumeFile2 = "input/sample-resume2.txt";
	
	/**
	 * Tests the HumanResourcesManager constructor.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testHumanResourcesManager() {
		EmployeeReader er = new EmployeeReader();
		ResumeReader rr = new ResumeReader();
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
		
		assertEquals("No interim supervisor.",
				hrm.removeEmployee("Kyle", "DeMarcino"));
		expectedOP = "OrganizationalProfile[\n";
		expectedOP += "   Jane Doe\n";
		expectedOP += "   John Smith\n";
		expectedOP += "   Thomas Webb\n";
		expectedOP += "   Suzanne Meadows\n";
		expectedOP += "   Jessica Daniels";
		expectedOP += "\n]";
		assertEquals(expectedOP, hrm.generateOrganizationalProfile());
		expectedR = "R040123346, 1, B R829476581, 1, A, R000634703, 1, M, R228401745, 1, "
				+ "M, R891429182, 1, P, ";
		assertEquals(expectedR, hrm.getResumeTree().elementsInLevelOrder());
		
		hrm = new HumanResourcesManager(employeeFile2, resumeFile2);
		expectedOP = "OrganizationalProfile[\n";
		expectedOP += "   Jimmy Nguyen\n";
		expectedOP += "   John Doe\n";
		expectedOP += "   Jane Smith\n";
		expectedOP += "   Adam Mada\n";
		expectedOP += "   Sam Adams\n";
		expectedOP += "   Mary Poppins\n";
		expectedOP += "   Steve Harvey\n";
		expectedOP += "   Chris Harvey\n";
		expectedOP += "   Chris Harvey";
		expectedOP += "\n]";
		assertEquals(expectedOP, hrm.generateOrganizationalProfile());
		expectedR = "R03, 2, A, R01, 1, N, R05, 2, M R07, 2, M, R00, 1, N, R02, 2, N, R04, "
				+ "2, N, R06, 2, M, R08, 2, M, ";
		assertEquals(expectedR, hrm.getResumeTree().elementsInLevelOrder());
		assertEquals("Chris Harvey", hrm.removeEmployee("Jimmy", "Nguyen"));
		expectedOP = "OrganizationalProfile[\n";
		expectedOP += "   Chris Harvey\n";
		expectedOP += "   John Doe\n";
		expectedOP += "   Jane Smith\n";
		expectedOP += "   Adam Mada\n";
		expectedOP += "   Sam Adams\n";
		expectedOP += "   Mary Poppins\n";
		expectedOP += "   Steve Harvey\n";
		expectedOP += "   Chris Harvey";
		expectedOP += "\n]";
		assertEquals(expectedOP, hrm.generateOrganizationalProfile());
		expectedR = "R05, 2, M, R03, 2, A, R07, 2, M, R01, 1, N R02, 2, N, R04, 2, N, "
				+ "R06, 2, M, R08, 2, M, ";
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
