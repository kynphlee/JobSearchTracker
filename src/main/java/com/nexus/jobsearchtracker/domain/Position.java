package com.nexus.jobsearchtracker.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Positions")
public class Position {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String positionTitle;
	private String dateApplied;
	private String companyName;
	private String duration;
	
	@Embedded
	private Address address;
	
	private String responsibilities;
	private String qualifications;
	private String preferredQualifications;
	private String perksBenefits;
	
	public Position() {}

	public Position(String positionTitle, String dateApplied, 
			String companyName, String duration, Address address,
			String responsibilities, String qualifications, 
			String preferredQualifications, String perksBenefits) {
		super();
		this.positionTitle = positionTitle;
		this.dateApplied = dateApplied;
		this.companyName = companyName;
		this.duration = duration;
		this.address = address;
		this.responsibilities = responsibilities;
		this.qualifications = qualifications;
		this.preferredQualifications = preferredQualifications;
		this.perksBenefits = perksBenefits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(String dateApplied) {
		this.dateApplied = dateApplied;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getPreferredQualifications() {
		return preferredQualifications;
	}

	public void setPreferredQualifications(String preferredQualifications) {
		this.preferredQualifications = preferredQualifications;
	}

	public String getPerksBenefits() {
		return perksBenefits;
	}

	public void setPerksBenefits(String perksBenefits) {
		this.perksBenefits = perksBenefits;
	}
}