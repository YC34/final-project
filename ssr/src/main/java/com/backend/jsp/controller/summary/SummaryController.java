package com.backend.jsp.controller.summary;

import com.backend.jsp.service.summary.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/summary")
public class SummaryController {

    private final SummaryService service;

    @Autowired
    public SummaryController(SummaryService service) {
        this.service = service;
    }

    @GetMapping
    public String summary(Model model) {
        Map<String, Object> result = service.getSummary();
        model.addAttribute("summary", result);

        return "summary/summary-page";
    }
}
