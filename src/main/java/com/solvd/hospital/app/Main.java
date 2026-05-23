package com.solvd.hospital.app;

import com.solvd.hospital.dao.IDoctorDAO;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.util.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        try (SqlSession session =
                     MyBatisUtil.getSqlSessionFactory().openSession()) {

            IDoctorDAO doctorDAO =
                    session.getMapper(IDoctorDAO.class);


            Doctor doctor = new Doctor();

            doctor.setFirstName("John");
            doctor.setLastName("Smith");

            doctorDAO.create(doctor);

            session.commit();

            logger.info("Doctor created successfully.");



            Doctor foundDoctor =
                    doctorDAO.getById(1);

            logger.info("Doctor found: " + foundDoctor);


            List<Doctor> doctors =
                    doctorDAO.getAll();

            for (Doctor d : doctors) {

                logger.info(d.toString());
            }



            foundDoctor.setFirstName("UpdatedName");

            doctorDAO.update(foundDoctor);

            session.commit();

            logger.info("Doctor updated successfully.");



        } catch (Exception e) {

            logger.log(Level.SEVERE,
                    "Error in Main class.", e);
        }
    }
}