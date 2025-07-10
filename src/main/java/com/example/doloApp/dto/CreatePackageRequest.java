package com.example.doloApp.dto;

import lombok.Data;

@Data
public class CreatePackageRequest {
    private String fromCity;
    private String toCity;
    private double weight;
    private double reward;
    private String imageUrl; // Firebase Storage URL
}
