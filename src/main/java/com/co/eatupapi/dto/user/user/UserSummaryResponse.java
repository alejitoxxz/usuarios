package com.co.eatupapi.dto.user.user;

import com.co.eatupapi.domain.user.user.UserStatus;

import java.util.UUID;

public class UserSummaryResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
    private String phone;
    private String branch;
    private UserStatus status;

    public UserSummaryResponse(UUID id, String firstName, String lastName, String documentNumber,
                               String email, String phone, String branch, UserStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.branch = branch;
        this.status = status;
    }

    public UUID getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDocumentNumber() { return documentNumber; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getBranch() { return branch; }
    public UserStatus getStatus() { return status; }
}
