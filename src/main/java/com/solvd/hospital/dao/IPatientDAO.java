package com.solvd.hospital.dao;

import com.solvd.hospital.model.Patient;
import java.util.List;

public interface IPatientDAO {

    void create(Patient patient);

    Patient getById(int id);

    List<Patient> getAll();

    void update(Patient patient);

    void delete(int id);
}