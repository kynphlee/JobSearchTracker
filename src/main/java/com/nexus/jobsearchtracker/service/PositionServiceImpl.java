package com.nexus.jobsearchtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.nexus.jobsearchtracker.dao.PositionRepository;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Position;

@Service
public class PositionServiceImpl implements PositionService {
	
	private TransactionTemplate txTemplate;

	@Autowired
	private PositionRepository positionRepository;
	
	public PositionServiceImpl(PlatformTransactionManager platformTransactionManager) {
		this.txTemplate = new TransactionTemplate(platformTransactionManager);
	}
	
	@Override
	public List<Position> listAll() {
		return positionRepository.findAll();
	}
	
	@Override
	public Position savePosition(Position p) {
		return txTemplate.execute(new TransactionCallback<Position>() {

			@Override
			public Position doInTransaction(TransactionStatus status) {
				return positionRepository.save(p);
			}
			
		});
	}
}
