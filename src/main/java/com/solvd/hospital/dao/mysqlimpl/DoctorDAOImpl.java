package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IDoctorDAO;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements IDoctorDAO {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Doctor doctor) {

        String sql = "INSERT INTO doctors(first_name, last_name) VALUES (?, ?)";

        try (
            Connection conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Doctor> getAll() {

        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors";


        try (
           Connection conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getInt("id"));
                d.setFirstName(rs.getString("first_name"));
                d.setLastName(rs.getString("last_name"));

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }
}