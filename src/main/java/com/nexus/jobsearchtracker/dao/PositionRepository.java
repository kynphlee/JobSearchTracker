package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.jobsearchtracker.domain.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

}
