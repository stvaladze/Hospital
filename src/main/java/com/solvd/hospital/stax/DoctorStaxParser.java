package com.solvd.hospital.stax;

import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Specialization;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.util.ArrayList;
import java.util.List;

public class DoctorStaxParser {

    public List<Doctor> parse(XMLStreamReader reader) throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        Doctor current = null;

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String name = reader.getLocalName();

                if ("doctor".equals(name)) {
                    current = new Doctor();

                } else if ("first_name".equals(name)) {
                    current.setFirstName(reader.getElementText());

                } else if ("last_name".equals(name)) {
                    current.setLastName(reader.getElementText());

                } else if ("specialization_id".equals(name)) {
                    Specialization s = new Specialization();
                    s.setId(Integer.parseInt(reader.getElementText()));
                    current.setSpecialization(s);
                }
            }

            if (event == XMLStreamConstants.END_ELEMENT) {
                if ("doctor".equals(reader.getLocalName())) {
                    doctors.add(current);
                } else if ("doctors".equals(reader.getLocalName())) {
                    break;
                }
            }
        }

        return doctors;
    }
}