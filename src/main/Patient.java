package main;

import java.time.LocalDateTime;
import java.util.List;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String gender;
    private String email;
    private String phone;
    private String address;

    private List<Appointment> appointments;
    private List<Diagnosis> diagnoses;
    private List<Treatment> treatments;
    private List<Prescription> prescriptions;

    // getters & setters
}