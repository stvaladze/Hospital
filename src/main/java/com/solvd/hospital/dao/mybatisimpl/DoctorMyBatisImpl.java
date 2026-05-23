package com.solvd.hospital.dao.mybatisimpl;

import com.solvd.hospital.dao.IDoctorDAO;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DoctorMyBatisImpl implements IDoctorDAO {

    @Override
    public void create(Doctor doctor) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.insert(
                    "DoctorMapper.insert",
                    doctor
            );

            session.commit();
        }
    }

    @Override
    public Doctor getById(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectOne(
                    "DoctorMapper.selectById",
                    id
            );
        }
    }

    @Override
    public List<Doctor> getAll() {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectList(
                    "DoctorMapper.selectAll"
            );
        }
    }

    @Override
    public void update(Doctor doctor) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.update(
                    "DoctorMapper.update",
                    doctor
            );

            session.commit();
        }
    }

    @Override
    public void delete(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.delete(
                    "DoctorMapper.delete",
                    id
            );

            session.commit();
        }
    }
}