package plagiarismdetection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.neu.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.SerializationUtils;

import edu.neu.comparison.ComparisonReport;
import edu.neu.comparison.Strategy;
import edu.neu.models.ReportContent;
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
	private ReportContent reportContent;
	private Strategy comparisonStrategy;
	private boolean runStarted = false;
	private boolean runCompleted = false;
	private String checkID;
	
	private ReportService reportService;
	
	public PlagiarismChecker(PlagiarismRun plagiarismRun, Strategy comparisonStrategy, ReportService reportService, String checkID) {
		this.plagiarismRun = plagiarismRun;
		this.comparisonStrategy = comparisonStrategy;
		this.reportContent = new ReportContent();
		this.reportService = reportService;
		this.checkID = checkID;
	}
	
	@Override
	public void run() {
		check();
	}
	
	
	// returns the plagiarism check id assosciated with the run
	public boolean check() {
		if(this.plagiarismRun!=null) {
			log.info("Initiating Plagiarism Check with id : "+checkID);
			this.report = reportService.createNewEmptyReportWithNameAndOwner(plagiarismRun.getDescription(), plagiarismRun.getUserId());
			runStarted = true;
			this.compareAllSubmissions(this.plagiarismRun);
			report.setReportFile(reportContent);
			Log.info("Report generated with "+reportContent.getComparisonList().size()+" comparisons");
			report.setReportScore(reportContent.getComparisonList().get(0).getScore());
			Log.info("Saving the report");
			reportService.saveReport(report);
			Log.info("Done Saving the report");
			runCompleted = true;
		}
		else {
			log.error(Constants.P_CHECK_ERROR_STRING + " for id : "+checkID);
			this.reportContent = new UnsuccessfulReportContent();
			Log.info("Generated an unsuccessful report content");
		}
		Log.info("Done Completing plagiarism check run");
		return runCompleted;
	}
	
	private void compareAllSubmissions(PlagiarismRun plagiarismRun) {
		Log.info("Start Comparing All Submissions pairs");
		for(Submission[] submissionPair : getSubmissionPairs(plagiarismRun)) {
			compareSubmissions(submissionPair[0], submissionPair[1]);
		}
		Log.info("Done Comparing All Submissions pairs");
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
		Log.info("Getting submission Files and comparing them");
		for(File f1 : submission1.getFiles()) {
			for(File f2 : submission2.getFiles()) {
				Log.info("Comparing files");
				this.reportContent.addTOComparisonList(compareFiles(f1, f2));
			}
		}
		Log.info("Done comparing getting submission Files and comparing them");
	}
	
	public ComparisonReport compareFiles(File f1, File f2) {
		/*ComparisonReport cr = new ComparisonReport(f1.getName(), f2.getName());
		cr.putScore(comparisonStrategy.getName(), comparisonStrategy.compare(f1, f2));
		return cr;*/
		return new ComparisonReport(f1.getName(), f2.getName(), comparisonStrategy.compare(f1, f2));
	}
	
	public boolean didRunStart() {
		return runStarted;
	}
	
	public int getNumComparisons() {
		return reportContent.getComparisonList().size();
	}
	
}
