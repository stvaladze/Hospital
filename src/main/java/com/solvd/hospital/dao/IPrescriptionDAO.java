package com.solvd.hospital.dao;

import com.solvd.hospital.model.Prescription;
import java.util.List;

public interface IPrescriptionDAO {

    void create(Prescription prescription);

    List<Prescription> getAll();
}