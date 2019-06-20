package com.nexus.jobsearchtracker.domain;

import java.io.Serializable;

public class Skill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String skill;
	private String yearsOfExperience;
	
	public Skill() {}
	
	public Skill(String skill, String yearsOfExperience) {
		super();
		this.skill = skill;
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	

}
