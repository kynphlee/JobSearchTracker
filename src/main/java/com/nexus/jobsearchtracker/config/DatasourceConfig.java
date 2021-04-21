package com.nexus.jobsearchtracker.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {

	@Bean
	public DataSource getDatasource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://db:3306/jobsearchtracker?useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSourceBuilder.username("user");
		dataSourceBuilder.password("p9goebXG7549");
		return dataSourceBuilder.build();
	}
}
