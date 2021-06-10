package com.example.demo.service;

import com.example.demo.model.Municipality;
import com.example.demo.model.Parish;
import com.example.demo.repository.MunicipalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MunicipalityService {

    @Autowired
    MunicipalityRepository municipalityRepository;

    public List<Municipality> findAll(){
        Set<Municipality> municipalitySet = new HashSet<>();

        for (Municipality municipality: municipalityRepository.findAll()) {
            municipalitySet.add(municipality);
        }

        List<Municipality> sortedList = municipalitySet.stream()
                .sorted(Comparator.comparing(Municipality::getId))
                .collect(Collectors.toList());

        return sortedList;
    }

}
