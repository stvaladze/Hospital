package com.solvd.hospital.stax;

import com.solvd.hospital.model.*;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.util.List;

public class XMLWriter {

    public void write(List<Patient> patients,
                      List<Doctor> doctors,
                      List<Appointment> appointments,
                      List<Diagnosis> diagnoses,
                      List<Treatment> treatments) {

        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer =
                    factory.createXMLStreamWriter(new FileWriter("hospital.xml"));

            writer.writeStartDocument();
            writer.writeStartElement("hospital");

            writer.writeStartElement("patients");
            for (Patient p : patients) {
                writer.writeStartElement("patient");
                writer.writeAttribute("id", String.valueOf(p.getId()));

                writer.writeStartElement("firstName");
                writer.writeCharacters(p.getFirstName());
                writer.writeEndElement();

                writer.writeStartElement("lastName");
                writer.writeCharacters(p.getLastName());
                writer.writeEndElement();

                writer.writeEndElement();
            }
            writer.writeEndElement();

            writer.writeStartElement("doctors");
            for (Doctor d : doctors) {
                writer.writeStartElement("doctor");
                writer.writeAttribute("id", String.valueOf(d.getId()));

                writer.writeStartElement("firstName");
                writer.writeCharacters(d.getFirstName());
                writer.writeEndElement();

                writer.writeStartElement("lastName");
                writer.writeCharacters(d.getLastName());
                writer.writeEndElement();

                writer.writeEndElement();
            }
            writer.writeEndElement();

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}