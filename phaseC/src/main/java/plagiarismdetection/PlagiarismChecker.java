package plagiarismdetection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
			report.setReportFile(SerializationUtils.serialize(reportContent));
			reportService.saveReport(report);
			runCompleted = true;
		}
		else {
			log.error(Constants.P_CHECK_ERROR_STRING + " for id : "+checkID);
			this.reportContent = new UnsuccessfulReportContent();
		}
		
		return runCompleted;
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
				this.reportContent.addTOComparisonList(compareFiles(f1, f2));
			}
		}
	}
	
	public ComparisonReport compareFiles(File f1, File f2) {
		ComparisonReport cr = new ComparisonReport(f1.getName(), f2.getName());
		cr.putScore(comparisonStrategy.getName(), comparisonStrategy.compare(f1, f2));
		return cr;
	}
	
	public boolean didRunStart() {
		return runStarted;
	}
	
	public int getNumComparisons() {
		return reportContent.getComparisonList().size();
	}
	
}
