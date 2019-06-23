package com.nexus.jobsearchtracker.domain;

import javax.persistence.Column;
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
	
	public Applicant() {}

	public Applicant(@NotNull @Size(max = 40) String firstName, @NotNull @Size(max = 40) String lastName,
		Integer yearsOfExperience, Address address) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.yearsOfExperience = yearsOfExperience;
	this.address = address;
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
