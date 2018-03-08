package edu.ncsu.csc316.hr.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.hr.adt.Dictionary;
import edu.ncsu.csc316.hr.adt.Queue;
import edu.ncsu.csc316.hr.adt.Tree;
import edu.ncsu.csc316.hr.data.Resume;
import edu.ncsu.csc316.hr.io.EmployeeReader;
import edu.ncsu.csc316.hr.io.ResumeReader;
import edu.ncsu.csc316.hr.list.LinkedList;
import edu.ncsu.csc316.hr.tree.BinarySearchTree;
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

	/** A binary search tree that contains resume information. */
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
	    try {
	    	    resumeTree = ResumeReader.readResumeInformation(pathToResumeFile);
	    } catch (FileNotFoundException e) {
	    	    throw new IllegalArgumentException("There was a problem reading the input file.");
	    }
	    buildTree(employeeInfo);
	}
	
	/**
	 * Builds the general tree, in which the shape of the tree is
	 * determined by the ordering specifically defined in the given
	 * list of input lines taken from the employee information file.
	 * 
	 * @param employeeInfo the list representation of each input line
	 * from the employee information file
	 */
	public void buildTree(LinkedList<String> employeeInfo) {
		//Assuming that the given employee info list is correctly formatted,
		//remove the first employee info and assign that as the root of the
		//general tree
		String input = employeeInfo.dequeue();
		String[] info = input.split(",");
		Employee employee = new Employee(info[0].trim(), info[1].trim(), info[2].trim());
		employeeTree.insert(employee.getResumeID(), employee);
		//Now, discard the first "("
		employeeInfo.dequeue();
		appendNodesToTree(((GeneralTree<Employee>) employeeTree).root(), employeeInfo);
	}
	
	/**
	 * An algorithm that helps build the general tree of employee 
	 * information.
	 * 
	 * @param n the current parent node
	 * @param employeeInfo the list representation of each input
	 * line from the employee information file
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void appendNodesToTree(Node n, LinkedList<String> employeeInfo) {
		//The check to whether the employee linked list is empty is to make sure
		//we have an indication of where to end the building tree process.
		Node currentNode = (Node) n;
		while (!employeeInfo.isEmpty()) {
			String input = employeeInfo.dequeue();
			//If "(", then create new list of children specifically for the new parent node.
			//The new parent node would be the most recently added child node of the previous
			//parent node, which should be at the end of the list of children nodes.
			if (input.equals("(")) {
				currentNode = (Node) currentNode.children.get(currentNode.children.size() - 1);
			}
			
			if (input.equals(")")) {
				currentNode = (Node) currentNode.parent;
			}
			//If the input is an employee info, then proceed to process and append to tree.
			if (!input.equals("(") && !input.equals(")")) {
				String[] info = input.split(",");
				Employee employee = new Employee(info[0].trim(), info[1].trim(), info[2].trim());
				((GeneralTree<Employee>) employeeTree).insert(employee.getResumeID(), employee, currentNode);
			}
		}
	}
	
	/**
	 * Returns a string representation of the interim employee
	 * who replaces the removed employee. If the removed employee
	 * does not supervise any other employees, then remove the
	 * employee and return "No interim supervisor"
	 * 
	 * @param first - the first name of the employee to remove
	 * @param last - the last name of the employee to remove
	 * @return the name of the employee who was promoted to interim supervisor
	 */
	public String removeEmployee(String first, String last) {
		String interim = removeEmployee(((GeneralTree<Employee>) employeeTree).root(), first, last);
		//If null is returned, that means that we did not find the employee
		//to remove.
		if (interim == null) {
			return "Employee was not found.";
		}
	    return interim;
	}
	
	/**
	 * A recursive algorithm that helps find the employee to remove, given
	 * the first and last name of the employee. If the employee to remove
	 * is found, then the chooseInterim recursive algorithm is called to
	 * replace the removed employee. Returns the interim employee that
	 * replaces the removed employee, or null if the employee to remove
	 * was not found.
	 * 
	 * @param node the current node to find the employee information
	 * @param first the first name of the employee to remove
	 * @param last the last name of the employee to remove
	 * @return the interim employee information or null if the employee
	 * was not found
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String removeEmployee(Node node, String first, String last) {
		if (((Employee) node.data).getFirstName().equals(first) && 
				((Employee) node.data).getLastName().equals(last)) {
			((BinarySearchTree<Resume>) resumeTree).remove(node.key);
			
			//If we have found the employee to remove, then call chooseInterim
			//recursive method. Doing so should replace the node that previously
			//contains the removed employee info with the interim employee info
			
			//If the node we found has no children, that means that the
			//employee we want to remove does not have any interim
			//employees. Therefore, return "No interim supervisor.
			if (node.children.size() == 0) {
				((GeneralTree<Employee>) employeeTree).remove(node.key, node);
				return "No interim supervisor.";
			}
			chooseInterim(node);
			String interim = ((Employee) node.data).toString();
			return interim;
		} else {
			int min = 0;
			int max = node.children.size() - 1;
			while (min <= max) {
				String interim = removeEmployee((Node) node.children.get(min), first, last);
				if (interim != null) {
					return interim;
				}
				min++;
			}
			return null;
		}
	}
	
	/**
	 * A recursive algorithm that helps determine the interim employee to
	 * replace the removed employee.
	 * 
	 * @param node the current node to find the interim employee for
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void chooseInterim(Node node) { 
		//Node n represents one possible node that has the interim employee
		Node currentNode = (Node) node.children.get(0);
		Resume currentResume = resumeTree.lookUp(((Employee) currentNode.data).getResumeID());
		for (int i = 1; i < node.children.size(); i++) {
			Node otherNode = (Node) node.children.get(i);
			Resume otherResume = resumeTree.lookUp(((Employee) otherNode.data).getResumeID());
			if (currentResume.getYearsOfService() < otherResume.getYearsOfService()) {
				currentNode = otherNode;
				currentResume = otherResume;
			} else if (currentResume.getYearsOfService() == otherResume.getYearsOfService()) {
				if (currentNode.children.size() < otherNode.children.size()) {
					currentNode = otherNode;
					currentResume = otherResume;
				} else if (currentNode.children.size() == otherNode.children.size()) {
					if (currentResume.getHighestDegree().equals("N") && (otherResume.getHighestDegree().equals("A")
						|| otherResume.getHighestDegree().equals("B") || otherResume.getHighestDegree().equals("M"))) {
						currentNode = otherNode;
						currentResume = otherResume;
					} else if (currentResume.getHighestDegree().compareTo(otherResume.getHighestDegree()) < 0
							&& !otherResume.getHighestDegree().equals("N")) {
						currentNode = otherNode;
						currentResume = otherResume;
					} else if (currentResume.getHighestDegree().equals(otherResume.getHighestDegree())) {
						if (((Employee) currentNode.data).getLastName().compareTo(((Employee) otherNode.data).getLastName()) > 0) {
							currentNode = otherNode;
							currentResume = otherResume;
						} else if (((Employee) currentNode.data).getLastName().equals(((Employee) otherNode.data).getLastName()) &&
								((Employee) currentNode.data).getFirstName().compareTo(((Employee) otherNode.data).getFirstName()) > 0) {
								currentNode = otherNode;
								currentResume = otherResume;
						}
					}
				}
			}
		}
		((GeneralTree<Employee>) employeeTree).setElement(currentNode.key, (Employee) currentNode.data, node);
		if (currentNode.children.size() != 0) {
			chooseInterim(currentNode);
		} else {
			((GeneralTree<Employee>) employeeTree).remove(currentNode.key, currentNode);
		}
	}
	
	/**
	 * Returns the string representation of the organizational
	 * profile of the company using the given input employee file.
	 * Note that the level-order traversal algorithm is referenced
	 * from the CSC316 Tree lecture slides on page 22, provided by
	 * Jason King.
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
		sb.append("OrganizationalProfile[");
		while (!queue.isEmpty()) {
			Node currentNode = queue.dequeue();
			sb.append("\n   ");
			sb.append(((GeneralTree<Employee>) employeeTree).getElement(currentNode).toString());
			int num = 0;
			while (num < ((GeneralTree<Employee>) employeeTree).numChildren(currentNode)) {
				queue.enqueue((Node) currentNode.children.get(num));
				num++;
			}
		}
		sb.append("\n]");
		return sb.toString();
	}
	
	/**
	 * Returns the resume tree.
	 * 
	 * @return the resume tree.
	 */
	public BinarySearchTree<Resume> getResumeTree() {
		return (BinarySearchTree<Resume>) resumeTree;
	}
}
