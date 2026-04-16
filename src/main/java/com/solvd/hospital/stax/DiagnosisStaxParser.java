package com.solvd.hospital.stax;

import com.solvd.hospital.model.Diagnosis;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisStaxParser {

    public List<Diagnosis> parse(XMLStreamReader reader) throws Exception {
        List<Diagnosis> diagnoses = new ArrayList<>();
        Diagnosis current = null;

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String name = reader.getLocalName();

                if ("diagnosis".equals(name)) {
                    current = new Diagnosis();

                } else if ("name".equals(name)) {
                    current.setName(reader.getElementText());
                }
            }

            if (event == XMLStreamConstants.END_ELEMENT) {
                if ("diagnosis".equals(reader.getLocalName())) {
                    diagnoses.add(current);
                } else if ("diagnoses".equals(reader.getLocalName())) {
                    break;
                }
            }
        }

        return diagnoses;
    }
}