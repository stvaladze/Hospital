package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.ITreatmentDAO;
import com.solvd.hospital.model.Treatment;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAOImpl implements ITreatmentDAO {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Treatment treatment) {

        String sql = "INSERT INTO treatment(name) VALUES (?)";


        try (
            Connection conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, treatment.getName());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Treatment> getAll() {

        List<Treatment> list = new ArrayList<>();
        String sql = "SELECT * FROM treatment";


        try (
           Connection conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)){
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Treatment t = new Treatment();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));

                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}