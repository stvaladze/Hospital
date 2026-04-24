package com.solvd.hospital.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "hospital")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hospital {

    @XmlElementWrapper(name = "patients")
    @XmlElement(name = "patient")
    private List<Patient> patients;

    @XmlElementWrapper(name = "doctors")
    @XmlElement(name = "doctor")
    private List<Doctor> doctors;

    @XmlElementWrapper(name = "appointments")
    @XmlElement(name = "appointment")
    private List<Appointment> appointments;

    @XmlElementWrapper(name = "diagnoses")
    @XmlElement(name = "diagnosis")
    private List<Diagnosis> diagnoses;

    @XmlElementWrapper(name = "treatments")
    @XmlElement(name = "treatment")
    private List<Treatment> treatments;

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }

    public List<Doctor> getDoctors() { return doctors; }
    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }

    public List<Diagnosis> getDiagnoses() { return diagnoses; }
    public void setDiagnoses(List<Diagnosis> diagnoses) { this.diagnoses = diagnoses; }

    public List<Treatment> getTreatments() { return treatments; }
    public void setTreatments(List<Treatment> treatments) { this.treatments = treatments; }
}