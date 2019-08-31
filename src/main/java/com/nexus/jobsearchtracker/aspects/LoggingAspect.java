package com.nexus.jobsearchtracker.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Position;
import com.nexus.jobsearchtracker.domain.Skill;

@Aspect
@Component
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger("LoggingAspect");
	
	//	Applicant Pointcuts
	@Pointcut("target(com.nexus.jobsearchtracker.service.ApplicantService)")
	public void applicantServices() {}
	
	@Pointcut("execution(* *..save(com.nexus.jobsearchtracker.domain.Applicant))")
	public void newApplicant() {}
	
	//	Position Pointcuts
	@Pointcut("target(com.nexus.jobsearchtracker.service.PositionService)")
	public void positionServices() {}
	
	@Pointcut("execution(* *..savePosition(com.nexus.jobsearchtracker.domain.Position))")
	public void savePosition() {}
	
	//	Skills Pointcuts
	@Pointcut("target(com.nexus.jobsearchtracker.service.SkillService)")
	public void skillServices() {}
	
	@Pointcut("execution(* *..saveSkill(com.nexus.jobsearchtracker.domain.Skill))")
	public void saveSkill() {}
	
	//	Saving a new applicant
	@AfterReturning(value = "applicantServices() && newApplicant()", returning = "applicant")
	public void logApplicant(JoinPoint jp, Applicant applicant) {
		String params = String.format("First Name: %s, Last Name: %s, Address1: %s, Address2: %s, "
				+ "City: %s, State: %s, Zip: %d", 
				applicant.getFirstName(), applicant.getLastName(),
				applicant.getAddress().getAddress1(), applicant.getAddress().getAddress2(),
				applicant.getAddress().getCity(), applicant.getAddress().getState(),
				applicant.getAddress().getZip());
		logger.info(String.format("Applicant was called with parameters: %s", params));
	}
	
	//	Saving skills for an applicant
	@AfterReturning(value = "skillServices() && saveSkill()", returning = "skill")
	public void logSkill(JoinPoint jp, Skill skill) {
		String params = String.format("Skill: %s, Experience: %s",
				skill.getSkill(), skill.getYearsOfExperience());
		logger.info(String.format("Skill for %s %s was added: %s", 
				skill.getApplicant().getFirstName(), 
				skill.getApplicant().getLastName(),
				params));
	}
	
	//	Saving a position
	@AfterReturning(value = "positionServices() && savePosition()", returning = "position")
	public void logPosition(JoinPoint jp, Position position) {
		String params = String.format("Company Name: %s, Date Applied: %s",
				position.getCompanyName(), position.getDateApplied());
		
		logger.info(String.format("New position was added: %s", 
//				position.getApplicant().getFirstName(), 
//				position.getApplicant().getLastName(),
				params));
	}
}
