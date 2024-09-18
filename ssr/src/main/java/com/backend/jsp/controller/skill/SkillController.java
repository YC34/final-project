package com.backend.jsp.controller.skill;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("skill")
public class SkillController {

    @GetMapping
    public String skill(Model model) {
        return "skill/skill-page";
    }

}
