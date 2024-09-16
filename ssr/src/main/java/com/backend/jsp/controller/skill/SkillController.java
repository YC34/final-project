package com.backend.jsp.controller.skill;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("skill")
public class SkillController {

    @GetMapping
    public String skill() {
        return "skill/skill-page";
    }

}
