package com.solvd.hospital.dao;

import com.solvd.hospital.model.Doctor;
import java.util.List;

public interface IDoctorDAO {
    void create(Doctor doctor);
    Doctor getById(int id);
    List<Doctor> getAll();
    void update(Doctor doctor);
    void delete(int id);
}