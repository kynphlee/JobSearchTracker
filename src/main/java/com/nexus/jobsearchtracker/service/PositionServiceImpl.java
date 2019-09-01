package com.nexus.jobsearchtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.jobsearchtracker.dao.PositionDetailsRepository;
import com.nexus.jobsearchtracker.dao.PositionRepository;
import com.nexus.jobsearchtracker.domain.Position;
import com.nexus.jobsearchtracker.domain.PositionDetails;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionRepository positionRepository;
	
//	@Autowired
//	private PositionDetailsRepository positionDetailsRepository;
	
	@Override
	public Position savePosition(Position p) {
		return positionRepository.save(p);
	}
}
