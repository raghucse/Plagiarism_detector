package edu.neu.comparison;

import edu.neu.models.ReportContent;

public class ComparisonReport {

	double runningScore;
	int runningCount;
	ReportContent reportContent;
	
	public ComparisonReport() {
		runningScore = 0.0;
		runningCount = 0;
		reportContent = new ReportContent();
	}
	
	public ComparisonReport(double score, int count, ReportContent reportContent) {
		this.runningScore = score;
		this.runningCount = count;
		this.reportContent = reportContent;
	}
	
	public double getAvgScore() {
		return runningScore / runningCount;
	}
	
	public int getRunningCount() {
		return runningCount;
	}
	
	public void setReportContent(ReportContent reportContent) {
		this.reportContent = reportContent;
	}
	
	public ReportContent getReportContent() {
		return reportContent;
	}
	
	public void addAnotherComparisonReport(ComparisonReport other) {
		this.runningScore += other.runningScore;
		this.runningCount += other.runningCount;
		this.reportContent.addAll(other.reportContent);
	}
	
	@Override
	public String toString() {
		return this.getAvgScore()+"|"+reportContent.getResult();
	}
	
}
