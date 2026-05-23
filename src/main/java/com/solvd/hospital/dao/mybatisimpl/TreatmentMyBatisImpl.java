package com.solvd.hospital.dao.mybatisimpl;

import com.solvd.hospital.dao.ITreatmentDAO;
import com.solvd.hospital.model.Treatment;
import com.solvd.hospital.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TreatmentMyBatisImpl implements ITreatmentDAO {

    @Override
    public void create(Treatment treatment) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.insert(
                    "TreatmentMapper.insert",
                    treatment
            );

            session.commit();
        }
    }

    @Override
    public Treatment getById(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectOne(
                    "TreatmentMapper.selectById",
                    id
            );
        }
    }

    @Override
    public List<Treatment> getAll() {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            return session.selectList(
                    "TreatmentMapper.selectAll"
            );
        }
    }

    @Override
    public void update(Treatment treatment) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.update(
                    "TreatmentMapper.update",
                    treatment
            );

            session.commit();
        }
    }

    @Override
    public void delete(int id) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            session.delete(
                    "TreatmentMapper.delete",
                    id
            );

            session.commit();
        }
    }
}