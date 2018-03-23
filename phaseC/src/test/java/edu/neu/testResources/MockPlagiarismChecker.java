package edu.neu.testResources;

import java.io.IOException;
import edu.neu.Log;
import edu.neu.comparison.Strategy;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.ReportService;
import plagiarismdetection.PlagiarismChecker;

public class MockPlagiarismChecker extends PlagiarismChecker{

	public MockPlagiarismChecker(PlagiarismRun plagiarismRun, Strategy comparisonStrategy,
			ReportService reportService) {
		super(plagiarismRun, comparisonStrategy, reportService);
	}
	
	@Override
	protected void dumpReportToTable() throws IOException {
		Log.info("Report dumped to table mocked");
	}
	
}
