package com.solvd.hospital.app;

import com.solvd.hospital.mybatis.mapper.DoctorMapper;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Hospital;
import com.solvd.hospital.stax.StaxParser;
import com.solvd.hospital.util.JacksonUtil;
import com.solvd.hospital.util.JaxbUtil;
import com.solvd.hospital.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger =
            Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        try {

            String xmlPath = "src/main/resources/hospital.xml";
            StaxParser parser = new StaxParser();
            parser.parse(xmlPath);
            logger.info("STAX parsing done");


            InputStream input = Main.class
                    .getClassLoader()
                    .getResourceAsStream("hospital.xml");
            Hospital hospital = JaxbUtil.unmarshal(input, Hospital.class);
            logger.info("JAXB unmarshalling done");


            JaxbUtil.marshal(hospital, new File("output.xml"));
            logger.info("JAXB marshalling done");


            Doctor doctor = new Doctor();
            doctor.setId(1);
            doctor.setFirstName("John");
            doctor.setLastName("Smith");
            JacksonUtil.toJson(doctor, new File("doctor.json"));
            logger.info("Jackson serialization done");


            Doctor loaded = JacksonUtil.fromJson(
                    new File("doctor.json"), Doctor.class);
            logger.info("Jackson deserialization done — doctor: "
                    + loaded.getFirstName());


            try (SqlSession session = MyBatisUtil.getFactory().openSession()) {
                DoctorMapper mapper = session.getMapper(DoctorMapper.class);
                Doctor mybatisDoctor = new Doctor();
                mybatisDoctor.setFirstName("MyBatis");
                mybatisDoctor.setLastName("Doctor");
                mapper.insert(mybatisDoctor);
                session.commit();
                logger.info("MyBatis insert done");
            }


            try (SqlSession session = MyBatisUtil.getFactory().openSession()) {
                DoctorMapper mapper = session.getMapper(DoctorMapper.class);
                List<Doctor> doctors = mapper.selectAll();
                doctors.forEach(d -> logger.info("MyBatis doctor: "
                        + d.getFirstName() + " " + d.getLastName()));
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing", e);
        }
    }
}