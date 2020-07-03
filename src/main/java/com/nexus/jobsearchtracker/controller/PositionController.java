package com.nexus.jobsearchtracker.controller;

import com.nexus.jobsearchtracker.domain.Position;
import com.nexus.jobsearchtracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/new")
    public String newPosition(Model model) {
        Position position = new Position();
        model.addAttribute("newPosition", position);
        return "newPosition";
    }

    @PostMapping("/new")
    public String addPosition(
            @ModelAttribute("newPosition") Position newPosition,
            BindingResult result) {
        if (result.hasErrors())
            System.out.println("An error in submission has occurred.");

        positionService.savePosition(newPosition);
        return "redirect:/";
    }
}
