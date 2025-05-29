package com.example.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.patient.model.Patient;
import com.example.patient.controller.PatientController;
import com.example.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPatients() throws Exception {
        Patient p1 = new Patient("Alex", "Lastname", LocalDate.of(1985, 5, 20), "5551234567", "alex@example.com", "male");
        Patient p2 = new Patient("name", "lastname2", LocalDate.of(1990, 7, 10), "5559876543", "name@example.com", "female");

        Mockito.when(patientService.getAllPatients()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Alex"))
                .andExpect(jsonPath("$[1].firstName").value("name"));
    }

    @Test
    public void testAddPatient() throws Exception {
        Patient p = new Patient("Alex", "Lastname", LocalDate.of(1985, 5, 20), "5551234567", "alex@example.com", "male");

        Mockito.when(patientService.addPatient(Mockito.any(Patient.class))).thenReturn(p);

        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alex"));
    }
}
