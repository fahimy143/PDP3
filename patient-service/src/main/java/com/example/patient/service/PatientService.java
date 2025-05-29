package com.example.patient.service;

import com.example.patient.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(String id);
    Patient addPatient(Patient patient);
    Patient updatePatient(String id, Patient patient);
    void deletePatient(String id);
}
