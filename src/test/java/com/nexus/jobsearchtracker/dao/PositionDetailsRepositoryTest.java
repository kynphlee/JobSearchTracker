package com.nexus.jobsearchtracker.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PositionDetailsRepositoryTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionDetailsRepository positionDetailsRepository;

    @Test
    @Ignore
    public void test() {

    }
}
