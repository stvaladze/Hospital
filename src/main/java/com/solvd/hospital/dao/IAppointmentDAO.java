package com.solvd.hospital.dao;

import com.solvd.hospital.model.Appointment;
import java.util.List;

public interface IAppointmentDAO {

    void create(Appointment appointment);

    Appointment getById(int id);

    List<Appointment> getAll();

    void delete(int id);
}