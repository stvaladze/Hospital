package com.solvd.hospital.model;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DoctorTest {

    private Doctor doctor;

    @BeforeClass(alwaysRun = true)
    public void suiteSetup() {
        log.info("=== DoctorTest suite started ===");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        doctor = new Doctor();
        doctor.setId(1);
        doctor.setFirstName("John");
        doctor.setLastName("Smith");

        Specialization spec = new Specialization();
        spec.setId(1);
        doctor.setSpecialization(spec);

        log.debug("Doctor created: {} {}", doctor.getFirstName(), doctor.getLastName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Test finished");
    }

    @AfterClass(alwaysRun = true)
    public void suiteTeardown() {
        log.info("=== DoctorTest suite finished ===");
    }


    @Test(priority = 1, groups = "smoke")
    public void testDoctorIdIsSet() {
        log.debug("Testing doctor ID");
        Assert.assertEquals(doctor.getId(), 1, "Doctor ID should be 1");
    }

    @Test(priority = 2, groups = "smoke")
    public void testDoctorFirstName() {
        log.debug("Testing doctor first name");
        Assert.assertEquals(doctor.getFirstName(), "John", "First name should be John");
    }

    @Test(priority = 3, groups = "smoke")
    public void testDoctorLastName() {
        log.debug("Testing doctor last name");
        Assert.assertEquals(doctor.getLastName(), "Smith", "Last name should be Smith");
    }

    @Test(priority = 4, groups = "smoke")
    public void testDoctorSpecializationNotNull() {
        log.debug("Testing doctor specialization");
        Assert.assertNotNull(doctor.getSpecialization(), "Specialization should not be null");
    }

    @Test(priority = 5, groups = "smoke")
    public void testDoctorAllFieldsTogether() {
        log.debug("Testing all doctor fields with SoftAssert");
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(doctor.getId(), 1);
        soft.assertEquals(doctor.getFirstName(), "John");
        soft.assertEquals(doctor.getLastName(), "Smith");
        soft.assertNotNull(doctor.getSpecialization());
        soft.assertAll();
    }

    @DataProvider(name = "validDoctors")
    public Object[][] validDoctors() {
        return new Object[][] {
                { 1, "Anna",   "Kalandadze"   },
                { 2, "Giorgi", "Beridze"       },
                { 3, "Nino",   "Tabatadze"     },
                { 4, "Levan",  "Kvaratskhelia" },
        };
    }

    @Test(priority = 6, groups = "smoke", dataProvider = "validDoctors")
    public void testCreateValidDoctor(int id, String firstName, String lastName) {
        log.debug("Testing valid doctor creation: {} {}", firstName, lastName);
        doctor.setId(id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(doctor.getId(), id);
        soft.assertEquals(doctor.getFirstName(), firstName);
        soft.assertEquals(doctor.getLastName(), lastName);
        soft.assertAll();
    }


    @Test(priority = 7, groups = "regression",
            expectedExceptions = IllegalArgumentException.class)
    public void testNullFirstNameIsRejected() {
        log.debug("Testing null first name is rejected");
        doctor.setFirstName(null);
    }

    @Test(priority = 8, groups = "regression",
            expectedExceptions = IllegalArgumentException.class)
    public void testEmptyFirstNameIsRejected() {
        log.debug("Testing empty first name is rejected");
        doctor.setFirstName("");
    }

    @Test(priority = 9, groups = "regression",
            expectedExceptions = IllegalArgumentException.class)
    public void testNullLastNameIsRejected() {
        log.debug("Testing null last name is rejected");
        doctor.setLastName(null);
    }

    @Test(priority = 10, groups = "regression",
            expectedExceptions = IllegalArgumentException.class)
    public void testEmptyLastNameIsRejected() {
        log.debug("Testing empty last name is rejected");
        doctor.setLastName("");
    }

    @Test(priority = 11, groups = "regression",
            expectedExceptions = IllegalArgumentException.class)
    public void testNegativeIdIsRejected() {
        log.debug("Testing negative ID is rejected");
        doctor.setId(-1);
    }

    @Test(priority = 12, groups = "regression",
            expectedExceptions = IllegalArgumentException.class)
    public void testZeroIdIsRejected() {
        log.debug("Testing zero ID is rejected");
        doctor.setId(0);
    }

    @Test(priority = 13, groups = "regression")
    public void testAppointmentsNullByDefault() {
        log.debug("Testing appointments are null by default");
        Assert.assertNull(doctor.getAppointments(),
                "New doctor should have no appointments yet");
    }
}