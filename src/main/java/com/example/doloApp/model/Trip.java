package com.example.doloApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    @Id
    private String id;

    private String travelerId;  // Firebase UID
    private String fromCity;
    private String toCity;
    private LocalDate travelDate;

    private boolean spaceAvailable;
}

