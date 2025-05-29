package com.example.patient.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patients")
public class Patient {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String contactNumber;
    private String emailAddress;
    private String gender;

    public Patient(String firstName, String lastName, LocalDate dateOfBirth,
               String contactNumber, String emailAddress, String gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.contactNumber = contactNumber;
    this.emailAddress = emailAddress;
    this.gender = gender;
}

}
