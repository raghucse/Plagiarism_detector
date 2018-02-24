package edu.neu.controllers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository
	extends JpaRepository<HelloObject, Integer> {
}
