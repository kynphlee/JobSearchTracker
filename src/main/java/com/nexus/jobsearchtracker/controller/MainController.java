package com.nexus.jobsearchtracker.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexus.jobsearchtracker.dao.ApplicantRepository;
import com.nexus.jobsearchtracker.dao.PositionRepository;
import com.nexus.jobsearchtracker.dao.SkillsRepository;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Position;
import com.nexus.jobsearchtracker.domain.Skill;

@Controller
public class MainController {
	
	private Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	@GetMapping("/")
	public String main(Model model) {
		int count = applicantRepository.findAll().size();
		model.addAttribute("applicantCount", count);
		return "index";
	}	
	
	@GetMapping("/newApplicant")
	public String newApplicantForm(Model model) {
		Applicant applicant = new Applicant();
		
//		applicant.getSkills().add(new Skill());
		model.addAttribute("newApplicant", applicant);
		return "newApplicant";
	}
	
	@PostMapping("/newApplicant")
	public String addApplicant(
			@ModelAttribute("newApplicant") Applicant newApplicant,
			BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
		Applicant a = applicantRepository.save(newApplicant);
		redirect.addFlashAttribute("applicantId", a.getId());
		log.info(String.format("Applicant \"%s %s\" has been saved.%n", a.getFirstName(), a.getLastName()));
		
		return "redirect:/applicantSkills";
	}
	
	@GetMapping("/newPosition")
	public String newPosition(Model model) {
		Position position = new Position();
		model.addAttribute("newPosition", position);
		return "newPosition";
	}
	
	@PostMapping("/newPosition")
	public String addPosition(
			@ModelAttribute("newPosition") Position newPosition,
			BindingResult result) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
		Position p = positionRepository.save(newPosition);
		
		log.info(String.format("Position \"%s\" has been saved.%n", p.getPositionTitle()));
		return "redirect:/";
	}
	
	@GetMapping("/applicantSkills")
	public String newApplicantSkillsForm(Model model) {

		long applicantId = (Long) model.asMap().get("applicantId");
		Optional<Applicant> app = applicantRepository.findById(applicantId);
		Applicant applicant = app.get();
		applicant.getSkills().add(new Skill());
		model.addAttribute("applicantSkills", applicant);
		return "applicantSkills";
	}
	
	@PostMapping("/applicantSkills")
	public String addSkillSet(
//			@ModelAttribute("applicantSkills") SkillsList applicantSkills,
			@ModelAttribute("skills") Skill applicantSkill,
			BindingResult result) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
		Skill s = skillsRepository.save(applicantSkill);
		
		return "redirect:/";
	}
}
