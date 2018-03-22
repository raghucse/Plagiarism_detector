package edu.neu.reports;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findById(int id);
    Report findByOwner(int owner);
}