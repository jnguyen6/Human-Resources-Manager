package edu.ncsu.csc316.hr.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.hr.data.Resume;
import edu.ncsu.csc316.hr.tree.BinarySearchTree;

/**
 * File reader class that reads a given file that contains resume
 * information and returns a collection of resumes from the file.
 * 
 * @author Jimmy Nguyen
 */
public class ResumeReader {

	/**
	 * Reads each input line from the resume information file and adds them to a 
	 * binary search tree. Once all the resume information has been read, the
	 * binary search tree is then returned. If the given file is not valid, 
	 * then a FileNotFoundException is thrown.
	 * 
	 * @param resumeFile the input file containing resume information
	 * @return the binary search tree that contains resume information
	 * @throws FileNotFoundException if the given resume information file
	 * is invalid
	 */
	public static BinarySearchTree<Resume> readResumeInformation(String resumeFile) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(new FileInputStream(resumeFile));
		BinarySearchTree<Resume> resumeTree = new BinarySearchTree<Resume>();
		//Discard header
		fileScanner.nextLine();
		while (fileScanner.hasNextLine()) {
//			String[] resumeInfo = fileScanner.nextLine().split(", ");
			String resumeInfo = fileScanner.nextLine().trim();
			if (!resumeInfo.equals("")) {
				Scanner lineScanner = new Scanner(resumeInfo);
				lineScanner.useDelimiter(", ");
				String resumeID = "";
				int yearsOfService = -1;
				String highestDegree = "";
				if (lineScanner.hasNext()) {
					resumeID = lineScanner.next();
				}
				if (lineScanner.hasNext()) {
					yearsOfService = Integer.parseInt(lineScanner.next());
				}
				if (lineScanner.hasNext()) {
					highestDegree = lineScanner.next();
				}
				Resume r = new Resume(resumeID, yearsOfService, highestDegree);
//				Resume r = new Resume(lineScanner.next(), Integer.parseInt(lineScanner.next()), lineScanner.next());
//				Resume r = new Resume(resumeInfo[0], Integer.parseInt(resumeInfo[1]), resumeInfo[2]);
				resumeTree.insert(r.getResumeID(), r);
				lineScanner.close();
			}
		}
		fileScanner.close();
		return resumeTree;
	}
}
