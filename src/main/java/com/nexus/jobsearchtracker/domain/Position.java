package com.nexus.jobsearchtracker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Positions")
public class Position {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String positionTitle;
	private String dateApplied;
	private String companyName;
	private String duration;
	
	@Embedded
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "applicant_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Applicant applicant;
	
	@OneToOne(mappedBy = "position", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "positionDetails_id", nullable = false)
	private PositionDetails positionDetails;

	public Position() {}

	public Position(String positionTitle, String dateApplied, 
			String companyName, String duration, Address address,
			PositionDetails positionDetails) {
		super();
		this.positionTitle = positionTitle;
		this.dateApplied = dateApplied;
		this.companyName = companyName;
		this.duration = duration;
		this.address = address;
		this.positionDetails = positionDetails;
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

	public PositionDetails getPositionDetails() {
		return positionDetails;
	}

	public void setPositionDetails(PositionDetails positionDetails) {
		if (positionDetails == null) {
			if (this.positionDetails != null) {
				this.positionDetails.setPosition(null);
			}
		} else {
			positionDetails.setPosition(this);
		}
		this.positionDetails = positionDetails;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
}