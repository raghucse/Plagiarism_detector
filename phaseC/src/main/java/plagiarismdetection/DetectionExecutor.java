package plagiarismdetection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.neu.Log;
import edu.neu.comparison.Strategy;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.ReportService;

/**
 * The Plagiarism detection executor (Singleton)
 * Has an executor service to execute threads (runs for each checks)
 * @author bharat vaidhyanathan
 */
public class DetectionExecutor {
	
	// The size of the thread pool of the underlying executor service
	private final int threadPoolSize; // defaults to 2
	// The underlying executor service
	private ExecutorService executor;
	// The single instance of this executor
	private static DetectionExecutor mInstance = null;
	// The number of checks done
	private long checkCount = 0;
	
	// The report service used to generate and save the report
	private ReportService reportService;
	
	/**
	 * The private constructor for this to allow it to be a singleton
	 * @param reportService : The report service needed to generate the report
	 * @param threadPoolSize : The size of the underlying executor service thread pool
	 */
	private DetectionExecutor(ReportService reportService, int threadPoolSize) {
		this.reportService = reportService;
		this.threadPoolSize = threadPoolSize;
		Log.info("Starting executor with "+threadPoolSize+ " threads");
		this.executor = Executors.newFixedThreadPool(threadPoolSize);
	}
	
	/**
	 * Returns the single instance assosciated with this singleton (with default 2 threads)
	 * @param reportService : The report service needed to generate report
	 * @return : The instance assosciated with the executor
	 */
	public static DetectionExecutor getInstance(ReportService reportService) {
		return getInstance(reportService, 2);
	}
	
	/**
	 * Returns the single instance assosciated with this singleton
	 * @param reportService : The report service needed to generate report
	 * @param n : The number of threads in the pool of the underlying executor service
	 * @return : The instance assosciated with the executor
	 */
	public static DetectionExecutor getInstance(ReportService reportService, int n) {
		if(mInstance == null) {
			mInstance = new DetectionExecutor(reportService, n);
		}
		return mInstance;
	}
	
	/**
	 * A method run the plagiarism check
	 * Submits the plagiarism check details to the underlying executor service
	 * @param plagRun : the plagiarism run object containing the information for the run
	 * @param comparisonStrategy : The comparison strategy to be used for the check
	 * @return
	 */
	public boolean runPlagiarismCheck(PlagiarismRun plagRun, Strategy comparisonStrategy) {
		Log.info("Submitting git plagiarism check to executor : "+plagRun);
		executor.submit(new PlagiarismChecker(plagRun, comparisonStrategy, reportService, getUniqueCheckID()));
		Log.info("Submitted git plagiarism check to executor : "+plagRun);
		return true;
	}
	
	/**
	 * A synchronized method to reutrn a unique id every time
	 * used for generating an id for the check
	 * @return : The uniquely generated run id
	 */
	private synchronized String getUniqueCheckID() {
		checkCount ++;
		return checkCount+"";
	}
	
	/**
	 * A method to stop the executor
	 */
	public void shutDown() {
		Log.info("Stopping executor");
		this.executor.shutdown();
	}

}
