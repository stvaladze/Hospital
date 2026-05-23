package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IDoctorDAO;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAOImpl implements IDoctorDAO {

    private static final Logger logger =
            Logger.getLogger(DoctorDAOImpl.class.getName());

    private final ConnectionPool pool =
            ConnectionPool.getInstance();

    @Override
    public void create(Doctor doctor) {

        String sql =
                "INSERT INTO doctors(first_name, last_name) VALUES (?, ?)";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());

            int rows = stmt.executeUpdate();

            logger.info("Doctor created successfully. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while creating doctor.", e);
        }
    }

    @Override
    public Doctor getById(int id) {

        String sql = "SELECT * FROM doctors WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Doctor doctor = new Doctor();

                    doctor.setId(rs.getInt("id"));
                    doctor.setFirstName(rs.getString("first_name"));
                    doctor.setLastName(rs.getString("last_name"));

                    logger.info("Doctor found with id: " + id);

                    return doctor;
                }
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching doctor by id.", e);
        }

        logger.warning("No doctor found with id: " + id);

        return null;
    }

    @Override
    public List<Doctor> getAll() {

        List<Doctor> doctors = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Doctor doctor = new Doctor();

                doctor.setId(rs.getInt("id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));

                doctors.add(doctor);
            }

            logger.info("Fetched all doctors successfully.");

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching all doctors.", e);
        }

        return doctors;
    }

    @Override
    public void update(Doctor doctor) {

        String sql =
                "UPDATE doctors SET first_name = ?, last_name = ? WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setInt(3, doctor.getId());

            int rows = stmt.executeUpdate();

            logger.info("Doctor updated successfully. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while updating doctor.", e);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM doctors WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            logger.info("Doctor deleted successfully. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while deleting doctor.", e);
        }
    }
}