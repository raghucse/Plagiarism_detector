package edu.neu.reports;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface maintains the repository of all the performed runs
 */
public interface PlagiarismRunRepository extends JpaRepository<PlagiarismRun, Long> {

	/**
	 * This function is used to search for a particular run
	 * @param runId is the value using which a run is searched for
	 * @return returns the plagiarism run that is found
	 */
	PlagiarismRun findByRunId(int runId);
}