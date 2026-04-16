package com.solvd.hospital.stax;

import com.solvd.hospital.model.Patient;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.util.ArrayList;
import java.util.List;

public class PatientStaxParser {

    public List<Patient> parse(XMLStreamReader reader) throws Exception {
        List<Patient> patients = new ArrayList<>();
        Patient current = null;

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String name = reader.getLocalName();

                if ("patient".equals(name)) {
                    current = new Patient();
                } else if ("firstName".equals(name)) {
                    current.setFirstName(reader.getElementText());
                } else if ("lastName".equals(name)) {
                    current.setLastName(reader.getElementText());
                }
            }

            if (event == XMLStreamConstants.END_ELEMENT) {
                if ("patient".equals(reader.getLocalName())) {
                    patients.add(current);
                } else if ("patients".equals(reader.getLocalName())) {
                    break;
                }
            }
        }

        return patients;
    }
}