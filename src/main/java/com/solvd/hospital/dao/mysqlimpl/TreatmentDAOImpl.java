package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.ITreatmentDAO;
import com.solvd.hospital.model.Treatment;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreatmentDAOImpl implements ITreatmentDAO {

    private static final Logger logger =
            Logger.getLogger(TreatmentDAOImpl.class.getName());

    private final ConnectionPool pool =
            ConnectionPool.getInstance();

    @Override
    public void create(Treatment treatment) {

        String sql =
                "INSERT INTO treatments(name) VALUES (?)";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treatment.getName());

            int rows = stmt.executeUpdate();

            logger.info("Treatment created. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while creating treatment.", e);
        }
    }

    @Override
    public Treatment getById(int id) {

        String sql = "SELECT * FROM treatments WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Treatment treatment = new Treatment();

                    treatment.setId(rs.getInt("id"));
                    treatment.setName(rs.getString("name"));

                    return treatment;
                }
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching treatment.", e);
        }

        return null;
    }

    @Override
    public List<Treatment> getAll() {

        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT * FROM treatments";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Treatment treatment = new Treatment();

                treatment.setId(rs.getInt("id"));
                treatment.setName(rs.getString("name"));

                treatments.add(treatment);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while fetching treatments.", e);
        }

        return treatments;
    }

    @Override
    public void update(Treatment treatment) {

        String sql =
                "UPDATE treatments SET name = ? WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treatment.getName());
            stmt.setInt(2, treatment.getId());

            int rows = stmt.executeUpdate();

            logger.info("Treatment updated. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while updating treatment.", e);
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM treatments WHERE id = ?";

        try (Connection conn = pool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            logger.info("Treatment deleted. Rows affected: " + rows);

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error while deleting treatment.", e);
        }
    }
}