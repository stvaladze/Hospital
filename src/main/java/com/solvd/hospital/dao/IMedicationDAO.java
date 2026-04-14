package com.solvd.hospital.dao;

import com.solvd.hospital.model.Medication;
import java.util.List;

public interface IMedicationDAO {

    void create(Medication medication);

    List<Medication> getAll();
}