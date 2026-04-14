package com.solvd.hospital.dao;

import com.solvd.hospital.model.Doctor;
import java.util.List;

public interface IDoctorDAO {

    void create(Doctor doctor);

    List<Doctor> getAll();
}