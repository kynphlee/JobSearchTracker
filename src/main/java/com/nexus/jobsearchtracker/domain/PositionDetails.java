package com.nexus.jobsearchtracker.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PositionDetails")
public class PositionDetails {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private String responsibilities;
	
	@Lob
	private String qualifications;
	
	@Lob
	private String preferredQualifications;
	
	@Lob
	private String perksBenefits;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Position position;
	
	public PositionDetails() {}

	public PositionDetails(String responsibilities, String qualifications, String preferredQualifications,
			String perksBenefits) {
		super();
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
