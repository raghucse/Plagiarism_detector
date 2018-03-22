package edu.neu.testResources;

import java.util.ArrayList;
import java.util.List;

import edu.neu.models.Submission;
import edu.neu.reports.PlagiarismRun;

public class MockPlagiarismRun extends PlagiarismRun{

	List<Submission> submissions = new ArrayList<Submission>();

	public List<Submission> getStudentSubmissions() {
		return this.submissions;
	}
	
	public void setStudentSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}
	
	public void addStudentSubmission(Submission submission) {
		this.submissions.add(submission);
	}
	
}
