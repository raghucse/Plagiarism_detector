package edu.neu.models;

/**
 * Interface of a faculty representing a different role than other users in the system
 * A Faculty is an actor in the system.
 * Faculties could run plagiarism checks and view reports
 * @author Team 106
 * @version 1.0
 */
public interface Faculty extends SystemUser {
	
	/**
	 * Initiates a plagiarism check for the particular assignment
	 * The ideal implementation of this methdo would spawn a thread and
	 * work asynchronously
	 * A plagiarism check should generate a report
	 * @param assignment
	 */
	void runPlagiarismChecker(Assignment assignment);

	/**
	 * Returns the JSON Object representation for a report
	 * @param report : The report which needs to be viewed
	 * @return : The JSON object if report is found, else an empty error object.
	 */
	String viewReport (Report report);
}
