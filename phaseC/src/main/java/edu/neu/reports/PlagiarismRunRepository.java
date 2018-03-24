package edu.neu.reports;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlagiarismRunRepository extends JpaRepository<PlagiarismRun, Long> {
	PlagiarismRun findById(int id);
}