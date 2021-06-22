package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Sogne")
public class Parish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sogne_kode")
    private Long parishCode;

    @Column(name = "navn")
    private String name;

    @ManyToOne
    @JoinColumn(name = "kommune_id")
    private Municipality municipality;

    @Column(name = "smitteniveau")
    private double infectionLevel;

    @Column(name = "nedlukning_startdato")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date shutdownStartDate;

    public Parish() {
    }

    public Parish(Long parishCode, String name, Municipality municipality, double infectionLevel, Date shutdownStartDate) {
        this.parishCode = parishCode;
        this.name = name;
        this.municipality = municipality;
        this.infectionLevel = infectionLevel;
        this.shutdownStartDate = shutdownStartDate;
    }

    public Parish(Long id, Long parishCode, String name, Municipality municipality, double infectionLevel, Date shutdownStartDate) {
        this.id = id;
        this.parishCode = parishCode;
        this.municipality = municipality;
        this.name = name;
        this.infectionLevel = infectionLevel;
        this.shutdownStartDate = shutdownStartDate;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParishCode() {
        return parishCode;
    }

    public void setParishCode(Long parishCode) {
        this.parishCode = parishCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public double getInfectionLevel() {
        return infectionLevel;
    }

    public void setInfectionLevel(double positivePercentage) {
        this.infectionLevel = positivePercentage;
    }

    public Date getShutdownStartDate() {
        return shutdownStartDate;
    }

    public void setShutdownStartDate(Date shutdownStartDate) {
        this.shutdownStartDate = shutdownStartDate;
    }

}
