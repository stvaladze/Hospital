package com.solvd.hospital.model;

public class DoctorDepartment {

    private int id;

    private Doctor doctor;
    private Department department;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}