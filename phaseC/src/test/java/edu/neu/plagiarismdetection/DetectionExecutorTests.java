package edu.neu.plagiarismdetection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.neu.reports.Report;
import edu.neu.reports.ReportService;
import edu.neu.testResources.AlwaysTrueComparisonStrategy;
import plagiarismdetection.DetectionExecutor;

public class DetectionExecutorTests {

	@Mock
	private ReportService reportService;
	private static DetectionExecutor executor;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		Report report = new Report();
		report.setId(1);
		when(reportService.createNewEmptyReportWithNameAndOwner(null, 0, null)).thenReturn(report);
		when(reportService.saveReport(any(Report.class))).thenReturn(report);
	}
	
	@Test
	public void testDetectionExecutorConstruction() {
		executor = DetectionExecutor.getInstance(reportService);
	}
	
	@Test
	public void testGetSameInstanceOfDetectionExecutor() {
		executor = DetectionExecutor.getInstance(reportService);
		DetectionExecutor executor1 = DetectionExecutor.getInstance(reportService);
		assertEquals(executor, executor1);
	}
	
	@Test
	public void testMockRun() {
		executor = DetectionExecutor.getInstance(reportService);
		executor.runPlagiarismCheck(null, new AlwaysTrueComparisonStrategy());
	}
	
	@AfterClass
	public static void cleanUp() {
		executor.shutDown();
	}
	
}
