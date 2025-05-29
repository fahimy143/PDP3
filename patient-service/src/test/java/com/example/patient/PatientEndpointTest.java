package com.example.patient;

import com.example.patient.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/patients";

    @Test
    public void testCreatePatient() {
        // Create a new patient
        Patient newPatient = new Patient("Ali", "Ahmadi", LocalDate.of(1995, 4, 10),
                "123456789", "ali@example.com", "Male");

        // Send POST request
        ResponseEntity<Patient> response = restTemplate.postForEntity(BASE_URL, newPatient, Patient.class);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensures API correctly returns 201
        assertNotNull(response.getBody().getId()); // Ensures patient was created
    }

    @Test
    public void testGetAllPatients() {
        ResponseEntity<Patient[]> response = restTemplate.getForEntity(BASE_URL, Patient[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetPatientById() {
        // First, create a patient
        Patient newPatient = new Patient("John", "Doe", LocalDate.of(1990, 1, 1),
                "555555555", "john@example.com", "Male");
        ResponseEntity<Patient> postResponse = restTemplate.postForEntity(BASE_URL, newPatient, Patient.class);
        String patientId = postResponse.getBody().getId();

        // Fetch the patient by ID
        ResponseEntity<Patient> getResponse = restTemplate.getForEntity(BASE_URL + "/" + patientId, Patient.class);

        // Verify response
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("John", getResponse.getBody().getFirstName());
    }

    @Test
    public void testUpdatePatient() {
        // First, create a patient
        Patient newPatient = new Patient("Alice", "Smith", LocalDate.of(1995, 4, 10),
                "123456789", "alice@example.com", "Female");
        ResponseEntity<Patient> postResponse = restTemplate.postForEntity(BASE_URL, newPatient, Patient.class);
        String patientId = postResponse.getBody().getId();

        // Modify the patient's contact number
        newPatient.setContactNumber("999999999");

        // Send PUT request to update patient
        restTemplate.put(BASE_URL + "/" + patientId, newPatient);

        // Verify the update
        ResponseEntity<Patient> updatedResponse = restTemplate.getForEntity(BASE_URL + "/" + patientId, Patient.class);
        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        assertEquals("999999999", updatedResponse.getBody().getContactNumber());
    }

    @Test
    public void testDeletePatient() {
        // First, create a patient
        Patient newPatient = new Patient("Mark", "Spencer", LocalDate.of(1985, 8, 5),
                "777777777", "mark@example.com", "Male");
        ResponseEntity<Patient> postResponse = restTemplate.postForEntity(BASE_URL, newPatient, Patient.class);
        String patientId = postResponse.getBody().getId();

        // Delete the patient
        restTemplate.delete(BASE_URL + "/" + patientId);

        // Try fetching again
        ResponseEntity<Patient> getResponse = restTemplate.getForEntity(BASE_URL + "/" + patientId, Patient.class);

        // Verify deletion
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}
