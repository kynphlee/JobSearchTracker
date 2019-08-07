package com.nexus.jobsearchtracker.service;

import java.util.List;
import java.util.Map;

import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Skill;

public interface SkillService {

	void updateSkillList(Applicant applicant, Map<String, String[]> reqParams);

	Skill saveSkill(Skill s);

	List<Skill> saveAllSkills(Iterable<Skill> entities);

}