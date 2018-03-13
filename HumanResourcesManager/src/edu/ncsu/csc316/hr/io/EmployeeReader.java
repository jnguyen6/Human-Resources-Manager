package edu.ncsu.csc316.hr.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.hr.list.LinkedList;

/**
 * File reader class that reads a given file that contains employee
 * information and returns the collection of employees from the file.
 * 
 * @author Jimmy Nguyen
 */
public class EmployeeReader {

	/**
	 * Reads each input line from the employee information file and adds them to a 
	 * linked list that uses a queue implementation. Returns the linked list that
	 * contains all the content of the employee file once the file has been completely
	 * processed. If the given file is not valid, then a FileNotFoundException is thrown.
	 * 
	 * @param employeeFile the input file containing employee information
	 * @return the linked list that contains all the contents of the given
	 * input file as Strings
	 * @throws FileNotFoundException if the given employee information file
	 * is invalid
	 */
	public static LinkedList<String> readEmployeeInformation(String employeeFile) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(new FileInputStream(employeeFile));
		LinkedList<String> employeeInfo = new LinkedList<String>();
		//Essentially, we want to move each input line from the file as a separate element to
		//add to the linked list queue implementation
		while (fileScanner.hasNextLine()) {
			String input = fileScanner.nextLine();
			if (!input.equals("")) {
				employeeInfo.enqueue(input.trim());
			}
		}
		fileScanner.close();
		return employeeInfo;
	}
}
