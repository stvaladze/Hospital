package com.solvd.hospital.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Diagnosis {

    @XmlElement
    private int id;

    @XmlElement
    private String name;

    private List<Patient> patients;

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        if (id <= 0) {

            throw new IllegalArgumentException(
                    "Diagnosis ID must be positive."
            );
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null || name.isBlank()) {

            throw new IllegalArgumentException(
                    "Diagnosis name cannot be empty."
            );
        }

        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}