package com.example.doloApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Package {

    @Id
    private String id;

    private String senderId;       // Firebase UID
    private String fromCity;
    private String toCity;
    private double weight;         // in kg
    private double reward;         // e.g., ₹50 for traveler

    private String imageUrl;       // uploaded from frontend to Firebase Storage
    private String status;         // open / booked / delivered
}

