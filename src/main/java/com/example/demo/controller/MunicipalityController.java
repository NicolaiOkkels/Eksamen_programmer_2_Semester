package com.example.demo.controller;

import com.example.demo.service.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MunicipalityController {

    @Autowired
    MunicipalityService municipalityService;

    @GetMapping("/kommuner")
    public String parishes(Model model) {
        model.addAttribute("municipality", municipalityService.findAll());
        return "kommuner";
    }
}
