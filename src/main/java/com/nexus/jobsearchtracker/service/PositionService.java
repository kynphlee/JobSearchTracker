package com.nexus.jobsearchtracker.service;

import java.util.List;

import com.nexus.jobsearchtracker.domain.Position;

public interface PositionService {
	
	List<Position> listAll();

	Position savePosition(Position p);

}