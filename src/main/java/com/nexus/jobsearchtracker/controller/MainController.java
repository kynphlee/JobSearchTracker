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

	private ApplicantService applicantService;

	private PositionService positionService;
	
	@ModelAttribute
	private List<Applicant> listAllApplicants() {
		return applicantService.listAll();
	}

	@Autowired
	public MainController(ApplicantService applicantService, PositionService positionService) {
		this.applicantService = applicantService;
		this.positionService = positionService;
	}
	
	@GetMapping
	public String main(Model model) {
		
		int applicantCount = applicantService.listAll().size();
		int positionCount = positionService.listAll().size();
		
		model.addAttribute("applicantCount", applicantCount);
		model.addAttribute("positionCount", positionCount);
		return "index";
	}
}
