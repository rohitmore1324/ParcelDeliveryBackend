package com.example.doloApp.dto;

import lombok.Data;

@Data
public class CreateBookingRequest {
    private String tripId;
    private String packageId;
    private String userId;
}

