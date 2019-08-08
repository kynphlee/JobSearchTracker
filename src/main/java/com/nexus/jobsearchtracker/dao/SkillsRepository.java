package com.nexus.jobsearchtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.jobsearchtracker.domain.Skill;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, Long>{

}
