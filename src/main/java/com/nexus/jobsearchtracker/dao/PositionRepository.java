package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.jobsearchtracker.domain.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{

}
