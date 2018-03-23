package edu.neu.testResources;

import java.io.File;

import edu.neu.comparison.ComparisonReport;
import edu.neu.comparison.Strategy;
import edu.neu.models.ReportContent;

public class AlwaysTrueComparisonStrategy implements Strategy{

	@Override
	public ComparisonReport compare(File f1, File f2) {
		ReportContent reportContent = new ReportContent();
		reportContent.appendToResult1("Match", true);
		reportContent.appendToResult2("Match", true);
		return new ComparisonReport(1.0, 1, reportContent);
	}
	
}
