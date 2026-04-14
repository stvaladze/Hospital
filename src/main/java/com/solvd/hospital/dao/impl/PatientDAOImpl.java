package com.solvd.hospital.dao.impl;

import com.solvd.hospital.dao.IPatientDAO;
import com.solvd.hospital.model.Patient;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements IPatientDAO {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Patient patient) {

        String sql = "INSERT INTO patients(first_name, last_name) VALUES (?, ?)";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }
    }

    @Override
    public Patient getById(int id) {

        String sql = "SELECT * FROM patients WHERE id = ?";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));

                return patient;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }

        return null;
    }

    @Override
    public List<Patient> getAll() {

        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));

                patients.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }

        return patients;
    }

    @Override
    public void update(Patient patient) {

        String sql = "UPDATE patients SET first_name = ?, last_name = ? WHERE id = ?";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setInt(3, patient.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM patients WHERE id = ?";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }
    }
}