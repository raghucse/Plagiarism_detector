package edu.neu.reports;

import edu.neu.user.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportSharedRepository reportSharedRepository;

    public List<Report> findAllReportsForSharedUser(int userId){
        return reportSharedRepository.findAllReportsForSharedUser(userId);
    }

    public List<ApplicationUser> findSharedUsersForReport(int reportId){
        return reportSharedRepository.findUsersByReportId(reportId);
    }

    public Report getReportByReportId(int reportId){
        return reportRepository.findById(reportId);
    }

    public List<Report> getReportByOwnerId(int userId){
        return reportRepository.findByOwner(userId);
    }

    public Report saveReport(Report report){
        return reportRepository.save(report);
    }

    public ReportShared saveSharedReport(ReportShared reportShared){
        return reportSharedRepository.save(reportShared);
    }
    
    public Report createNewEmptyReportWithNameAndOwner(String name, int ownerId) {
    		System.out.println("THIS IS CALLED");
    		Report report = new Report();
    		report.setName(name);
    		report.setOwner(ownerId);
    		return saveReport(report);
    }

}
