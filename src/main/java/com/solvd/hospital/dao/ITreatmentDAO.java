package com.solvd.hospital.dao;

import com.solvd.hospital.model.Treatment;

import java.util.List;

public interface ITreatmentDAO {

    void create(Treatment treatment);
    Treatment getById(int id);
    List<Treatment> getAll();
    void update(Treatment treatment);
    void delete(int id);
}