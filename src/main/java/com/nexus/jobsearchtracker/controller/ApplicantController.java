package com.nexus.jobsearchtracker.controller;

import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/new")
    public String newApplicantForm(Model model) {
        Applicant applicant = new Applicant();

        model.addAttribute("applicant", applicant);
        return "newApplicant";
    }

    @PostMapping("/add")
    public String addApplicant(
            @ModelAttribute("applicant") Applicant newApplicant,
            BindingResult result, HttpServletRequest req) {
        if (result.hasErrors())
            System.out.println("An error in submission has occurred.");

        Applicant a = applicantService.save(newApplicant);
        req.getSession().setAttribute("applicantId", a.getId());
        req.getSession().setAttribute("applicant", a);

        return "redirect:/applicantSkills";
    }
}
