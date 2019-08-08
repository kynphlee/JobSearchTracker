package com.nexus.jobsearchtracker.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

	public DataSource getDatasource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:mem:testdb");
		dataSourceBuilder.username("sa");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}
}
