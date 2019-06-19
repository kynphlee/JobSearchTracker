package com.nexus.jobsearchtracker.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Applicants")
public class Applicant {
	
	@Id
	@GeneratedValue
	private Long id;
	
//	@NotNull
	@Size(max = 40)
	private String firstName;
	
//	@NotNull
	@Size(max = 40)
	private String lastName;
	
	private Integer yearsOfExperience;
	
	@Embedded
	private Address address;
	
	private ArrayList<Skill> skills = new ArrayList<>();
	
	public Applicant() {}

	public Applicant(Long id, @NotNull @Size(max = 40) String firstName, @NotNull @Size(max = 40) String lastName,
			Integer yearsOfExperience, Address address, ArrayList<Skill> skills) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearsOfExperience = yearsOfExperience;
		this.address = address;
		this.skills = skills;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		StringBuilder skillSet = new StringBuilder();
		for(Skill skill: skills) {
			skillSet.append(String.format("%s: %d,", skill.getSkill(), skill.getYearsOfExperience()));
		}
		
		return "Applicant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", yearsOfExperience="
				+ yearsOfExperience + ", address=" + address + ", skills=" + skillSet + "]";
	}
	
	
}
