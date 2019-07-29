package com.nexus.jobsearchtracker.controller;

import java.util.List;
import java.util.Map;

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
	
	@ModelAttribute
	private List<Applicant> listAllApplicants() {
		return applicantRepository.findAll();
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
		
		model.addAttribute("applicant", applicant);
		return "newApplicant";
	}

	@PostMapping("/newApplicant")
	public String addApplicant(
			@ModelAttribute("applicant") Applicant newApplicant,
			BindingResult result, HttpServletRequest req) {
		if (result.hasErrors())
			System.out.println("An error in submission has occurred.");
		
		Applicant a = applicantRepository.save(newApplicant);
		req.getSession().setAttribute("applicantId", a.getId());
		req.getSession().setAttribute("applicant", a);
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
	public String newApplicantSkillsForm(Model model, HttpServletRequest req) {

		Long applicantId = (Long) req.getSession().getAttribute("applicantId");
		Applicant applicant = (Applicant) req.getSession().getAttribute("applicant");
		
		Skill s = new Skill();
		s.setApplicant(applicant);
		applicant.getSkills().add(s);

		req.getSession().setAttribute("applicant", applicant);
		model.addAttribute("applicantId", applicantId);
		model.addAttribute("firstName", applicant.getFirstName());
		model.addAttribute("applicant", applicant);
		return "applicantSkills";
	}
	
	@PostMapping("/applicantSkills")
	public String addSkillSet(Model model, HttpServletRequest req) {
		Applicant applicant = (Applicant) req.getSession().getAttribute("applicant");
		
		updateSkillList(applicant, req.getParameterMap());
		
		List<Skill> applicantSkills = applicant.getSkills();
		for(Skill skill: applicantSkills) {
			Skill s = skillsRepository.save(skill);
			System.out.printf("Added \'%s\' for %s%n", s.getSkill(), applicant.getFirstName());
		}
		
		return "redirect:/";
	}
	
	//-----------------------------Dynamic Fields------------------------------------------------
	
	@RequestMapping(value = "/applicantSkills", params = {"addRow"})
	public String addSkillRow(Model model, HttpServletRequest req) {
		final Long applicantId = Long.valueOf(req.getParameter("addRow"));
		Applicant applicant = (Applicant) req.getSession().getAttribute("applicant");
		
		updateSkillList(applicant, req.getParameterMap());

		Skill s = new Skill();
		s.setApplicant(applicant);
		applicant.getSkills().add(s);

		req.getSession().setAttribute("applicant", applicant);
		model.addAttribute("applicant", applicant);
		model.addAttribute("firstName", applicant.getFirstName());
		model.addAttribute("applicantId", applicantId);
		
		return "applicantSkills";
	}
	
	@RequestMapping(value="/applicantSkills", params={"removeRow"})
	public String removeRow(Model model, HttpServletRequest req) {
		final Long rowId = Long.valueOf(req.getParameter("removeRow"));
		Applicant applicant = (Applicant) req.getSession().getAttribute("applicant");
		
		applicant.getSkills().remove(rowId.intValue());
	    model.addAttribute("applicant", applicant);
	    model.addAttribute("applicantId", applicant.getId());
	    return "applicantSkills";
	}
	
	private void updateSkillList(Applicant applicant, Map<String, String[]> reqParams) {
		reqParams.forEach((key, value) -> {
			if (key.contains("skills")) {
				int id = Integer.parseInt(key.substring(7, 8));
				if (key.contains(".skill"))
					applicant.getSkills().get(id).setSkill(value[0]);
				else if (key.contains(".yearsOfExperience")) {
					applicant.getSkills().get(id).setYearsOfExperience(value[0]);
				}
			}
		});
	}
}
