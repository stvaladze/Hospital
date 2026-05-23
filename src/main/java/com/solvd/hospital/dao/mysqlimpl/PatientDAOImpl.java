package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IPatientDAO;
import com.solvd.hospital.model.Patient;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAOImpl implements IPatientDAO {


    private static final Logger logger =
            Logger.getLogger(PatientDAOImpl.class.getName());


    private final ConnectionPool pool =
            ConnectionPool.getInstance();


    @Override
    public void create(Patient patient) {


        String sql =
                "INSERT INTO patients(first_name, last_name) VALUES (?, ?)";


        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());


            stmt.setString(2, patient.getLastName());


            int rows = stmt.executeUpdate();

            logger.info("Patient created. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while creating patient.", e);
        }
    }


    @Override
    public Patient getById(int id) {

        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {


                if (rs.next()) {

                    Patient patient = new Patient();

                    patient.setId(rs.getInt("id"));
                    patient.setFirstName(rs.getString("first_name"));
                    patient.setLastName(rs.getString("last_name"));

                    return patient;
                }
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching patient by id.", e);
        }

        return null;
    }


    @Override
    public List<Patient> getAll() {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Patient patient = new Patient();

                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));

                patients.add(patient);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching all patients.", e);
        }

        return patients;
    }


    @Override
    public void update(Patient patient) {

        String sql =
                "UPDATE patients SET first_name = ?, last_name = ? WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setInt(3, patient.getId());

            int rows = stmt.executeUpdate();

            logger.info("Patient updated. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while updating patient.", e);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM patients WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            logger.info("Patient deleted. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while deleting patient.", e);
        }
    }
}