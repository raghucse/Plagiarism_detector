package plagiarismdetection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.neu.comparison.Strategy;
import edu.neu.models.Report;
import edu.neu.models.Submission;
import edu.neu.reports.PlagiarismRun;
import edu.neu.utils.Constants;

public class PlagiarismChecker implements Runnable{
	
	private static final Logger log = LogManager.getLogger(PlagiarismChecker.class);

	// A single plagiarism check is run when a plagiarism check is initiated
	private PlagiarismRun plagiarismRun;
	private Report report;
	private Strategy comparisonStrategy;
	
	public PlagiarismChecker(PlagiarismRun plagiarismRun, Strategy comparisonStrategy) {
		this.plagiarismRun = plagiarismRun;
		this.comparisonStrategy = comparisonStrategy;
		this.report = new Report(); // create a new empty report in the reports table here and get back the id
	}
	
	@Override
	public void run() {
		try {
			String report = check();
			// dump this report to the reports table
		}
		catch(Exception e) {
			log.error("Error when running plagiarism check for "+plagiarismRun+ " : "+ e.getStackTrace());
		}
	}
	
	
	// returns the plagiarism check id assosciated with the run
	public String check() {
		String id = "someUniqueIDHereGeneratedBYSingleton";
		if(this.plagiarismRun!=null) {
			this.report.appendToResult("Initiating Plagiarism Check with id : "+id);
			this.compareAllSubmissions(this.plagiarismRun);
		}
		else {
			this.report.appendToResult(Constants.P_CHECK_ERROR_STRING);
		}
		return id;
	}
	
	private void compareAllSubmissions(PlagiarismRun plagiarismRun) {
		for(Submission[] submissionPair : getSubmissionPairs(plagiarismRun)) {
			compareSubmissions(submissionPair[0], submissionPair[1]);
		}
	}
	
	public List<Submission[]> getSubmissionPairs(PlagiarismRun plagiarismRun) {
		List<Submission[]> result = new ArrayList<>();
		Submission[] submissions= plagiarismRun.getStudentSubmissions()
				.toArray(new Submission[plagiarismRun.getStudentSubmissions().size()]);
		
		for(int i=0; i<submissions.length-1; i++) {
			for(int j=i+1; j<submissions.length; j++) {
				result.add(new Submission[] {submissions[i], submissions[j]});
			}
		}
		return result;
	}
	
	public void compareSubmissions(Submission submission1, Submission submission2) {
		for(File f1 : submission1.getFiles()) {
			for(File f2 : submission2.getFiles()) {
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
