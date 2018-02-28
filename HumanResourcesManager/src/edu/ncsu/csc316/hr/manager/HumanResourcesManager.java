package edu.ncsu.csc316.hr.manager;

import edu.ncsu.csc316.hr.adt.Dictionary;
import edu.ncsu.csc316.hr.adt.Tree;
import edu.ncsu.csc316.hr.data.Resume;
import edu.ncsu.csc316.hr.data.Employee;

/**
 * Class that allows the user to either create an organizational
 * profile of a set of employees by tier or remove an employee
 * and promote a supervised employee to interim employee.
 * 
 * @author Jimmy Nguyen
 */
public class HumanResourcesManager {

	/** A search tree that contains resume information. */
	private Dictionary<Resume> resumeTree;
	/** A general tree that contains employee information. */
	private Tree<Employee> employeeTree;
	
	/**
	 * Constructs a new HR manager with the given input files
	 * 
	 * @param pathToEmployeeFile
	 *            - the path to the employee input file
	 * @param pathToResumeFile
	 *            - the path to the resume input file
	 */
	public HumanResourcesManager(String pathToEmployeeFile, String pathToResumeFile) {
	    // TODO: Add your code here
	}
	
	/**
	 * Returns a string representation of the interim employee
	 * who replaces the removed employee.
	 * 
	 * @param first - the first name of the employee to remove
	 * @param last - the last name of the employee to remove
	 * @return the name of the employee who was promoted to interim supervisor
	 */
	public String removeEmployee(String first, String last) {
	    // TODO Add your code here
		return null;
	}
	
	/**
	 * Returns the string representation of the organizational
	 * profile of the company using the given input employee file.
	 * 
	 * @return the organizational profile
	 */
	public String generateOrganizationalProfile() {
	    // TODO: Add your code here
		return null;
	}
}
