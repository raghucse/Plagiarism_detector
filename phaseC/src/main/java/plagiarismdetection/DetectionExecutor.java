package plagiarismdetection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import edu.neu.comparison.Strategy;
import edu.neu.reports.PlagiarismRun;

public class DetectionExecutor {
	
	private final int THREAD_POOL_SIZE; // defaults to 2
	private ExecutorService executor;
	private static final Logger log = Logger.getLogger(DetectionExecutor.class);
	private static DetectionExecutor mInstance = null;
	
	private DetectionExecutor(int threadPoolSize) {
		THREAD_POOL_SIZE = threadPoolSize;
		log.info("Starting executor with "+THREAD_POOL_SIZE+ " threads");
		this.executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}
	
	private DetectionExecutor() {
		this(2);
	}
	
	public static DetectionExecutor getInstance() {
		if(mInstance == null) {
			mInstance = new DetectionExecutor();
		}
		return mInstance;
	}
	
	public static DetectionExecutor getInstance(int n) {
		if(mInstance == null) {
			mInstance = new DetectionExecutor(n);
		}
		return mInstance;
	}
	
	public boolean runPlagiarismCheck(PlagiarismRun plagRun, Strategy comparisonStrategy) {
		executor.submit(new PlagiarismChecker(plagRun, comparisonStrategy));
		log.info("Submitted to executor : "+plagRun);
		return true;
	}
	

}
