package com.example.doloApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTripRequest {
    private String fromCity;
    private String toCity;
    private LocalDate travelDate;
    private boolean spaceAvailable;
}
