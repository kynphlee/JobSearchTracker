package com.nexus.jobsearchtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.jobsearchtracker.dao.ApplicantRepository;
import com.nexus.jobsearchtracker.domain.Applicant;

@Service
public class ApplicantServiceImpl {

	@Autowired
	private ApplicantRepository applicantRepository;
	
	public List<Applicant> listAll() {
		return applicantRepository.findAll();
	}
	
	public Applicant save(Applicant a) {
		return applicantRepository.save(a);
	}
}
