package edu.neu.models;

import java.io.File;
import java.util.List;

/**
 * This interface determines the list of submissions
 */
public interface Submission {

	/**
	 * @return Returns the student name to whom the submission is associated
	 */
	public String getStudentName();
	
	/**
	 * @return Returns the list of files that are submitted
	 */
	public List<File> getFiles();
	
}
