package com.solvd.hospital.dao;

import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Diagnosis;
import java.util.List;

public interface IDiagnosisDAO {

    void create(Diagnosis diagnosis);
    Diagnosis getById(int id);
    List<Diagnosis> getAll();
    void update(Diagnosis diagnosis);
    void delete(int id);
}



