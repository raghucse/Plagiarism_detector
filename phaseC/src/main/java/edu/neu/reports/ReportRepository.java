package edu.neu.reports;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * This interface maintains the repository of all the reports
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     * @param id determines the id of the report to be searched
     * @return returns the report found against the specified id
     */
    Report findById(int id);

    /**
     * @param owner determines the id of the owner whose reports are to be returned
     * @return returns the list of reports found against the specified owner id
     */
    List<Report> findByOwner(int owner);


    @Query(value = "SELECT r.id, r.run_name FROM report r WHERE r.owner = :owner", nativeQuery = true)
    List<Object[]> findReportIdsByOwner(@Param("owner") int owner);
}