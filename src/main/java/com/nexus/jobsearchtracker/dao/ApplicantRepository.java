package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.jobsearchtracker.domain.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}
