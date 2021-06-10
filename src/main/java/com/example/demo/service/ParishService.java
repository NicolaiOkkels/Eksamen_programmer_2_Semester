package com.example.demo.service;

import com.example.demo.model.Parish;
import com.example.demo.repository.ParishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParishService {

    @Autowired
    ParishRepository parishRepository;

    public List<Parish> findAll(){
        Set<Parish> parishSet = new HashSet<>();

        for (Parish parish: parishRepository.findAll()) {
            parishSet.add(parish);
        }

        List<Parish> sortedList = parishSet.stream()
                .sorted(Comparator.comparing(Parish::getId))
                .collect(Collectors.toList());

        return sortedList;
    }

    public Parish findById(Long id) {
        //findById returnerer en optional, så der skal checkes for null og pakkes ud
        Optional<Parish> optionalParish = parishRepository.findById(id);

        if (!optionalParish.isPresent()) {
            throw new RuntimeException("Sogn " + id + " ikke fundet");
        }

        return optionalParish.get();
    }

    public Parish create(Parish parish) {
        List<Parish> list = new ArrayList<>();
        for (Parish tempParish : parishRepository.findAll()) {
            list.add(tempParish);
        }

        int currentMaxId = list.size();
        int newId = currentMaxId += 1;
        parish.setId((long) newId);
        return parishRepository.save(parish);
    }

    public void deleteById(Long id) {
        parishRepository.deleteById(id);
    }

    public Parish update(Parish parish) {
        return parishRepository.save(parish);
    }
}
