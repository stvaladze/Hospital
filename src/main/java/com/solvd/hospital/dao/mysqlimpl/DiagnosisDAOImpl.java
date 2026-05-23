package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IDiagnosisDAO;
import com.solvd.hospital.model.Diagnosis;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiagnosisDAOImpl implements IDiagnosisDAO {

    private static final Logger logger =
            Logger.getLogger(DiagnosisDAOImpl.class.getName());

    private final ConnectionPool pool =
            ConnectionPool.getInstance();

    @Override
    public void create(Diagnosis diagnosis) {

        String sql =
                "INSERT INTO diagnosis(name) VALUES (?)";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, diagnosis.getName());

            int rows = stmt.executeUpdate();

            logger.info("Diagnosis created. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while creating diagnosis.", e);
        }
    }

    @Override
    public Diagnosis getById(int id) {

        String sql = "SELECT * FROM diagnosis WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Diagnosis diagnosis = new Diagnosis();

                    diagnosis.setId(rs.getInt("id"));
                    diagnosis.setName(rs.getString("name"));

                    return diagnosis;
                }
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching diagnosis.", e);
        }

        return null;
    }

    @Override
    public List<Diagnosis> getAll() {

        List<Diagnosis> diagnoses = new ArrayList<>();

        String sql = "SELECT * FROM diagnosis";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Diagnosis diagnosis = new Diagnosis();

                diagnosis.setId(rs.getInt("id"));
                diagnosis.setName(rs.getString("name"));

                diagnoses.add(diagnosis);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching diagnoses.", e);
        }

        return diagnoses;
    }

    @Override
    public void update(Diagnosis diagnosis) {

        String sql =
                "UPDATE diagnosis SET name = ? WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, diagnosis.getName());
            stmt.setInt(2, diagnosis.getId());

            int rows = stmt.executeUpdate();

            logger.info("Diagnosis updated. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while updating diagnosis.", e);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM diagnosis WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            logger.info("Diagnosis deleted. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while deleting diagnosis.", e);
        }
    }
}