package edu.ncsu.csc316.hr.ui;

import java.util.Scanner;

import edu.ncsu.csc316.hr.manager.HumanResourcesManager;

/**
 * User interface class that reads the input files that contain employee
 * information and resume information and creates a HumanResourcesManager
 * instance. Once the two specific input files are provided, the class
 * then prompts the user to either generate an organizational profile 
 * of the entire set of employees, remove an employee and his or her
 * resume information from the HR system, or quit the program.
 * 
 * @author Jimmy Nguyen
 */
public class HumanResourcesManagerUI {

	/** The instance variable of the human resources manager. */
	private HumanResourcesManager hr;
	
	/**
	 * Method that starts the program. The user will first be prompted to provide
	 * an input file containing employee information. The user will then be prompted
	 * to provide an input file containing resume information. If either one of the
	 * given input files do not exist, then the user will be re-prompted to provide
	 * a new input file. Once two valid input files have been provided, the method
	 * processUserAction() will then be called to continue processing user input.
	 * 
	 * @param args the command-line arguments (not used)
	 */
	public static void main(String[] args) {
		HumanResourcesManagerUI ui = new HumanResourcesManagerUI();
		Scanner console = new Scanner(System.in);
		String employeeFile = "";
		String resumeFile = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print("Enter an input file that contains employee information: ");
			employeeFile = console.nextLine();
			if (!employeeFile.endsWith("txt")) {
				System.out.println("The given employee file is invalid. Please provide "
						+ "the correct file.");
			} else {
				isValid = true;
			}
		}
		isValid = false;
		while (!isValid) {
			System.out.print("Enter an input file that contains resume information: ");
			resumeFile = console.nextLine();
			if (!resumeFile.endsWith("txt")) {
				System.out.println("The given resume file is invalid. Please provide "
						+ "the correct file.");
			} else {
				isValid = true;
			}
		}
		ui.processUserAction(console, employeeFile, resumeFile);
	}
	
	/** 
	 * The user will then be asked to either generate an organizational profile of all
	 * employees by tier, remove an employee from the HR system, or quit the
	 * program. If the user provides invalid input, then the program will close.
	 * 
	 * @param console the Scanner that reads input from the console
	 * @param employeeFile the employee information input file
	 * @param resumeFile the resume information input file
	 */
	public void processUserAction(Scanner console, String employeeFile, String resumeFile) {
		
	}
}
