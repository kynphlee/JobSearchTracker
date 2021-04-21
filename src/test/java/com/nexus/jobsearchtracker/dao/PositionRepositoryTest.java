package com.nexus.jobsearchtracker.dao;

import com.nexus.jobsearchtracker.domain.Address;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PositionRepositoryTest {

    @Autowired
    private PositionRepository positionRepository;

    private Address testAddress;
    private Applicant testApplicant;

    private Position testPosition;
    static Address positionAddress;

    @Before
    public void init() {
        testAddress = new Address();
        testAddress.setAddress1("1419 Druid Valley Drive NE");
        testAddress.setAddress2("Apt. A");
        testAddress.setCity("Atlanta");
        testAddress.setState("Ga");
        testAddress.setZip(30329);

        positionAddress = new Address();
        positionAddress.setAddress1("123 Main St.");
        positionAddress.setAddress2("Suite 420");
        positionAddress.setCity("Atlanta");
        positionAddress.setState("Ga");
        positionAddress.setZip(30030);

        testApplicant = new Applicant(
                "Kendall",
                "Fleming",
                11,
                testAddress);
    }

    @Test
    public void saveAndRetrievePosition() {

        testPosition = new Position();
        testPosition.setApplicant(testApplicant);
        testPosition.setCompanyName("Some Company");
        testPosition.setAddress(positionAddress);
        testPosition.setDateApplied("04/20/2020");

        Position result = positionRepository.save(testPosition);

        assertThat(result.getCompanyName()).isEqualTo(testPosition.getCompanyName());
        assertThat(result.getDateApplied()).isEqualTo(testPosition.getDateApplied());
        assertThat(result.getAddress()).isEqualTo(testPosition.getAddress());
    }
}
