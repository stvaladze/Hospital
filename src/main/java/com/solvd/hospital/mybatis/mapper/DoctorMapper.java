package com.solvd.hospital.mybatis.mapper;

import com.solvd.hospital.model.Doctor;
import java.util.List;

public interface DoctorMapper {
    void insert(Doctor doctor);
    Doctor selectById(int id);
    List<Doctor> selectAll();
    void update(Doctor doctor);
    void delete(int id);
}