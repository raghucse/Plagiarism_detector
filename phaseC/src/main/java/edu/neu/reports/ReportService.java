package edu.neu.reports;

import edu.neu.user.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

/**
 * This class handles the Report Service
 */
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportSharedRepository reportSharedRepository;

    /**
     * @param userId determines the id of the user whose reports are to be returned
     * @return returns the list of reports found against the specified user id
     */
    public List<Report> findAllReportsForSharedUser(int userId){
        return reportSharedRepository.findAllReportsForSharedUser(userId);
    }

    /**
     * @param reportId determines the report id for which all the users
     *                 related to this id are to be determined
     * @return returns list of users found against the specified report id
     */
    public List<ApplicationUser> findSharedUsersForReport(int reportId){
        return reportSharedRepository.findUsersByReportId(reportId);
    }

    /**
     * @param reportId determines the report id to be searched
     * @return returns the report found against the specified id
     */
    public Report getReportByReportId(int reportId){
        return reportRepository.findById(reportId);
    }

    /**
     * @param userId determines the user id which is used to find all
     *               the reports related to this user
     * @return returns all the report ids found against the specified user id
     */
    public List<ReportIdsRes> getReportByOwnerId(int userId){
        List<ReportIdsRes> reportIdsRes = new ArrayList<>();
        List<Object[]> objs = reportRepository.findReportIdsByOwner(userId);
        System.out.println(objs.size());
        for(Object[] obj: objs ){
            ReportIdsRes reportIds = new ReportIdsRes();
            reportIds.setId((int)obj[0]);
            reportIds.setRunName((String)obj[1]);
            reportIdsRes.add(reportIds);
        }
        return reportIdsRes;
    }

    /**
     * @param userId determines the user id which is used to find all
     *               the reports related to this user
     * @return returns all the reports found against the specified user id
     */
    public List<Report> getReportIdsByOwner(int userId){
        return reportRepository.findByOwner(userId);
    }

    /*
     * @param report is the report to be saved
     * @return Returns the list of reports after saving the new report
     */
    public Report saveReport(Report report){
        return reportRepository.save(report);
    }

    /*
     * @param reportShared is the shared report to be saved
     * @return Returns the list of shared reports after saving the
     *         new shared report
     */
    public ReportShared saveSharedReport(ReportShared reportShared){
        return reportSharedRepository.save(reportShared);
    }

    /**
     * Creates a new report
     * @param name is the name of the report
     * @param ownerId is the id of the owner related to the report
     * @return returns the newly created report
     */
    public Report createNewEmptyReportWithNameAndOwner(String name, int ownerId, String description) {
    		Report report = new Report();
    		report.setRunName(name);
    		report.setDescription(description);
    		report.setOwner(ownerId);
    		return saveReport(report);
    }

    public long totalReportCount()
    {
        return reportRepository.count();
    }

}
