package com.nexus.jobsearchtracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nexus.jobsearchtracker.dao.ApplicantRepository;
import com.nexus.jobsearchtracker.dao.PositionRepository;
import com.nexus.jobsearchtracker.dao.SkillsRepository;
import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Position;
import com.nexus.jobsearchtracker.domain.Skill;
import com.nexus.jobsearchtracker.domain.SkillsList;

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
			BindingResult result) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
//		List<Skill> skillSet = newApplicant.getSkills();
		Applicant a = applicantRepository.save(newApplicant);
		log.info(String.format("Applicant \"%s %s\" has been saved.%n", a.getFirstName(), a.getLastName()));
		
//		for(Skill skill: skillSet) {
//			skillRepository.save(skill);
//			log.info(String.format("Skill \"%s %s\" for applicant \"%s %s\" has been saved.%n"
//					, skill.getSkill(), a.getFirstName(), a.getLastName()));
//		}
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
//		return "redirect:/";
		return "redirect:/";
	}
	
	@GetMapping("/applicantSkills")
	public String newApplicantSkillsForm(Model model) {
		SkillsList skillsList = new SkillsList();
		
		skillsList.getSkillSet().add(new Skill());
		model.addAttribute("applicantSkills", skillsList);
		return "applicantSkills";
	}
	
	@PostMapping("/applicantSkills")
	public String addSkillSet(
			@ModelAttribute("applicantSkills") SkillsList applicantSkills,
			BindingResult result) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
		SkillsList s = skillsRepository.save(applicantSkills);
		
		return "redirect:/";
	}
}
