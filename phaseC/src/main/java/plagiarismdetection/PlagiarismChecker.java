package plagiarismdetection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.neu.comparison.ComparisonReport;
import edu.neu.comparison.Strategy;
import edu.neu.models.Submission;
import edu.neu.models.UnsuccessfulReportContent;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.Report;
import edu.neu.reports.ReportService;
import edu.neu.utils.Constants;

public class PlagiarismChecker implements Runnable{
	
	private static final Logger log = LogManager.getLogger(PlagiarismChecker.class);

	// A single plagiarism check is run when a plagiarism check is initiated
	private PlagiarismRun plagiarismRun;
	private Report report;
	private ComparisonReport comparisonReport;
	private Strategy comparisonStrategy;
	private boolean runStarted = false;
	
	private ReportService reportService;
	
	public PlagiarismChecker(PlagiarismRun plagiarismRun, Strategy comparisonStrategy, ReportService reportService) {
		this.plagiarismRun = plagiarismRun;
		this.comparisonStrategy = comparisonStrategy;
		this.reportService = reportService;
		comparisonReport = new ComparisonReport();
	}
	
	@Override
	public void run() {
		try {
			check();
		}
		catch(Exception e) {
			log.error("Error when running plagiarism check for "+plagiarismRun+ " : "+ e.getStackTrace());
			comparisonReport.setReportContent(new UnsuccessfulReportContent());
		}
	}
	
	
	// returns the plagiarism check id assosciated with the run
	public String check() throws IOException {
		String id = "someUniqueIDHereGeneratedBYSingleton";
		if(this.plagiarismRun!=null) {
			log.info("Initiating Plagiarism Check with id : "+id);
			this.report = reportService.createNewEmptyReportWithNameAndOwner(plagiarismRun.getDescription(), plagiarismRun.getUserId());
			runStarted = true;
			this.compareAllSubmissions(this.plagiarismRun);
			dumpReportToTable();
		}
		else {
			log.error(Constants.P_CHECK_ERROR_STRING + " for id : "+id);
			comparisonReport.setReportContent(new UnsuccessfulReportContent());
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
				this.comparisonReport.addAnotherComparisonReport(compareFiles(f1, f2));
			}
		}
	}
	
	public ComparisonReport compareFiles(File f1, File f2) {
		return comparisonStrategy.compare(f1, f2);
	}
	
	public double getReportScore() {
		return this.comparisonReport.getAvgScore();
	}
	
	public double getNumberOfComparisons() {
		return this.comparisonReport.getAvgScore();
	}
	
	public String getReportResult() {
		return this.comparisonReport.getReportContent().getResult();
	}
	
	protected void dumpReportToTable() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos); 
		out.writeObject(comparisonReport.getReportContent());
		out.flush();
		report.setReportFile(bos.toByteArray());
		report.setReportScore(comparisonReport.getAvgScore());
	}
	
	public boolean didRunStart() {
		return runStarted;
	}
	
}
