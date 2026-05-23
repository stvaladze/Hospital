package com.solvd.hospital.dao.mybatisimpl;

import com.solvd.hospital.dao.IPatientDAO;
import com.solvd.hospital.model.Patient;
import com.solvd.hospital.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PatientMyBatisImpl implements IPatientDAO {

    @Override
    public void create(Patient patient) {

        try (SqlSession session =
                MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.insert(
                    "PatientMapper.insert",
                    patient
            );

            session.commit();
        }
    }


    @Override
    public Patient getById(int id) {

        try (SqlSession session =
                MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectOne(
                    "PatientMApper.selectById",
                    id
            );
        }
    }


    @Override
    public List<Patient> getAll() {

        try (SqlSession session =
                MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectList(
                    "PatientMapper.selectAll"
            );
        }
    }

    @Override
    public void update(Patient patient) {

        try (SqlSession session =
                MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.update(
                    "PatientMapper.update",
                    patient
            );

            session.commit();
        }
    }

    @Override
    public void delete(int id) {

        try (SqlSession session =
                MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.delete(
                    "PatientMapper.delete",
                    id
            );

            session.commit();
        }
    }
}
