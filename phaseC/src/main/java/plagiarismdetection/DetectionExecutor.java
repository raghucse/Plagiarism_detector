package plagiarismdetection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.neu.Log;
import edu.neu.comparison.Strategy;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.ReportService;

public class DetectionExecutor {
	
	private final int threadPoolSize; // defaults to 2
	private ExecutorService executor;
	private static DetectionExecutor mInstance = null;
	private long checkCount = 0;
	

	private ReportService reportService;
	
	private DetectionExecutor(ReportService reportService, int threadPoolSize) {
		this.reportService = reportService;
		this.threadPoolSize = threadPoolSize;
		Log.info("Starting executor with "+threadPoolSize+ " threads");
		this.executor = Executors.newFixedThreadPool(threadPoolSize);
	}
	
	public static DetectionExecutor getInstance(ReportService reportService) {
		return getInstance(reportService, 2);
	}
	
	public static DetectionExecutor getInstance(ReportService reportService, int n) {
		if(mInstance == null) {
			mInstance = new DetectionExecutor(reportService, n);
		}
		return mInstance;
	}
	
	public boolean runPlagiarismCheck(PlagiarismRun plagRun, Strategy comparisonStrategy) {
		Log.info("Submitting git plagiarism check to executor : "+plagRun);
		executor.submit(new PlagiarismChecker(plagRun, comparisonStrategy, reportService, getUniqueCheckID()));
		Log.info("Submitted git plagiarism check to executor : "+plagRun);
		return true;
	}
	
	private synchronized String getUniqueCheckID() {
		checkCount ++;
		return checkCount+"";
	}
	
	public void shutDown() {
		Log.info("Stopping executor");
		this.executor.shutdown();
	}

}
