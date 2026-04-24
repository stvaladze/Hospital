package com.solvd.hospital.stax;

import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Patient;
import com.solvd.hospital.model.Doctor;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentStaxParser {

    public List<Appointment> parse(XMLStreamReader reader) throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        Appointment current = null;

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String name = reader.getLocalName();

                if ("appointment".equals(name)) {
                    current = new Appointment();

                } else if ("appointment_date".equals(name)) {
                    current.setAppointmentDate(reader.getElementText());

                } else if ("patient_id".equals(name)) {
                    Patient p = new Patient();
                    p.setId(Integer.parseInt(reader.getElementText()));
                    current.setPatient(p);

                } else if ("doctor_id".equals(name)) {
                    Doctor d = new Doctor();
                    d.setId(Integer.parseInt(reader.getElementText()));
                    current.setDoctor(d);
                }
            }

            if (event == XMLStreamConstants.END_ELEMENT) {
                if ("appointment".equals(reader.getLocalName())) {
                    appointments.add(current);
                } else if ("appointments".equals(reader.getLocalName())) {
                    break;
                }
            }
        }

        return appointments;
    }
}