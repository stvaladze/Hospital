package com.solvd.hospital.dao;

import com.solvd.hospital.model.Prescription;
import com.solvd.hospital.model.Treatment;

import java.util.List;

public interface IPrescriptionDAO {

    void create(Prescription prescription);
    Prescription getById(int id);
    List<Prescription> getAll();
    void update(Prescription prescription);
    void delete(int id);
}