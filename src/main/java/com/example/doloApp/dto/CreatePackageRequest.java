package com.example.doloApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class CreatePackageRequest {
    @NotBlank(message = "From city is required")
    private String fromCity;
    
    @NotBlank(message = "To city is required")
    private String toCity;
    
    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    
    @NotNull(message = "Reward is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Reward must be greater than 0")
    private Double reward;
    
    private String imageUrl; // Firebase Storage URL - optional
}
