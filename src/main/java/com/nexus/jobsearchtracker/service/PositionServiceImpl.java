package com.nexus.jobsearchtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.jobsearchtracker.dao.PositionRepository;
import com.nexus.jobsearchtracker.domain.Position;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionRepository positionRepository;
	
	@Override
	public Position savePosition(Position p) {
		return positionRepository.save(p);
	}

	@Override
	public List<Position> listAll() {
		return positionRepository.findAll();
	}
}
