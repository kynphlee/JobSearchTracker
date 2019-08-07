package com.nexus.jobsearchtracker.service;

import java.util.List;

import com.nexus.jobsearchtracker.domain.Applicant;

public interface ApplicantService {

	List<Applicant> listAll();

	Applicant save(Applicant a);

}