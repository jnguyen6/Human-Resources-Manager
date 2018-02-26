package edu.ncsu.csc316.hr.data;

/**
 * Class that holds an employee information. The employee information
 * consists of the employee's first name, last name, and resume ID.
 * 
 * @author Jimmy Nguyen
 */
public class Employee {

	/** The first name of the employee. */
	private String firstName;
	/** The last name of the employee. */
	private String lastName;
	/** The employee's resume ID. */
	private String resumeID;
	
	/**
	 * Constructor that initializes the first name, last name, and resume ID fields
	 * to the given parameter values.
	 * 
	 * @param firstName the first name of the employee
	 * @param lastName the last name of the employee
	 * @param resumeID the employee's resume ID
	 */
	public Employee(String firstName, String lastName, String resumeID) {
		setFirstName(firstName);
		setLastName(lastName);
		setResumeID(resumeID);
	}
	
	/**
	 * Returns the employee's first name.
	 * 
	 * @return the employee's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the employee. If the given first name is null
	 * or an empty String, then an IllegalArgumentException is thrown.
	 * 
	 * @param firstName the first name of the employee to set
	 * @throws IllegalArgumentException if the given first name is null
	 * or an empty String
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name.");
		}
		this.firstName = firstName;
	}
	
	/**
	 * Returns the employee's last name.
	 * 
	 * @return the employee's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name of the employee. If the given lasst name is null
	 * or an empty String, then an IllegalArgumentException is thrown.
	 * 
	 * @param lastName the last name of the employee to seet
	 * @throws IllegalArgumentException if the given last name is null
	 * or an empty String
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name.");
		}
		this.lastName = lastName;
	}
	
	/**
	 * Returns the employee's resume ID.
	 * 
	 * @return the employee's resume ID
	 */
	public String getResumeID() {
		return resumeID;
	}
	
	/**
	 * Sets the employee's resume ID. If the given resume ID is null or
	 * an empty String, then an IllegalArgumentExeption is thrown.
	 * 
	 * @param resumeID the employee's resume ID to set
	 * @throws IllegalArgumentException if the given resume ID is null
	 * or an empty String
	 */
	public void setResumeID(String resumeID) {
		if (resumeID == null || resumeID.equals("")) {
			throw new IllegalArgumentException("Invalid resume ID.");
		}
		this.resumeID = resumeID;
	}
	
	/**
	 * Creates and returns a String representation of the employee. The String
	 * will consist of the employee's first and last name.
	 * 
	 * @return the String representation of the employee
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName);
		sb.append(" ");
		sb.append(lastName);
		return sb.toString();
	}

	/**
	 * Compares two Employee objects to determine if both of them hash to the 
	 * same numerical value.
	 * 
	 * @return the result of comparing the two Employee objects
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((resumeID == null) ? 0 : resumeID.hashCode());
		return result;
	}

	/**
	 * Compares two Employee objects to determine if both of them are equal on 
	 * all fields.
	 * 
	 * @param obj the Employee object used for comparison
	 * @return true if the two Employee objects are equal or false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (resumeID == null) {
			if (other.resumeID != null)
				return false;
		} else if (!resumeID.equals(other.resumeID))
			return false;
		return true;
	}
	
}
