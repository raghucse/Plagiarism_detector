package edu.neu.reports;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findById(int id);
    List<Report> findByOwner(int owner);


    @Query(value = "SELECT r.id FROM report r WHERE r.owner = :owner", nativeQuery = true)
    List<Integer> findReportIdsByOwner(@Param("owner") int owner);
}