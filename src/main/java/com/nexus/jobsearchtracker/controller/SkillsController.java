package com.nexus.jobsearchtracker.controller;

import com.nexus.jobsearchtracker.domain.Applicant;
import com.nexus.jobsearchtracker.domain.Skill;
import com.nexus.jobsearchtracker.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillsController {

    private SkillService skillService;

    @Autowired
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/new")
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

    @PostMapping("/add")
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

    @RequestMapping(value = "/addRow")
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

    @RequestMapping(value="/removeRow")
    public String removeRow(Model model, HttpServletRequest req) {
        final Long rowId = Long.valueOf(req.getParameter("removeRow"));
        Applicant applicant = (Applicant) req.getSession().getAttribute("applicant");

        applicant.getSkills().remove(rowId.intValue());
        model.addAttribute("applicant", applicant);
        model.addAttribute("applicantId", applicant.getId());
        return "applicantSkills";
    }
}
