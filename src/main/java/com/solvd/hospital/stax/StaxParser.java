package com.solvd.hospital.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;

public class StaxParser {

    public void parse(String filePath) throws Exception {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(new FileInputStream(filePath));

        PatientStaxParser patientParser = new PatientStaxParser();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String name = reader.getLocalName();

                switch (name) {
                    case "patients":
                        patientParser.parse(reader);
                        break;
                }
            }
        }
    }
}