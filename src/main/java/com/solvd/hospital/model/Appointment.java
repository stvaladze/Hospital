package com.solvd.hospital.model;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class Appointment {

    @XmlElement
    private int id;


    @XmlElement(name = "patient_id")
    private int patientId;

    @XmlElement(name = "doctor_id")
    private int doctorId;

    @XmlElement(name = "appointment_date")
    private String appointmentDate;


    private Patient patient;
    private Doctor doctor;

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", appointmentDate='" + appointmentDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        if (id <= 0) {

            throw new IllegalArgumentException(
                    "Appointment ID must be positive."
            );
        }

        this.id = id;
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {

        if (patientId <= 0) {

            throw new IllegalArgumentException(
                    "Patient ID must be positive."
            );
        }

        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {

        if (doctorId <= 0) {

            throw new IllegalArgumentException(
                    "Doctor ID must be positive."
            );
        }

        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {

        if (appointmentDate == null
                || appointmentDate.isBlank()) {

            throw new IllegalArgumentException(
                    "Appointment date cannot be empty."
            );
        }

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