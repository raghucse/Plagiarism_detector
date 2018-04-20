package edu.neu.plagiarismdetection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.neu.comparison.Strategy;
import edu.neu.models.Submission;
import edu.neu.reports.PlagiarismRun;
import edu.neu.reports.Report;
import edu.neu.reports.ReportService;
import edu.neu.testResources.AlwaysTrueComparisonStrategy;
import static org.mockito.Matchers.*;
import edu.neu.testResources.MockPlagiarismRun;
import edu.neu.testResources.MockSubmission;
import plagiarismdetection.PlagiarismChecker;

public class PlagiarismCheckTests {
	
	private static final double ASSERT_EPSILON = 0.001;
	private static final String MOCK_TEST_ID = "mockTestID"; 
	
	@Mock
	private ReportService reportService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		Report report = new Report();
		report.setId(1);
		when(reportService.createNewEmptyReportWithNameAndOwner(null, 0, null)).thenReturn(report);
		when(reportService.saveReport(any(Report.class))).thenReturn(report);
	}
	
	@Test
	public void testNullAssignment() throws IOException {
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = null;
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		p_checker.check();
		//assertEquals(Constants.P_CHECK_ERROR_STRING, p_checker.getReportResult());
		assertEquals(0, p_checker.getNumComparisons());
	}
	
	@Test
	public void testTwoSubmissionsAssignment(){
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp1"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp2"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		p_checker.check();
		//assertEquals(1.0, p_checker.getReportScore(), ASSERT_EPSILON);
		assertEquals(1, p_checker.getNumComparisons());
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
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		p_checker.check();
		//assertEquals(1.0, p_checker.getReportScore(), ASSERT_EPSILON);
		assertEquals(3, p_checker.getNumComparisons());
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
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		p_checker.check();
		//assertEquals(1.0, p_checker.getReportScore(), ASSERT_EPSILON);
		assertEquals(4, p_checker.getNumComparisons());
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
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		p_checker.check();
		//assertEquals(1.0, p_checker.getReportScore(), ASSERT_EPSILON);
		assertEquals(12, p_checker.getNumComparisons());
	}
	
	@Test
	public void testTwoSubmissionsAssignmentWithRun(){
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp1"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp2"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		p_checker.run();
		//assertEquals(1.0, p_checker.getReportScore(), ASSERT_EPSILON);
		assertEquals(1, p_checker.getNumComparisons());
	}
	
	
	@Test
	public void testIfPlagiarismCheckerStarted(){
		Strategy comparisonStrategy = new AlwaysTrueComparisonStrategy();
		PlagiarismRun plagiarismRun = new MockPlagiarismRun();
		
		Submission sub1 = new MockSubmission();
		sub1.getFiles().add(new File("temp1"));
		
		Submission sub2 = new MockSubmission();
		sub2.getFiles().add(new File("temp2"));
		
		plagiarismRun.getStudentSubmissions().add(sub1);
		plagiarismRun.getStudentSubmissions().add(sub2);
		
		PlagiarismChecker p_checker = new PlagiarismChecker(plagiarismRun, comparisonStrategy, reportService, MOCK_TEST_ID);
		assertFalse(p_checker.didRunStart());
		p_checker.run();
		assertTrue(p_checker.didRunStart());
		//assertEquals(1.0, p_checker.getReportScore(), ASSERT_EPSILON);
		assertEquals(1, p_checker.getNumComparisons());
	}
	
}
