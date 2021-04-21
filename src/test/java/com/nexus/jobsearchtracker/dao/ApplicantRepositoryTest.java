package com.nexus.jobsearchtracker.dao;

import com.nexus.jobsearchtracker.domain.Address;
import com.nexus.jobsearchtracker.domain.Applicant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicantRepositoryTest {

	@Autowired
	private ApplicantRepository applicantRepository;

	private Address testAddress;
	private Applicant testApplicant;

	@Before
	public void init() {
		testAddress = new Address();
		testAddress.setAddress1("1419 Druid Valley Drive NE");
		testAddress.setAddress2("Apt. A");
		testAddress.setCity("Atlanta");
		testAddress.setState("Ga");
		testAddress.setZip(30329);
		
		testApplicant = new Applicant(
				"Kendall",
				"Fleming",
				11,
				testAddress);
	}
	
	@Test
	public void saveAndRetrieveEntity() {
		applicantRepository.save(testApplicant);
		assertThat(applicantRepository.count()).isEqualTo(1);
		
		List<Applicant> allApplicants = applicantRepository.findAll();
		Applicant a = allApplicants.get(0);
		
		assertThat(a.getFirstName()).isEqualTo("Kendall");
		assertThat(a.getLastName()).isEqualTo("Fleming");
		assertThat(a.getYearsOfExperience()).isEqualTo(11);
		assertThat(a.getAddress()).isEqualTo(testAddress);
	}
}
