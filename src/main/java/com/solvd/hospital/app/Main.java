package com.solvd.hospital.app;

import com.solvd.hospital.stax.StaxParser;
import com.solvd.hospital.util.JaxbUtil;
import com.solvd.hospital.model.Hospital;

import java.io.File;
import java.io.InputStream;
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


        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing XML", e);
        }
    }
}