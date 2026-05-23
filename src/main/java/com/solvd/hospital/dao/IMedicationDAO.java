package com.solvd.hospital.dao;

import com.solvd.hospital.model.Medication;
import java.util.List;

public interface IMedicationDAO {

    void create(Medication medication);
    Medication getById(int id);
    List<Medication> getAll();
    void update(Medication medication);
    void delete(int id);
}