package com.solvd.hospital.app;

import com.solvd.hospital.dao.IAppointmentDAO;
import com.solvd.hospital.dao.IDoctorDAO;
import com.solvd.hospital.dao.IPatientDAO;
import com.solvd.hospital.dao.impl.AppointmentDAOImpl;
import com.solvd.hospital.dao.impl.DoctorDAOImpl;
import com.solvd.hospital.dao.impl.PatientDAOImpl;
import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Patient;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        IPatientDAO patientDAO = new PatientDAOImpl();
        IDoctorDAO doctorDAO = new DoctorDAOImpl();
        IAppointmentDAO appointmentDAO = new AppointmentDAOImpl();

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Tutberidze");
        patientDAO.create(patient);

        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Smith");
        doctorDAO.create(doctor);

        List<Patient> patients = patientDAO.getAll();
        List<Doctor> doctors = doctorDAO.getAll();

        for (Patient p : patients) {
            System.out.println(p.getId() + " " + p.getFirstName());
        }

        for (Doctor d : doctors) {
            System.out.println(d.getId() + " " + d.getFirstName());
        }

        if (!patients.isEmpty() && !doctors.isEmpty()) {
            Appointment appointment = new Appointment();
            appointment.setPatient(patients.get(0));
            appointment.setDoctor(doctors.get(0));
            appointment.setAppointmentDate(LocalDateTime.now());
            appointmentDAO.create(appointment);
        }

        List<Appointment> appointments = appointmentDAO.getAll();

        for (Appointment a : appointments) {
            System.out.println(
                    a.getId() + " " +
                            a.getPatient().getFirstName() + " " +
                            a.getDoctor().getFirstName() + " " +
                            a.getAppointmentDate()
            );
        }

        if (!patients.isEmpty()) {
            patientDAO.delete(patients.get(0).getId());
        }
    }
}