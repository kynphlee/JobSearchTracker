package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.jobsearchtracker.domain.SkillsList;

public interface SkillsRepository extends JpaRepository<SkillsList, Long>{

}
