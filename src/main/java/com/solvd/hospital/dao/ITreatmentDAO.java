package com.solvd.hospital.dao;

import com.solvd.hospital.model.Treatment;
import java.util.List;

public interface ITreatmentDAO {

    void create(Treatment treatment);

    List<Treatment> getAll();
}