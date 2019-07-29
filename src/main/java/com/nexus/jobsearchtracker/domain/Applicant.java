package com.nexus.jobsearchtracker.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Applicants")
public class Applicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Size(max = 40)
	@Column(name="first_name")
	private String firstName;
	
	@NotNull
	@Size(max = 40)
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="experience")
	private Integer yearsOfExperience;
	
	@Embedded
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY,
			mappedBy = "applicant")
	private List<Skill> skills = new ArrayList<Skill>();
	
	@OneToMany(mappedBy = "applicant")
	private List<Position> positions = new ArrayList<Position>();
	
	public Applicant() {}

	public Applicant(@NotNull @Size(max = 40) String firstName, @NotNull @Size(max = 40) String lastName,
		Integer yearsOfExperience, Address address) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.yearsOfExperience = yearsOfExperience;
	this.address = address;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", yearsOfExperience="
				+ yearsOfExperience + ", address=" + address + "]";
	}
	
	
}
