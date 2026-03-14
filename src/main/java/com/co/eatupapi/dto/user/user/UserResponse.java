package com.co.eatupapi.dto.user.user;

import com.co.eatupapi.domain.user.user.UserStatus;

import java.time.LocalDate;
import java.util.UUID;

public class UserResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String department;
    private String city;
    private String address;
    private String branch;
    private UserStatus status;

    public UserResponse(UUID id, String firstName, String lastName, String documentType, String documentNumber,
                        String phone, String email, LocalDate birthDate, String department, String city,
                        String address, String branch, UserStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.department = department;
        this.city = city;
        this.address = address;
        this.branch = branch;
        this.status = status;
    }

    public UUID getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDocumentType() { return documentType; }
    public String getDocumentNumber() { return documentNumber; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getDepartment() { return department; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public String getBranch() { return branch; }
    public UserStatus getStatus() { return status; }
}
