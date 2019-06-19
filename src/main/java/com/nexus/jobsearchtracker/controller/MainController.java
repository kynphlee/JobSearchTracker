package com.nexus.jobsearchtracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexus.jobsearchtracker.dao.ApplicantRepository;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Skill;

@Controller
public class MainController {
	
	private Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@RequestMapping(path = "/newApplicant", params={"addSkill"})
	public String addSkill(final Applicant applicant, BindingResult result) {
		applicant.getSkills().add(new Skill());
		return "redirect:/newApplicant";
	}
	
	@RequestMapping(path="/newApplicant", params= {"removeSkill"})
	public String removeSkill(final Applicant applicant, final BindingResult result,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
		applicant.getSkills().remove(rowId.intValue());
	    return "redirect:/newApplicant";
	}
	
	@GetMapping("/")
	public String main(Model model) {
		int count = applicantRepository.findAll().size();
		model.addAttribute("applicantCount", count);
		return "index";
	}	
	
	@GetMapping("/newApplicant")
	public String newApplicantForm(Model model) {
		Applicant applicant = new Applicant();
		
		applicant.getSkills().add(new Skill());
		model.addAttribute("newApplicant", applicant);
		return "newApplicant";
	}
	
	@PostMapping("/newApplicant")
	public String addApplicant(
			@ModelAttribute("newApplicant") Applicant newApplicant,
			BindingResult result) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
		Applicant a = applicantRepository.save(newApplicant);
		
		log.info(String.format("Applicant \"%s %s\" has been saved.%n", a.getFirstName(), a.getLastName()));
		return "redirect:/";
	}
}
