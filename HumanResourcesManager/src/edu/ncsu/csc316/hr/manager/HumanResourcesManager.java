package edu.ncsu.csc316.hr.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.hr.adt.Dictionary;
import edu.ncsu.csc316.hr.adt.Queue;
import edu.ncsu.csc316.hr.adt.Tree;
import edu.ncsu.csc316.hr.data.Resume;
import edu.ncsu.csc316.hr.io.EmployeeReader;
import edu.ncsu.csc316.hr.list.LinkedList;
import edu.ncsu.csc316.hr.tree.GeneralTree;
import edu.ncsu.csc316.hr.tree.GeneralTree.Node;
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
		employeeTree = new GeneralTree<Employee>();
		LinkedList<String> employeeInfo = null;
	    try {
			employeeInfo = EmployeeReader.readEmployeeInformation(pathToEmployeeFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("There was a problem reading the input file.");
		}
	    buildTree(employeeInfo);
	}
	
	/**
	 * Builds the general tree, in which the shape of the tree is determined by
	 * the ordering specifically defined in the given list of input lines taken
	 * from the employee information file.
	 * 
	 * @param employeeInfo the list representation of each input line from the
	 * employee information file
	 */
	public void buildTree(LinkedList<String> employeeInfo) {
		//Assumming that the given employee info list is correctly formatted,
		//remove the first employee info and assign that as the root of the
		//general tree
		String input = employeeInfo.dequeue();
		String[] info = input.split(" ");
		Employee employee = new Employee(info[0], info[1], info[2]);
		employeeTree.insert(employee, employee);
		//Now, discard the first "("
		employeeInfo.dequeue();
		appendNodesToTree(((GeneralTree<Employee>) employeeTree).root(), employeeInfo);
	}
	
	/**
	 * A recursive algorithm that helps build the general tree of employee 
	 * information.
	 * 
	 * @param n the current parent node
	 * @param employeeInfo the list representation of each input line from the
	 * employee information file
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void appendNodesToTree(Node n, LinkedList<String> employeeInfo) {
		//The check to whether the employee linked list is empty is to make sure
		//we have an indication of where to end the building tree process.
		if (!employeeInfo.isEmpty()) {
			String input = employeeInfo.dequeue();
			//If "(", then create new list of children specifically for the new parent node.
			//The new parent node would be the most recently added child node of the previous
			//parent node, which should be at the end of the list of children nodes.
			if (input.equals("(")) {
				Node parentNode = (Node) n.children.get(n.children.size() - 1);
				appendNodesToTree(parentNode, employeeInfo);
			}
			//If ")", then call appendNodesToTree, with the parent of the current parent node.
			if (input.equals(")")) {
				Node parentNode = (Node) n.parent;
				appendNodesToTree(parentNode, employeeInfo);
			}
			//If the input is an employee info, then proceed to process and append to tree.
			if (!input.equals("(") && !input.equals(")")) {
				String[] info = input.split(" ");
				Employee employee = new Employee(info[0], info[1], info[2]);
				((GeneralTree<Employee>) employeeTree).insert(employee, employee, n);
				appendNodesToTree(n, employeeInfo);
			}
		}
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
	 * Note that the level-order traversal algorithm is referenced
	 * from the CSC316 Tree lecture slides on page 22.
	 * 
	 * @return the organizational profile
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String generateOrganizationalProfile() {
		//To print a set of employees by tier, we need a queue that performs
		//a level-order traversal
		Queue<Node> queue = new LinkedList<Node>();
		if (((GeneralTree<Employee>) employeeTree).root() != null) {
	    		queue.enqueue(((GeneralTree<Employee>) employeeTree).root());
	    }
		StringBuilder sb = new StringBuilder();
		sb.append("OrganizationalProfile[\n");
		while (!queue.isEmpty()) {
			Node currentNode = queue.dequeue();
			sb.append("   ");
			sb.append(((GeneralTree<Employee>) employeeTree).getElement(currentNode).toString());
			sb.append("\n");
			int num = 0;
			while (num < ((GeneralTree<Employee>) employeeTree).numChildren(currentNode)) {
				queue.enqueue((Node) currentNode.children.get(num));
			}
		}
		sb.append("\n]");
		return sb.toString();
	}
}
