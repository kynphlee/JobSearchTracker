package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.jobsearchtracker.domain.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}
