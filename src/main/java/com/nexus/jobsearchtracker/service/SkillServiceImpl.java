package com.nexus.jobsearchtracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.nexus.jobsearchtracker.dao.SkillsRepository;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Skill;

@Service
public class SkillServiceImpl implements SkillService {

	private TransactionTemplate txTemplate;

	private SkillsRepository skillsRepository;

	@Autowired
	public SkillServiceImpl(PlatformTransactionManager platformTransactionManager, SkillsRepository skillsRepository) {
		this.txTemplate = new TransactionTemplate(platformTransactionManager);
		this.skillsRepository = skillsRepository;
	}
	
	@Override
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
	
	@Override
	public Skill saveSkill(Skill s) {
		return txTemplate.execute((status) -> skillsRepository.save(s));
	}
	
	@Override
	public List<Skill> saveAllSkills(Iterable<Skill> entities) {
		return skillsRepository.saveAll(entities);
	}
}
