package edu.neu.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findById(int id);
    List<Report> findByOwner(int owner);
}