package com.nexus.jobsearchtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.nexus.jobsearchtracker.dao.ApplicantRepository;
import com.nexus.jobsearchtracker.domain.Applicant;

@Service
public class ApplicantServiceImpl implements ApplicantService {

	private TransactionTemplate txTemplate;

	private ApplicantRepository applicantRepository;

	@Autowired
	public ApplicantServiceImpl(PlatformTransactionManager platformTransactionManager, ApplicantRepository applicantRepository) {
		this.txTemplate = new TransactionTemplate(platformTransactionManager);
		this.applicantRepository = applicantRepository;
	}
	
	@Override
	public List<Applicant> listAll() {
		return applicantRepository.findAll();
	}
	
	@Override
	public Applicant save(Applicant a) {
		return txTemplate.execute(new TransactionCallback<Applicant>() {

			@Override
			public Applicant doInTransaction(TransactionStatus status) {
				return applicantRepository.save(a);
			}
		});
	}
}
