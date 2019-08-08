package com.nexus.jobsearchtracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nexus.jobsearchtracker.dao.ApplicantRepository;
import com.nexus.jobsearchtracker.dao.SkillsRepository;
import com.nexus.jobsearchtracker.domain.Address;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Skill;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SkillsRepositoryTest {
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	static Address testAddress;
	static Applicant testApplicant;
	
	@Before
	public void init() {
		testAddress = new Address();
		testAddress.setAddress1("1419 Druid Valley Drive NE");
		testAddress.setAddress2("Apt. A");
		testAddress.setCity("Atlanta");
		testAddress.setState("Ga");
		testAddress.setZip(30329);
		
		testApplicant = new Applicant(
				"Kendall",
				"Fleming",
				11,
				testAddress);
	}
	
	@Test
	public void saveAndRetrieveEntity() {
		Applicant a = applicantRepository.save(testApplicant);
		
		Skill skill = new Skill();
		skill.setSkill("Java");
		skill.setYearsOfExperience("9");
		skill.setApplicant(a);
		
		skillsRepository.save(skill);
		assertThat(skillsRepository.count()).isEqualTo(1);
		
		List<Skill> allSkills = skillsRepository.findAll();
		Skill s = allSkills.get(0);
		assertThat(s.getId()).isEqualTo(1);
		assertThat(s.getSkill()).isEqualTo("Java");
		assertThat(s.getYearsOfExperience()).isEqualTo("9");
		assertThat(s.getApplicant().getId()).isEqualTo(1);
	}

}
