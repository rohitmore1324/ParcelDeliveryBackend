package com.example.doloApp.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    private String id;

    private String packageId;
    private String tripId;
    private String userId; // User who made the booking

    private String status; // "pending", "accepted", "completed", "rejected"
    private LocalDateTime createdAt;
}

