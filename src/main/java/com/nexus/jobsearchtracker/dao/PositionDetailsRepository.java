package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.jobsearchtracker.domain.PositionDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDetailsRepository extends JpaRepository<PositionDetails, Long>{

}
