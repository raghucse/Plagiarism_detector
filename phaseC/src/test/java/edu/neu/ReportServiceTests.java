package edu.neu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.neu.reports.Report;
import edu.neu.reports.ReportRepository;
import edu.neu.reports.ReportService;
import edu.neu.reports.ReportShared;
import edu.neu.reports.ReportSharedRepository;

public class ReportServiceTests {

	@Mock
    private ReportRepository reportRepository;

	@Mock
    private ReportSharedRepository reportSharedRepository;
    
    @Mock
    private ReportService reportService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(reportRepository.findById(0)).thenReturn(null);
		when(reportRepository.findReportIdsByOwner(0)).thenReturn(null);
		when(reportRepository.findByOwner(0)).thenReturn(null);
		when(reportRepository.save(new Report())).thenReturn(null);
		when(reportSharedRepository.findAllReportsForSharedUser(0)).thenReturn(null);
		when(reportSharedRepository.findUsersByReportId(0)).thenReturn(null);
		when(reportSharedRepository.save(new ReportShared())).thenReturn(null);
	}
	
	@Test
	public void testFindById() {
		assertNull(reportService.getReportByReportId(0));
	}
	
	@Test
	public void testFindReportIdsByOwner() {
		assertEquals(0, reportService.getReportByOwnerId(0).size());
	}
	
	@Test
	public void testFindByOwnerId() {
		assertEquals(0, reportService.getReportByOwnerId(0).size());
	}
	
	@Test
	public void testFindByOwner() {
		assertEquals(0, reportService.getReportIdsByOwner(0).size());
	}
	
	@Test
	public void testSaveReport() {
		assertNull(reportService.saveReport(null));
	}
	
	@Test
	public void testSaveSharedReport() {
		assertNull(reportService.saveSharedReport(null));
	}
	
	@Test
	public void testFindAllReportsForSharedUser() {
		assertEquals(0, reportService.findAllReportsForSharedUser(0).size());
	}
	
}
