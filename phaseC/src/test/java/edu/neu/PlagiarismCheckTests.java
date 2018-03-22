package edu.neu;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import edu.neu.comparison.Strategy;
import edu.neu.models.Submission;
import edu.neu.reports.PlagiarismRun;
import edu.neu.testResources.AlwaysTrueComparisonStrategy;
import edu.neu.testResources.MockPlagiarismRun;
import edu.neu.testResources.MockSubmission;
import edu.neu.utils.Constants;
import plagiarismdetection.PlagiarismChecker;

public class PlagiarismCheckTests {
	
	@Test
	public void testNullAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = null;
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy);
		p_checker.check();
		assertEquals(Constants.P_CHECK_ERROR_STRING, p_checker.getReportResult());
	}
	
	@Test
	public void testTwoSubmissionsAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp1"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp2"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy);
		p_checker.check();
		assertEquals(1, matchCount(p_checker.getReportResult()));
	}
	
	
	@Test
	public void testThreeSubmissionsAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp1"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp2"));
		
		Submission sub3 = new MockSubmission();
		sub3.getFiles().add(new File("temp3"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		plagiarismRun.getStudentSubmissions().add(sub3);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy);
		p_checker.check();
		assertEquals(3, matchCount(p_checker.getReportResult()));
	}
	
	@Test
	public void testTwoSubmissionsTwoFilesEachAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp-a1"));
		sub1.getFiles().add(new File("temp-a2"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp-b1"));
		sub2.getFiles().add(new File("temp-b2"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy);
		p_checker.check();
		assertEquals(4, matchCount(p_checker.getReportResult()));
	}
	
	@Test
	public void testThreeSubmissionsTwoFilesEachAssignment() {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp-a1"));
		sub1.getFiles().add(new File("temp-a2"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp-b1"));
		sub2.getFiles().add(new File("temp-b2"));
		
		Submission sub3 = new MockSubmission();
		sub3.getFiles().add(new File("temp-c1"));
		sub3.getFiles().add(new File("temp-c2"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		plagiarismRun.getStudentSubmissions().add(sub3);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy);
		p_checker.check();
		assertEquals(12, matchCount(p_checker.getReportResult()));
	}
	
	private int matchCount(String result) {
		int count = 0;
		for(String s : result.split(",")) {
			if(s.equals("Match"))
				count++;
		}
		return count;
	}
}
