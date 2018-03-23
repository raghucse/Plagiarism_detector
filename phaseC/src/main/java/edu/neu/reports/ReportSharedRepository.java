package edu.neu.reports;

import edu.neu.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportSharedRepository extends JpaRepository<ReportShared, Long> {
    /**
     *
     * @param reportId
     * @return
     */
    List<ReportShared> findByReportId(int reportId);

    /**
     *
     * @param user
     * @return ReportShared
     */
    List<ReportShared> findByUser(int user);

    @Query(value = "SELECT * FROM users where users.id IN ( SELECT userId from reportshared rs where rs.reportId = :reportId)",
            nativeQuery=true)
    public List<ApplicationUser> findUsersByReportId(@Param("reportId") int reportId);


    @Query(value = "SELECT * FROM report where report.id IN ( SELECT reportId from reportshared rs where rs.user = :userId)",
            nativeQuery=true)
    public List<Report> findAllReportsForSharedUser(@Param("userId") int userId);
}
