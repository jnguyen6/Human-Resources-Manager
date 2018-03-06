package edu.ncsu.csc316.hr.data;

/**
 * Class that holds the resume information. The resume information consists 
 * of resume ID, the number of service years served and the highest degree 
 * earned.
 * 
 * @author Jimmy Nguyen
 */
public class Resume implements Comparable<Resume> {

	/** The resume ID. */
	private String resumeID;
	/** The number of service years. */
	private int yearsOfService;
	/** The highest degree completed. */
	private String highestDegree;
	
	/**
	 * Constructor that initializes the resume ID, years of service, and
	 * highest degree fields to the given parameter values.
	 * 
	 * @param resumeID the resume ID
	 * @param yearsOfService the number of service years
	 * @param highestDegree the highest degree completed
	 */
	public Resume(String resumeID, int yearsOfService, String highestDegree) {
		setResumeID(resumeID);
		setYearsOfService(yearsOfService);
		setHighestDegree(highestDegree);
	}
	
	/**
	 * Returns the resume ID.
	 * 
	 * @return the resume ID
	 */
	public String getResumeID() {
		return resumeID;
	}
	
	/**
	 * Sets the resume ID. If the given resume ID is null or an empty String,
	 * then an IllegalArgumentException is thrown.
	 * 
	 * @param resumeID the resume ID to set
	 * @throws IllegalArgumentException if the given resume ID is null or
	 * an empty String
	 */
	public void setResumeID(String resumeID) {
		if (resumeID == null || resumeID.equals("")) {
			throw new IllegalArgumentException("Invalid resume ID.");
		}
		this.resumeID = resumeID;
	}
	
	/**
	 * Returns the number of service years.
	 * 
	 * @return the number of service years
	 */
	public int getYearsOfService() {
		return yearsOfService;
	}
	
	/**
	 * Sets the number of service years. If the given number of service years is
	 * negative, then an IllegalArgumentException is thrown.
	 * 
	 * @param yearsOfService the number of service years to set
	 * @throws IllegalArgumentException if the given number of service years is
	 * negative
	 */
	public void setYearsOfService(int yearsOfService) {
		if (yearsOfService < 0) {
			throw new IllegalArgumentException("Invalid number of service years.");
		}
		this.yearsOfService = yearsOfService;
	}
	
	/**
	 * Returns the highest degree completed.
	 * 
	 * @return the highest degree completed
	 */
	public String getHighestDegree() {
		return highestDegree;
	}
	
	/**
	 * Sets the highest degree completed. If the given degree is null or an empty
	 * String, then an IllegalArgumentException is thrown.
	 * 
	 * @param highestDegree the highest degree completed to set
	 * @throws IllegalArgumentException if the given degree is null or an empty
	 * String
	 */
	public void setHighestDegree(String highestDegree) {
		if (highestDegree == null || highestDegree.equals("")) {
			throw new IllegalArgumentException("Invalid degree.");
		}
		this.highestDegree = highestDegree;
	}
	
	/**
	 * Creates and returns a String representation of the resume, which consists of
	 * the resume ID, years of service, and highest degree completed.
	 * 
	 * @return the String representation of the resume
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(resumeID);
		sb.append(", ");
		sb.append(yearsOfService);
		sb.append(", ");
		sb.append(highestDegree);
		return sb.toString();
	}

	/**
	 * Compares two Resume objects to determine if both of them hash to the 
	 * same numerical value.
	 * 
	 * @return the result of comparing the two Resume objects
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((highestDegree == null) ? 0 : highestDegree.hashCode());
		result = prime * result + ((resumeID == null) ? 0 : resumeID.hashCode());
		result = prime * result + yearsOfService;
		return result;
	}

	/**
	 * Compares two Resume objects to determine if both of them are equal on 
	 * all fields.
	 * 
	 * @param obj the Resume object used for comparison
	 * @return true if the two Resume objects are equal or false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resume other = (Resume) obj;
		if (!highestDegree.equals(other.highestDegree))
			return false;
		if (!resumeID.equals(other.resumeID))
			return false;
		if (yearsOfService != other.yearsOfService)
			return false;
		return true;
	}

	/**
	 * Compares this Resume object with the given Resume object based on resume IDs.
	 * If this resume ID is less than, equal to, or greater than the other resume ID,
	 * then a negative value, 0, or a positive value is returned.
	 * 
	 * @param other the given Resume object to compare
	 */
	@Override
	public int compareTo(Resume other) {
		return this.resumeID.compareTo(other.resumeID);
	}
}
