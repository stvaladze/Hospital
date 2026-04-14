package com.solvd.hospital.model;

public class PrescriptionMedication {

    private int id;

    private Prescription prescription;
    private Medication medication;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }

    public Medication getMedication() { return medication; }
    public void setMedication(Medication medication) { this.medication = medication; }
}