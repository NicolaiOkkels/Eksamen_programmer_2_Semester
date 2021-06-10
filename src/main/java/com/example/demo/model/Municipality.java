package com.example.demo.model;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;


@Entity
@Table(name = "Kommune")
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "navn")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipality")
    private Set<Parish> parish;

    public Municipality() {
    }

    public double calculateMunicipalityInfectionLevels(){
        double totalAmount = 0.0;
        for (Iterator<Parish> it = parish.iterator(); it.hasNext(); ) {
            Parish parish = it.next();
            totalAmount += parish.getInfectionLevel();
        }
        double avg = totalAmount / parish.size();

        return avg;
    }

    public Municipality(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

