package com.solvd.hospital.dao;

import com.solvd.hospital.model.Diagnosis;
import java.util.List;

public interface IDiagnosisDAO {

    void create(Diagnosis diagnosis);

    List<Diagnosis> getAll();
}