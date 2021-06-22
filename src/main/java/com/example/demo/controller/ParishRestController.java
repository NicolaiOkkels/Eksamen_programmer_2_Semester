package com.example.demo.controller;

import com.example.demo.model.Municipality;
import com.example.demo.model.Parish;
import com.example.demo.service.ParishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ParishRestController {

    @Autowired
    private ParishService parishService;

    @GetMapping("/sogne")
    public ResponseEntity<List<Parish>> findAllParish(){
        return ResponseEntity.ok().body(parishService.findAll());
    }

    @GetMapping("/sogne/{id}")
    public ResponseEntity<Parish> findParishById(@PathVariable("id") long id) {
        Parish parish = parishService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/sogne/" + parish.getId());
        return new ResponseEntity<Parish>(parish ,headers, HttpStatus.CREATED);
    }

    @PutMapping("/sogne/{id}")
    public Parish parishUpdate(@PathVariable("id") long id, @RequestBody Parish parish){
        return new Parish(id, parish.getParishCode(), parish.getName(), parish.getMunicipality(), parish.getInfectionLevel(),parish.getShutdownStartDate());
    }

    @PostMapping("/sogne")
    public ResponseEntity<Parish> create(@RequestBody Parish parish){
        Parish newParish = parishService.create(parish);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/sogne/" + parish.getId());

        return new ResponseEntity<Parish>(newParish,headers,HttpStatus.CREATED); //201 CREATED
    }

    @DeleteMapping("/sogne/{id}")
    public ResponseEntity<String> deleteParish(@PathVariable("id") long id){
        parishService.deleteById(id);
        return new ResponseEntity<String>("Slettet sogn: " + id, HttpStatus.OK);
    }

}
