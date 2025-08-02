package com.example.doloApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTripRequest {
    @NotBlank(message = "From city is required")
    private String fromCity;
    
    @NotBlank(message = "To city is required")
    private String toCity;
    
    @NotNull(message = "Travel date is required")
    @Future(message = "Travel date must be in the future")
    private LocalDate travelDate;
    
    private boolean spaceAvailable = true; // Default to true
}
