package com.example.doloApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;      // Firebase UID

    private String name;
    private String email;
    private String phone;

    private String role;    // sender | traveler | both
}

