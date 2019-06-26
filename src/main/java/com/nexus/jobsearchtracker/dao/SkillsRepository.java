package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexus.jobsearchtracker.domain.Skill;

public interface SkillsRepository extends JpaRepository<Skill, Long>{

}
