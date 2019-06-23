package com.nexus.jobsearchtracker.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class Skill implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
//	@Column(name="applicant_id")
//	private Long applicantId;
	
	private String skill;
	private String yearsOfExperience;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "applicant_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Applicant application;
	
	public Skill() {}

	public Skill(/*Long applicantId,*/ String skill, String yearsOfExperience) {
		super();
		//this.applicantId = applicantId;
		this.skill = skill;
		this.yearsOfExperience = yearsOfExperience;
	}

//	public Long getApplicantId() {
//		return applicantId;
//	}
//
//	public void setApplicantId(Long applicantId) {
//		this.applicantId = applicantId;
//	}

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
