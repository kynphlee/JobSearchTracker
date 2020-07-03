package com.nexus.jobsearchtracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Position;
import com.nexus.jobsearchtracker.domain.Skill;
import com.nexus.jobsearchtracker.service.ApplicantService;
import com.nexus.jobsearchtracker.service.PositionService;
import com.nexus.jobsearchtracker.service.SkillService;

@Controller
@RequestMapping("/")
public class MainController {
	
	// Services
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private PositionService positionService;
	
	@ModelAttribute
	private List<Applicant> listAllApplicants() {
		return applicantService.listAll();
	}
	
	@GetMapping
	public String main(Model model) {
		
		int applicantCount = applicantService.listAll().size();
		int positionCount = positionService.listAll().size();
		
		model.addAttribute("applicantCount", applicantCount);
		model.addAttribute("positionCount", positionCount);
		return "index";
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
		
		skillService.updateSkillList(applicant, req.getParameterMap());
		
		List<Skill> applicantSkills = applicant.getSkills();
		for(Skill skill: applicantSkills) {
			skillService.saveSkill(skill);
		}
		
		return "redirect:/";
	}
	
	//-----------------------------Dynamic Fields------------------------------------------------
	
	@RequestMapping(value = "/applicantSkills", params = {"addRow"})
	public String addSkillRow(Model model, HttpServletRequest req) {
		final Long applicantId = Long.valueOf(req.getParameter("addRow"));
		Applicant applicant = (Applicant) req.getSession().getAttribute("applicant");
		
		skillService.updateSkillList(applicant, req.getParameterMap());

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
}
