package com.nexus.jobsearchtracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.jobsearchtracker.dao.SkillsRepository;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Skill;

@Service
public class SkillServiceImpl {

	@Autowired
	private SkillsRepository skillsRepository;
	
	public void updateSkillList(Applicant applicant, Map<String, String[]> reqParams) {
		reqParams.forEach((key, value) -> {
			if (key.contains("skills")) {
				int id = Integer.parseInt(key.substring(7, 8));
				if (key.contains(".skill"))
					applicant.getSkills().get(id).setSkill(value[0]);
				else if (key.contains(".yearsOfExperience")) {
					applicant.getSkills().get(id).setYearsOfExperience(value[0]);
				}
			}
		});
	}
	
	public Skill saveSkill(Skill s) {
		return skillsRepository.save(s);
	}
	
	public List<Skill> saveAllSkills(Iterable<Skill> entities) {
		return skillsRepository.saveAll(entities);
	}
}
