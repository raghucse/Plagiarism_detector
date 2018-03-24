package plagiarismdetection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.neu.Log;
import edu.neu.comparison.Strategy;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.ReportService;

public class DetectionExecutor {
	
	private final int THREAD_POOL_SIZE; // defaults to 2
	private ExecutorService executor;
	private static DetectionExecutor mInstance = null;
	private long checkCount = 0;
	

	private ReportService reportService;
	
	private DetectionExecutor(ReportService reportService, int threadPoolSize) {
		this.reportService = reportService;
		THREAD_POOL_SIZE = threadPoolSize;
		Log.info("Starting executor with "+THREAD_POOL_SIZE+ " threads");
		this.executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}
	
	private DetectionExecutor(ReportService reportService) {
		this(reportService, 2);
	}
	
	public static DetectionExecutor getInstance(ReportService reportService) {
		if(mInstance == null) {
			mInstance = new DetectionExecutor(reportService);
		}
		return mInstance;
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

}
