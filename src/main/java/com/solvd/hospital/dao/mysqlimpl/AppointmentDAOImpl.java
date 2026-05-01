package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IAppointmentDAO;
import com.solvd.hospital.model.*;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements IAppointmentDAO {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Appointment appointment) {
        String sql = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatient().getId());
            stmt.setInt(2, appointment.getDoctor().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Appointment> getAll() {
        List<Appointment> list = new ArrayList<>();

        String sql = """
            SELECT a.id, a.appointment_date,
                   p.id as p_id, p.first_name as p_name,
                   d.id as d_id, d.first_name as d_name
            FROM appointments a
            JOIN patients p ON a.patient_id = p.id
            JOIN doctors d ON a.doctor_id = d.id
        """;

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("p_id"));
                p.setFirstName(rs.getString("p_name"));

                Doctor d = new Doctor();
                d.setId(rs.getInt("d_id"));
                d.setFirstName(rs.getString("d_name"));

                Appointment a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPatient(p);
                a.setDoctor(d);
                a.setAppointmentDate(rs.getString("appointment_date"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Appointment getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
    }
}