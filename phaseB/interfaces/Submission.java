package plagiarismdetector;

/**
 * The Submission interface which dictates a submission made by the student
 * Any submission must have the source, student and assignment assosciated with it 
 * @author Team 106
 * @version 1.0
 */
public interface Submission {
	
	/**
	 * Returns the source assosciated with the submission
	 * @return : The source
	 */
	Source getSource();

	/**
	 * Returns the student whose submission it is
	 * @return : The student
	 */
	Student getStudent();

	/**
	 * Returns the assignment for which the student has submitted
	 * @return : The assignment
	 */
	Assignment getAssignment();

}
