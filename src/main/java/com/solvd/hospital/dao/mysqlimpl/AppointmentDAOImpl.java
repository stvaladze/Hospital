package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IAppointmentDAO;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Patient;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDAOImpl implements IAppointmentDAO {

    private static final Logger logger =
            Logger.getLogger(AppointmentDAOImpl.class.getName());

    private final ConnectionPool pool =
            ConnectionPool.getInstance();

    @Override
    public void create(Appointment appointment) {

        String sql = """
                INSERT INTO appointments
                (patient_id, doctor_id, appointment_date)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatient().getId());

            stmt.setInt(2, appointment.getDoctor().getId());

            stmt.setTimestamp(
                    3,
                    Timestamp.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            int rows = stmt.executeUpdate();

            logger.info("Appointment created successfully. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while creating appointment.", e);
        }
    }

    @Override
    public Appointment getById(int id) {

        String sql = """
                SELECT a.id,
                       a.appointment_date,

                       p.id AS p_id,
                       p.first_name AS p_first_name,
                       p.last_name AS p_last_name,

                       d.id AS d_id,
                       d.first_name AS d_first_name,
                       d.last_name AS d_last_name

                FROM appointments a

                JOIN patients p
                    ON a.patient_id = p.id

                JOIN doctors d
                    ON a.doctor_id = d.id

                WHERE a.id = ?
                """;

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Patient patient = new Patient();

                    patient.setId(rs.getInt("p_id"));
                    patient.setFirstName(rs.getString("p_first_name"));
                    patient.setLastName(rs.getString("p_last_name"));

                    Doctor doctor = new Doctor();

                    doctor.setId(rs.getInt("d_id"));
                    doctor.setFirstName(rs.getString("d_first_name"));
                    doctor.setLastName(rs.getString("d_last_name"));

                    Appointment appointment = new Appointment();

                    appointment.setId(rs.getInt("id"));

                    appointment.setPatient(patient);
                    appointment.setDoctor(doctor);

                    appointment.setPatientId(patient.getId());
                    appointment.setDoctorId(doctor.getId());

                    appointment.setAppointmentDate(
                            rs.getTimestamp("appointment_date").toString()
                    );

                    logger.info("Appointment found with id: " + id);

                    return appointment;
                }
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching appointment by id.", e);
        }

        logger.warning("No appointment found with id: " + id);

        return null;
    }

    @Override
    public List<Appointment> getAll() {

        List<Appointment> appointments = new ArrayList<>();

        String sql = """
                SELECT a.id,
                       a.appointment_date,

                       p.id AS p_id,
                       p.first_name AS p_first_name,
                       p.last_name AS p_last_name,

                       d.id AS d_id,
                       d.first_name AS d_first_name,
                       d.last_name AS d_last_name

                FROM appointments a

                JOIN patients p
                    ON a.patient_id = p.id

                JOIN doctors d
                    ON a.doctor_id = d.id
                """;

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Patient patient = new Patient();

                patient.setId(rs.getInt("p_id"));
                patient.setFirstName(rs.getString("p_first_name"));
                patient.setLastName(rs.getString("p_last_name"));

                Doctor doctor = new Doctor();

                doctor.setId(rs.getInt("d_id"));
                doctor.setFirstName(rs.getString("d_first_name"));
                doctor.setLastName(rs.getString("d_last_name"));

                Appointment appointment = new Appointment();

                appointment.setId(rs.getInt("id"));

                appointment.setPatient(patient);
                appointment.setDoctor(doctor);

                appointment.setPatientId(patient.getId());
                appointment.setDoctorId(doctor.getId());

                appointment.setAppointmentDate(
                        rs.getTimestamp("appointment_date").toString()
                );

                appointments.add(appointment);
            }

            logger.info("Fetched all appointments successfully.");

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching all appointments.", e);
        }

        return appointments;
    }

    @Override
    public void update(Appointment appointment) {

        String sql = """
                UPDATE appointments
                SET patient_id = ?,
                    doctor_id = ?,
                    appointment_date = ?
                WHERE id = ?
                """;

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatient().getId());

            stmt.setInt(2, appointment.getDoctor().getId());

            stmt.setTimestamp(
                    3,
                    Timestamp.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            stmt.setInt(4, appointment.getId());

            int rows = stmt.executeUpdate();

            logger.info("Appointment updated successfully. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while updating appointment.", e);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM appointments WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            logger.info("Appointment deleted successfully. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while deleting appointment.", e);
        }
    }
}