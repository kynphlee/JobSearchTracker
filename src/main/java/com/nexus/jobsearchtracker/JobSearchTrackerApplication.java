package com.nexus.jobsearchtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

//import com.nexus.jobsearchtracker.dao.ApplicantRepository;

@SpringBootApplication
public class JobSearchTrackerApplication /*implements CommandLineRunner*/{

//	@Autowired
//	private ApplicantRepository applicantRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JobSearchTrackerApplication.class, args);
	}
	
	@Bean
	@Description("Thymeleaf Template Resolver")
	public ClassLoaderTemplateResolver templateResolver() {
		// Spring boot solution
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	 
	    return templateResolver;
	}
	 
	@Bean
	@Description("Thymeleaf Template Engine")
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    return templateEngine;
	}
	
	@Bean
	@Description("Thymeleaf View Resolver")
	public ThymeleafViewResolver viewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setOrder(1);
	    return viewResolver;
	}

//	@Override
//	public void run(String... args) throws Exception {
//		applicantRepository.deleteAllInBatch();
//		
//		Applicant newApplicant = new Applicant();
//		newApplicant.setFirstName("Kendall");
//		newApplicant.setLastName("Fleming");
//		newApplicant.setYearsOfExperience(11);
//		
//		Address myAddress = new Address();
//		myAddress.setAddress1("1419 Druid Valley Drive NE");
//		myAddress.setAddress2("Apt. A");
//		myAddress.setCity("Atlanta");
//		myAddress.setState("Georgia");
//		myAddress.setZip(30329);
//		
//		newApplicant.setAddress(myAddress);
//		
//		applicantRepository.save(newApplicant);
//		
//		Optional<Applicant> result = applicantRepository.findById(1L);
//		
//		if (result.isPresent())
//			System.out.println(result.get());
//	}

}
