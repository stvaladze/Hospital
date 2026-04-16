package com.solvd.hospital.dao.mysqlimpl;

import com.solvd.hospital.dao.IDiagnosisDAO;
import com.solvd.hospital.model.Diagnosis;
import com.solvd.hospital.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAOImpl implements IDiagnosisDAO {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void create(Diagnosis diagnosis) {

        String sql = "INSERT INTO diagnosis(name) VALUES (?)";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, diagnosis.getName());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) pool.releaseConnection(conn);
        }
    }

    @Override
    public List<Diagnosis> getAll() {

        List<Diagnosis> list = new ArrayList<>();
        String sql = "SELECT * FROM diagnosis";

        Connection conn = null;

        try {
            conn = pool.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Diagnosis d = new Diagnosis();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) pool.releaseConnection(conn);
        }

        return list;
    }
}