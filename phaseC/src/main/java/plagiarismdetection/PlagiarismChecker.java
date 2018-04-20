package plagiarismdetection;

import edu.neu.Log;
import edu.neu.comparison.ComparisonReport;
import edu.neu.comparison.DiffContent;
import edu.neu.comparison.Strategy;
import edu.neu.models.ReportContent;
import edu.neu.models.Submission;
import edu.neu.models.UnsuccessfulReportContent;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.Report;
import edu.neu.reports.ReportService;
import edu.neu.utils.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a runnable class which is run by the detection executor
 * Whenever a plagiarism check is initiated, it is constructed as this runnable and then
 * executed in the thread pool executor service
 * 
 * The runnable takes in a plagiarism run obect and a comparison strategy and then executes
 * as required.
 * 
 * The runnable includes a logger for logging.
 * 
 * @author bharat vaidhyanathan (team 106)
 */
public class PlagiarismChecker implements Runnable{
	
	// The logger for this class 
	private static final Logger log = LogManager.getLogger(PlagiarismChecker.class);

	// A single plagiarism check is run when a plagiarism check is initiated
	private PlagiarismRun plagiarismRun;
	
	// The report that is generated
	private Report report;
	
	// The content of the report
	private ReportContent reportContent;
	
	// The comparison strategy to be used
	private Strategy comparisonStrategy;
	
	// booleans signifying the process completion
	private boolean runStarted = false;
	private boolean runCompleted = false;
	
	// The check Id that is randomly generated
	private String checkID;
	
	// The report service that is used to interact with the report
	private ReportService reportService;
	
	/**
	 * The constructor for the plagiarism checker
	 * @param plagiarismRun : the plagiarism run object to be used to run the check
	 * @param comparisonStrategy : the comparison strategy that would be used
	 * @param reportService : the report service used to generat and dump reports
	 * @param checkID : the check ID unique to this run
	 */
	public PlagiarismChecker(PlagiarismRun plagiarismRun, Strategy comparisonStrategy, ReportService reportService, String checkID) {
		this.plagiarismRun = plagiarismRun;
		this.comparisonStrategy = comparisonStrategy;
		this.reportContent = new ReportContent();
		this.reportService = reportService;
		this.checkID = checkID;
	}
	
	/**
	 * The run method
	 * This is called by the executor service whenever it schedules this to a thread
	 */
	@Override
	public void run() {
		check();
	}
	
	
	/**
	 * This method initiates the check and returns if the check was done or not
	 * The method returns false if for some reason it is not able to do the check
	 * @return : whether the check was successfully completed or not
	 */
	// returns the plagiarism check id assosciated with the run
	public boolean check() {
		if(this.plagiarismRun!=null) {
			log.info("Initiating Plagiarism Check with id : "+checkID);
			this.report = reportService.createNewEmptyReportWithNameAndOwner(plagiarismRun.getDescription(), plagiarismRun.getUserId(), plagiarismRun.getDescription());
			runStarted = true;
			// Starting to compare all submissions
			this.compareAllSubmissions(this.plagiarismRun);
			report.setReportFile(reportContent);
			Log.info("Report generated with "+reportContent.getComparisonList().size()+" comparisons");
			report.setReportScore(reportContent.getComparisonList().get(0).getScores().getTotalScore());
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
	
	/**
	 * This method compares all the submission for a plagiarism run
	 * @param plagiarismRun : The plagiarism run object to check for
	 */
	private void compareAllSubmissions(PlagiarismRun plagiarismRun) {
		Log.info("Start Comparing All Submissions pairs");
		// Looping over all the submission pairs in the run
		for(Submission[] submissionPair : getSubmissionPairs(plagiarismRun)) {
			compareSubmissions(submissionPair[0], submissionPair[1]);
		}
		Log.info("Done Comparing All Submissions pairs");
	}
	
	/**
	 * A method to return unique submission pairs from the submission list in the 
	 * plagiarism run object
	 * @param plagiarismRun : the plagiarism run objec inside which the submissions are
	 * @return : Unique pairs from the list of submissions in the run object
	 */
	public List<Submission[]> getSubmissionPairs(PlagiarismRun plagiarismRun) {
		List<Submission[]> result = new ArrayList<>();
		// convert the submissions list to array
		Submission[] submissions= plagiarismRun.getStudentSubmissions()
				.toArray(new Submission[plagiarismRun.getStudentSubmissions().size()]);
		
		// Iterate over the array and return pairs  (unique)
		for(int i=0; i<submissions.length-1; i++) {
			for(int j=i+1; j<submissions.length; j++) {
				result.add(new Submission[] {submissions[i], submissions[j]});
			}
		}
		return result;
	}
	
	/**
	 * Compares two submissions and adds their results to report content
	 * @param submission1 : The first submission
	 * @param submission2 : The second submission
	 */
	public void compareSubmissions(Submission submission1, Submission submission2) {
		Log.info("Getting submission Files and comparing them");
		for(File f1 : submission1.getFiles()) {
			for(File f2 : submission2.getFiles()) {
				Log.info("Comparing files");
				this.reportContent.addTOComparisonList(compareFiles(submission1.getStudentName(), submission2.getStudentName(), f1, f2));
			}
		}
		Log.info("Done comparing getting submission Files and comparing them");
	}
	
	/**
	 * Compare two files and return the comparison report
	 * @param s1 : Student 1 name
	 * @param s2 : Student 2 name
	 * @param f1 : File 1
	 * @param f2 : File 2
	 * @return : The comparison report containing the comparison report for the files
	 */
	public ComparisonReport compareFiles(String s1, String s2, File f1, File f2) {
		return new ComparisonReport(s1, s2, f1.getName(), f2.getName(), comparisonStrategy.compare(f1, f2), new DiffContent().getFinalDiff(f1, f2));

	}
	
	/**
	 * Return whether the check started or not
	 * @return : whether run started or not
	 */
	public boolean didRunStart() {
		return runStarted;
	}
	
	/**
	 * Return the number of comparisons done
	 * @return : num comparisons
	 */
	public int getNumComparisons() {
		return reportContent.getComparisonList().size();
	}
	
}
