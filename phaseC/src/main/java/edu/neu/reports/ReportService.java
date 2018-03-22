package edu.neu.reports;

import edu.neu.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportSharedRepository reportSharedRepository;

    public List<Report> findAllReportsForSharedUser(int userId){
        return reportSharedRepository.findAllReportsForSharedUser(userId);
    }

    public List<User> findSharedUsersForReport(int reportId){
        return reportSharedRepository.findUsersByReportId(reportId);
    }

    public Report getReportByReportId(int reportId){
        return reportRepository.findById(reportId);
    }

    public Report getReportByOwnerId(int userId){
        return reportRepository.findByOwner(userId);
    }

    public void saveReport(Report report){
        reportRepository.save(report);
    }

    public void saveSharedReport(ReportShared reportShared){
        reportSharedRepository.save(reportShared);
    }

}
