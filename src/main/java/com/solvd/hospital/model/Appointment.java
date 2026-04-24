package com.solvd.hospital.model;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment {

    @XmlElement
    private int id;

    // ✅ For JAXB (XML uses IDs)
    @XmlElement(name = "patient_id")
    private int patientId;

    @XmlElement(name = "doctor_id")
    private int doctorId;

    @XmlElement(name = "appointment_date")
    private String appointmentDate;


    private Patient patient;
    private Doctor doctor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}