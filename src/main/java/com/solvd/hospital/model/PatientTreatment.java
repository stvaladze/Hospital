package com.solvd.hospital.model;

public class PatientTreatment {

    private int id;

    private Patient patient;
    private Treatment treatment;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Treatment getTreatment() { return treatment; }
    public void setTreatment(Treatment treatment) { this.treatment = treatment; }
}