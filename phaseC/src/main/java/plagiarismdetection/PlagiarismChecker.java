package plagiarismdetection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.neu.comparison.Strategy;
import edu.neu.models.Assignment;
import edu.neu.models.Report;
import edu.neu.models.Submission;
import edu.neu.utils.Constants;

public class PlagiarismChecker {

	// A single plagiarism check is run on an assignment
	private Assignment assignment;
	private Report report;
	private Strategy comparisonStrategy;
	
	public PlagiarismChecker(Assignment assignment, Strategy comparisonStrategy) {
		this.assignment = assignment;
		this.comparisonStrategy = comparisonStrategy;
		this.report = new Report();
	}
	
	// returns the plagiarism check id assosciated with the run
	public String check() {
		String id = "someUniqueIDHereGeneratedBYSingleton";
		if(this.assignment!=null) {
			this.report.appendToResult("Initiating Plagiarism Check with id : "+id);
			this.compareAllSubmissions(this.assignment);
		}
		else {
			this.report.appendToResult(Constants.P_CHECK_ERROR_STRING);
		}
		return id;
	}
	
	private void compareAllSubmissions(Assignment assignment) {
		for(Submission[] submissionPair : getSubmissionPairs(assignment)) {
			compareSubmissions(submissionPair[0], submissionPair[1]);
		}
	}
	
	public List<Submission[]> getSubmissionPairs(Assignment assignment) {
		List<Submission[]> result = new ArrayList<>();
		Submission[] submissions= assignment.getStudentSubmissions().values()
				.toArray(new Submission[assignment.getStudentSubmissions().size()]);
		
		for(int i=0; i<submissions.length-1; i++) {
			for(int j=i+1; j<submissions.length; j++) {
				result.add(new Submission[] {submissions[i], submissions[j]});
			}
		}
		return result;
	}
	
	public void compareSubmissions(Submission submission1, Submission submission2) {
		for(File f1 : submission1.getSource().getFiles()) {
			for(File f2 : submission2.getSource().getFiles()) {
				this.report.appendToResult(compareFiles(f1, f2));
			}
		}
	}
	
	public String compareFiles(File f1, File f2) {
		return comparisonStrategy.compare(f1, f2);
	}
	
	public String getReportResult() {
		return this.report.getResult();
	}
	
}
