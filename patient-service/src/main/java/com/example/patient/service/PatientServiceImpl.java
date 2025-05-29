package com.example.patient.service;

import com.example.patient.model.Patient;
import com.example.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    
    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(String id) {
        return repository.findById(id);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public Patient updatePatient(String id, Patient patient) {
        patient.setId(id); // Ensure the ID is set
        return repository.save(patient);
    }

    @Override
    public void deletePatient(String id) {
        repository.deleteById(id);
    }
}
