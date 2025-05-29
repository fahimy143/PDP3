package com.example.patient.client;

import com.example.patient.model.Patient;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;

public class PatientRestClient {

    private static final String BASE_URL = "http://localhost:8080/patients";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        // 1. Create a new patient
        Patient newPatient = new Patient(
                "Ali",
                "Ahmadi",
                LocalDate.of(1995, 4, 10),
                "123456789",
                "ali.ahmadi@example.com",
                "Male"
        );

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> requestEntity = new HttpEntity<>(newPatient, headers);

        // POST - Create new patient
        ResponseEntity<Patient> postResponse = restTemplate.postForEntity(BASE_URL, requestEntity, Patient.class);
        System.out.println("Created patient: " + postResponse.getBody());

        // 2. GET - All patients
        ResponseEntity<Patient[]> getAllResponse = restTemplate.getForEntity(BASE_URL, Patient[].class);
        System.out.println("All patients:");
        Arrays.stream(getAllResponse.getBody()).forEach(System.out::println);

        // 3. GET - Single patient by ID
        String patientId = postResponse.getBody().getId();
        ResponseEntity<Patient> getResponse = restTemplate.getForEntity(BASE_URL + "/" + patientId, Patient.class);
        System.out.println("Retrieved patient: " + getResponse.getBody());

        // 4. PUT - Update patient
        Patient updatedPatient = getResponse.getBody();
        updatedPatient.setContactNumber("999999999");
        HttpEntity<Patient> updateEntity = new HttpEntity<>(updatedPatient, headers);
        restTemplate.exchange(BASE_URL + "/" + patientId, HttpMethod.PUT, updateEntity, Void.class);
        System.out.println("Updated patient contact number.");

        // 5. DELETE - Remove patient
        restTemplate.delete(BASE_URL + "/" + patientId);
        System.out.println("Deleted patient with ID: " + patientId);
    }
}
