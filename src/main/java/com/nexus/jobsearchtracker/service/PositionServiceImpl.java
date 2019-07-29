package com.nexus.jobsearchtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.jobsearchtracker.dao.PositionRepository;
import com.nexus.jobsearchtracker.domain.Position;

@Service
public class PositionServiceImpl {

	@Autowired
	private PositionRepository positionRepository;
	
	public Position savePosition(Position p) {
		return positionRepository.save(p);
	}
}
