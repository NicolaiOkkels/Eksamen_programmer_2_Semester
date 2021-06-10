package com.example.demo.controller;

import com.example.demo.model.Municipality;
import com.example.demo.model.Parish;
import com.example.demo.service.MunicipalityService;
import com.example.demo.service.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class ParishController {

    @Autowired
    ParishService parishService;

    @Autowired
    MunicipalityService municipalityService;

    @GetMapping("/")
    public String parishes(Model model) {
        model.addAttribute("parishes", parishService.findAll());
        model.addAttribute("currentDate", LocalDate.now());
        return "sogne";
    }

    @GetMapping("/opret")
    public String create(Model model) {
        model.addAttribute("municipality", municipalityService.findAll());
        return "opret";
    }

    @PostMapping("/opret")
    public String create(@ModelAttribute Parish parish, WebRequest request) {
        int id = Integer.parseInt(request.getParameter("kommune_id"));

        for (Municipality municipality : municipalityService.findAll()) {
            if (id == municipality.getId()) {
                parish.setMunicipality(municipality);
            }
        }

        Parish newParish = parishService.create(parish);
        parishService.update(newParish);
        return "redirect:/";
    }

    @GetMapping("/opdater/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        model.addAttribute("parish", parishService.findById(id));
        model.addAttribute("municipality", municipalityService.findAll());
        return "opdater";
    }

    @PostMapping("/opdater")
    public String update(@ModelAttribute Parish parish) {
        parishService.update(parish);
        return "redirect:/";
    }

    @GetMapping("/slet/{id}")
    public String delete(@PathVariable("id") long id) {
        parishService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/setPreScheduledShutdownDate")
    public String shutdownDate(){
        return "redirect:/";
    }
}
