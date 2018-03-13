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
	private HumanResourcesManager hrm;
	
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
		hrm = new HumanResourcesManager(employeeFile, resumeFile);
		String input = "";
		System.out.print("Generate Organizational Profile (G), Remove an Employee from the "
				+ "HR System (R), or Quit the Program (Q): ");
		input = console.nextLine();
		while (input.equalsIgnoreCase("G") || input.equalsIgnoreCase("R")) {
			if (input.equalsIgnoreCase("G")) {
				System.out.print("Generating Organizational Profile...\n\n");
				String organizationalProfile = hrm.generateOrganizationalProfile();
				System.out.print(organizationalProfile);
				System.out.print("\n\nThe organizational profile has been sucessfully created.\n\n");
			} else {
				String name = "";
				System.out.print("Please provide the first and last name of the employee to "
					+ "remove from the HR system: ");
				name = console.nextLine();
				Scanner lineScanner = new Scanner(name);
				String interim = hrm.removeEmployee(lineScanner.next(), lineScanner.next());
				if (interim.indexOf("Employee was not found.") != -1) {
					System.out.print("\n" + interim + "\n\n");
				} else {
					System.out.print("Removing employee...\n\n");
					System.out.print("The employee has been removed from the HR system, and the HR system "
							+ "is updated accordingly.\n\n");
					if (interim.equals("No interim supervisor.")) {
						System.out.print(interim + "\n\n");
					} else {
						 System.out.print("The interim employee is: " + interim + "\n\n");
					}
				}
				lineScanner.close();
			}
			System.out.print("Generate Organizational Profile (G), Remove an Employee from the "
					+ "HR system (R), or Quit the Program (Q): ");
			input = console.nextLine();
		}
		System.out.print("\nThe Human Resources Manager program will close.");
		console.close();
		System.exit(1);
	}
}
