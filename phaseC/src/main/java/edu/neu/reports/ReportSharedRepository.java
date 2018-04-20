package edu.neu.reports;

import edu.neu.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This interface handles all the reports shared by the user
 */
public interface ReportSharedRepository extends JpaRepository<ReportShared, Long> {
    /**
     * Returns list of shared reports based on the report id
     * @param reportId is the id of the report to be searched
     * @return list of shared reports found against the specified id
     */
    List<ReportShared> findByReportId(int reportId);

    /**
     * Returns list of shared reports based on the user id
     * @param userId is the id of the user whose reports are to be searched for
     * @return ReportShared returns list of shared reports found against the specified id
     */
    List<ReportShared> findByUserId(int userId);

    @Query(value = "SELECT * FROM users where users.id IN ( SELECT userId from reportshared rs where rs.reportId = :reportId)",
            nativeQuery=true)
    /**
     * Finds the list of users based on the specified report id
     */
    public List<ApplicationUser> findUsersByReportId(@Param("reportId") int reportId);


    @Query(value = "SELECT * FROM report where report.id IN ( SELECT reportId from reportshared rs where rs.user = :userId)",
            nativeQuery=true)

    /**
     * Finds the list of reports based on the specified report id
     */
    public List<Report> findAllReportsForSharedUser(@Param("userId") int userId);
}
