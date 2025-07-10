package com.example.doloApp.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String phone;
    private String role; // sender | traveler | both
}

