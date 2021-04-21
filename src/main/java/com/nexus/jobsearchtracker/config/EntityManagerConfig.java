package com.nexus.jobsearchtracker.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class EntityManagerConfig {

    private SessionFactory hibernateFactory;

    @Autowired
    public EntityManagerConfig(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

}