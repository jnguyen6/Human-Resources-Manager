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
	 * linked list that uses a queue implementation. Returns the linked list of
	 * all the content of the file once the file has been completely processed.
	 * If the given file is not valid, then a FileNotFoundException is thrown.
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
			employeeInfo.enqueue(fileScanner.nextLine().trim());
		}
		fileScanner.close();
		return employeeInfo;
	}
}
