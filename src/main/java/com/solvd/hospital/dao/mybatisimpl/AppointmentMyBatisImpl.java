package com.solvd.hospital.dao.mybatisimpl;

import com.solvd.hospital.dao.IAppointmentDAO;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AppointmentMyBatisImpl
        implements IAppointmentDAO {

    @Override
    public void create(Appointment appointment) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.insert(
                    "AppointmentMapper.insert",
                    appointment
            );

            session.commit();
        }
    }

    @Override
    public Appointment getById(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectOne(
                    "AppointmentMapper.selectById",
                    id
            );
        }
    }

    @Override
    public List<Appointment> getAll() {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectList(
                    "AppointmentMapper.selectAll"
            );
        }
    }

    @Override
    public void update(Appointment appointment) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.update(
                    "AppointmentMapper.update",
                    appointment
            );

            session.commit();
        }
    }

    @Override
    public void delete(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.delete(
                    "AppointmentMapper.delete",
                    id
            );

            session.commit();
        }
    }
}