package com.nexus.jobsearchtracker.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="skills")
public class SkillsList {

//	@Id
//	@GeneratedValue
	private Long id;
	
//	@ElementCollection
//	private ArrayList<Skill> skillSet;
	
	public SkillsList() {
//		skillSet = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public ArrayList<Skill> getSkillSet() {
//		return skillSet;
//	}
//
//	public void setSkillSet(ArrayList<Skill> skillSet) {
//		this.skillSet = skillSet;
//	}
}
