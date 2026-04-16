package com.solvd.hospital.stax;

import com.solvd.hospital.model.Treatment;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.util.ArrayList;
import java.util.List;

public class TreatmentStaxParser {

    public List<Treatment> parse(XMLStreamReader reader) throws Exception {
        List<Treatment> treatments = new ArrayList<>();
        Treatment current = null;

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String name = reader.getLocalName();

                if ("treatment".equals(name)) {
                    current = new Treatment();
                } else if ("name".equals(name)) {
                    current.setName(reader.getElementText());
                }
            }

            if (event == XMLStreamConstants.END_ELEMENT) {
                if ("treatment".equals(reader.getLocalName())) {
                    treatments.add(current);
                } else if ("treatments".equals(reader.getLocalName())) {
                    break;
                }
            }
        }

        return treatments;
    }
}