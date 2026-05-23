package com.solvd.hospital.dao.mybatisimpl;

import com.solvd.hospital.dao.IDiagnosisDAO;
import com.solvd.hospital.model.Diagnosis;
import com.solvd.hospital.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DiagnosisMyBatisImpl implements IDiagnosisDAO {

    @Override
    public void create(Diagnosis diagnosis) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.insert(
                    "DiagnosisMapper.insert",
                    diagnosis
            );

            session.commit();
        }
    }

    @Override
    public Diagnosis getById(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectOne(
                    "DiagnosisMapper.selectById",
                    id
            );
        }
    }

    @Override
    public List<Diagnosis> getAll() {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectList(
                    "DiagnosisMapper.selectAll"
            );
        }
    }

    @Override
    public void update(Diagnosis diagnosis) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.update(
                    "DiagnosisMapper.update",
                    diagnosis
            );

            session.commit();
        }
    }

    @Override
    public void delete(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.delete(
                    "DiagnosisMapper.delete",
                    id
            );

            session.commit();
        }
    }
}