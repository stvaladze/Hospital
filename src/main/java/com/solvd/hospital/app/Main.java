package com.solvd.hospital.app;

import com.solvd.hospital.stax.StaxParser;

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

            logger.info("XML parsed successfully");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while parsing XML", e);
        }
    }
}